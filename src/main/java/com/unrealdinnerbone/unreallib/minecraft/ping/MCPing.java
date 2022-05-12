package com.unrealdinnerbone.unreallib.minecraft.ping;

import com.unrealdinnerbone.unreallib.TaskScheduler;
import com.unrealdinnerbone.unreallib.json.JsonUtil;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MCPing
{

    public static CompletableFuture<MCServerPingResponse> ping(String host, int port) {
        CompletableFuture<MCServerPingResponse> future = new CompletableFuture<>();

        TaskScheduler.handleTaskOnThread(() -> {
            try {
                future.complete(getPing(host, port));
            }catch(IOException | TimeoutException e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, TimeoutException {
        System.out.println(getPing("apollouhc.com", 25565).description());
    }

    private static MCServerPingResponse getPing(final String address, final int port) throws IOException, TimeoutException {

        if (address == null) {
            throw new IOException("Hostname cannot be null!");
        }

        String json;

        var ping = System.currentTimeMillis();

        try (var socket = new Socket()) {

            socket.connect(new InetSocketAddress(address, port), 5000);
            ping = System.currentTimeMillis() - ping;

            var handshakeStream = new ByteArrayOutputStream();
            var handshake = new DataOutputStream(handshakeStream);

            handshake.write(0x00); // Handshake Packet
            writeVarInt(handshake, 4); // Protocol Version
            writeVarInt(handshake, address.length());
            handshake.writeBytes(address);
            handshake.writeShort(port);
            writeVarInt(handshake, 1); // Status Handshake

            var out = new DataOutputStream(socket.getOutputStream());
            writeVarInt(out, handshakeStream.size());
            out.write(handshakeStream.toByteArray());

            // STATUS REQUEST ->
            out.writeByte(0x01); // Packet Size
            out.writeByte(0x00); // Packet Status Request

            // <- STATUS RESPONSE
            var in = new DataInputStream(socket.getInputStream());
            readVarInt(in);
            var id = readVarInt(in);

            io(id == -1, "Server ended data stream unexpectedly.");
            io(id != 0x00, "Server returned invalid packet.");

            var length = readVarInt(in);
            io(length == -1, "Server ended data stream unexpectedly.");
            io(length == 0, "Server returned unexpected value.");

            var data = new byte[length];
            in.readFully(data);
            json = new String(data, StandardCharsets.UTF_8);

            // Ping ->
            out.writeByte(0x09); // Packet Size
            out.writeByte(0x01); // Ping Packet
            out.writeLong(System.currentTimeMillis());

            // Ping <-
            readVarInt(in);
            id = readVarInt(in);
            io(id == -1, "Server ended data stream unexpectedly.");
            io(id != 0x01, "Server returned invalid packet"); // Check Ping Packet

        }


        try {
            return JsonUtil.DEFAULT.parse(MCServerPingResponse.class, json);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Throws IOException when condition is false.
     *
     * @param b Condition
     * @param m Exception cause
     * @throws IOException Exception
     */
    private static void io(final boolean b, final String m) throws IOException {
        if (b) {
            throw new IOException(m);
        }
    }

    private static int readVarInt(DataInputStream in) throws IOException, TimeoutException {
        int i = 0;
        int j = 0;

        while (true) {
            AtomicInteger k = new AtomicInteger();

            var executor = Executors.newSingleThreadExecutor();
            var future = executor.submit(() -> {
                try {
                    k.set(in.readByte());
                } catch (IOException e) {
                    k.set(Integer.MAX_VALUE);
                }
            });

            try {
                future.get(3, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
                throw e;
            } catch (ExecutionException | InterruptedException e) {
                throw new IOException(e);
            } finally {
                executor.shutdownNow();
            }

            if (k.get() == Integer.MAX_VALUE) throw new IOException();

            i |= (k.get() & 0x7F) << j++ * 7;

            if (j > 5) {
                throw new IOException("VarInt too big");
            }

            if ((k.get() & 0x80) != 128) {
                break;
            }
        }

        return i;
    }

    private static void writeVarInt(DataOutputStream out, int inputParamInt) throws IOException {
        var paramInt = inputParamInt;
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }

            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }
}
