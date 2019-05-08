package com.unrealdinnerbone.unreallib.event;


import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

@Slf4j
public class EventMangaer<T> {

    private ConcurrentHashMap<Class<? extends T>, Queue<Consumer<T>>> interactions = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked cast")
    public <B extends T> void registerHandler(Class<B> eventClass, Consumer<B> eventConsumer) {
        log.debug("Registering event handler for {} with {}", eventClass.getName(), eventConsumer.getClass().getName());
        if (!interactions.containsKey(eventClass)) {
            interactions.put(eventClass, new ConcurrentLinkedQueue<>());
        }
        interactions.get(eventClass).add((Consumer<T>) eventConsumer);
    }

    public <B extends T> void post(Class<B> eventClass, B eClass) {
        if (interactions.containsKey(eventClass)) {
            Queue<Consumer<T>> interactionsToHandle = interactions.get(eventClass);
            interactionsToHandle.forEach(runnable -> runnable.accept(eClass));
        }
    }

    public void clear() {
        interactions.clear();
    }

}
