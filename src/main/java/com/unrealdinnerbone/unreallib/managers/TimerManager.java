package com.unrealdinnerbone.unreallib.managers;

import com.unrealdinnerbone.unreallib.ReflectionHelper;
import com.unrealdinnerbone.unreallib.TaskScheduler;
import com.unrealdinnerbone.unreallib.api.ITimerEvent;
import com.unrealdinnerbone.unreallib.api.TimerEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimerManager
{
    public static void init() {
        ReflectionHelper.scan(ITimerEvent.class, TimerEvent.class).forEach(pair -> TaskScheduler.scheduleRepeatingTask(pair.getB().value(), pair.getB().timeUnit(), pair.getA()::onTimerEvent));
    }
}
