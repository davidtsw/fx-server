package com.david.fxserver.events;

import lombok.Getter;

@Getter
public class BaseEvent<T> {

    final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
