package com.unrealdinnerbone.unreallib.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

@SuppressWarnings("unchecked cast")
public class EventManager<T> {

    private final ConcurrentHashMap<Class<? extends T>, Queue<Consumer<T>>> interactions = new ConcurrentHashMap<>();

    public <B extends T> void registerHandler(Class<B> eventClass, Consumer<B> eventConsumer) {
        if (!interactions.containsKey(eventClass)) {
            interactions.put(eventClass, new ConcurrentLinkedQueue<>());
        }
        interactions.get(eventClass).add((Consumer<T>) eventConsumer);
    }

    public <B extends T> void post(B eClass) {
        Class<B> eventClass = (Class<B>) eClass.getClass();
        if (interactions.containsKey(eClass)) {
            interactions.get(eventClass).forEach(runnable -> runnable.accept(eClass));
        }
    }

}
