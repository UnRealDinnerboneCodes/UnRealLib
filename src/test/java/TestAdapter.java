import com.squareup.moshi.*;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

public class TestAdapter extends JsonAdapter<World> {


    @Override
    @FromJson
    public World fromJson(JsonReader reader) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        Test test = moshi.adapter(Test.class).fromJson(reader);
        return new World("pre", test.x, test.y, test.z);
    }

    @Override
    @ToJson
    public void toJson(JsonWriter writer, @Nullable World value) throws IOException {
        if(value != null) {
            writer.beginObject();
            writer.name("x").value(value.x());
            writer.name("y").value(value.y());
            writer.name("z").value(value.z());
            writer.endObject();
        }else {
            writer.nullValue();
        }
    }


    public static final class Test {
        private final int x;
        private final int y;
        private final int z;

        public Test(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public int z() {
            return z;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Test) obj;
            return this.x == that.x &&
                    this.y == that.y &&
                    this.z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "Test[" +
                    "x=" + x + ", " +
                    "y=" + y + ", " +
                    "z=" + z + ']';
        }
    }
}
