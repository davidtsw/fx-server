package com.david.fxserver.aggregates;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.david.fxserver.commands.CreateAccountCommand;
import com.david.fxserver.commands.HouseTradeCommand;
import com.david.fxserver.commands.TradeCommand;
import com.david.fxserver.events.AccountCreatedEvent;
import com.david.fxserver.events.HouseTradeProcessedEvent;
import com.david.fxserver.events.TradeProcessedEvent;

@Aggregate
public class AccountAggregate {

	@AggregateIdentifier
    private String id;

    private Map<String, BigDecimal> accountBalance = new HashMap<>();
    
    public AccountAggregate() {}

    @CommandHandler
    protected AccountAggregate(CreateAccountCommand command){
    	
    	AggregateLifecycle.apply(new AccountCreatedEvent(command.getId()));
    }
    
    @CommandHandler
    protected void on(TradeCommand command){
    	
    	if(command.getFromAmount().compareTo(BigDecimal.ZERO) <= 0 || command.getToAmount().compareTo(BigDecimal.ZERO) <= 0)
    	{
    		throw new IllegalArgumentException("amount <= 0");
    	}
    	
    	TradeProcessedEvent userAccountEvent = new TradeProcessedEvent(command.getId(), command.getFromCurrency(),
    			command.getToCurrency(), command.getFromAmount(), command.getToAmount(), command.getRate(),
    			command.getTimePlaced(), command.getOriginatingCountry());
        AggregateLifecycle.apply(userAccountEvent);
    }
    
    @CommandHandler
    protected void on(HouseTradeCommand command){
    	
    	HouseTradeProcessedEvent houseAccountEvent = new HouseTradeProcessedEvent(command.getUserId(), command.getFromCurrency(),
    			command.getToCurrency(), command.getFromAmount(), command.getToAmount());
        AggregateLifecycle.apply(houseAccountEvent);
    }
    
    @EventSourcingHandler
    protected void on(AccountCreatedEvent event){

    	this.id = event.getId();
    }
    
    @EventSourcingHandler
    protected void on(TradeProcessedEvent event){

        BigDecimal fromCurrencyBalance = accountBalance.getOrDefault(event.getFromCurrency(), BigDecimal.ZERO);
        accountBalance.put(event.getFromCurrency(), fromCurrencyBalance.subtract(event.getFromAmount()));
        
        BigDecimal toCurrencyBalance = accountBalance.getOrDefault(event.getToCurrency(), BigDecimal.ZERO);
        accountBalance.put(event.getToCurrency(), toCurrencyBalance.add(event.getToAmount()));
    }
    
    @EventSourcingHandler
    protected void on(HouseTradeProcessedEvent event){

        BigDecimal fromCurrencyBalance = accountBalance.getOrDefault(event.getFromCurrency(), BigDecimal.ZERO);
        accountBalance.put(event.getFromCurrency(), fromCurrencyBalance.add(event.getFromAmount()));
        
        BigDecimal toCurrencyBalance = accountBalance.getOrDefault(event.getToCurrency(), BigDecimal.ZERO);
        accountBalance.put(event.getToCurrency(), toCurrencyBalance.subtract(event.getToAmount()));
    }
}
