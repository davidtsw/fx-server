package com.david.fxserver.events;

public class AccountCreatedEvent extends BaseEvent<String> {

    public AccountCreatedEvent(String id) {
        super(id);
    }
}
