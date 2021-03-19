package com.unrealdinnerbone.unreallib.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class EventManager<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManager.class);

    private final ConcurrentHashMap<Class<? extends T>, Queue<Consumer<T>>> interactions = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked cast")
    public <B extends T> void registerHandler(Class<B> eventClass, Consumer<B> eventConsumer) {
        LOGGER.debug("Registering event handler for {} with {}", eventClass.getName(), eventConsumer.getClass().getName());
        if (!interactions.containsKey(eventClass)) {
            interactions.put(eventClass, new ConcurrentLinkedQueue<>());
        }
        interactions.get(eventClass).add((Consumer<T>) eventConsumer);
    }

    public <B extends T> void post(B eClass) {
        Class<B> eventClass = (Class<B>) eClass.getClass();
        if (interactions.containsKey(eventClass)) {
            LOGGER.debug("Posting event {}", eventClass.getName());
            interactions.get(eventClass).forEach(runnable -> runnable.accept(eClass));
        } else {
            LOGGER.error("Cant post even for {} no events listing for it", eventClass.getName());
        }
    }

    protected ConcurrentHashMap<Class<? extends T>, Queue<Consumer<T>>> getInteractions() {
        return interactions;
    }
}
