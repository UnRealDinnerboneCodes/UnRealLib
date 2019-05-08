package com.unrealdinnerbone.unreallib;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.logging.LogManager;

@Slf4j
public class LogHacker
{
    @SneakyThrows
    public static void init() {
        LogManager.getLogManager().readConfiguration(LogHacker.class.getClassLoader().getResourceAsStream("logging.properties"));
        log.info("Logging Hacked!");
    }

}
