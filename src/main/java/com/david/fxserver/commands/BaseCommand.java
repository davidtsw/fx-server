package com.david.fxserver.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import lombok.Getter;

@Getter
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    final T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
