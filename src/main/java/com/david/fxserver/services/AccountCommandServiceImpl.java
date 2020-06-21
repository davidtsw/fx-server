package com.david.fxserver.services;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import com.david.fxserver.commands.CreateAccountCommand;
import com.david.fxserver.commands.HouseTradeCommand;
import com.david.fxserver.commands.TradeCommand;
import com.david.fxserver.dtos.Constants;
import com.david.fxserver.dtos.TradeDTO;
import com.david.fxserver.events.AccountCreatedEvent;
import com.david.fxserver.events.TradeProcessedEvent;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;
    private Set<String> accountSet = ConcurrentHashMap.newKeySet();

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> doTrade(TradeDTO dto) {
    	if(!accountSet.contains(dto.getUserId()))
    	{
    		commandGateway.send(new CreateAccountCommand(dto.getUserId()));
    	}
    	TradeCommand command = new TradeCommand(dto.getUserId(), dto.getCurrencyFrom(), dto.getCurrencyTo(), dto.getAmountSell(), dto.getAmountBuy(),
    			dto.getRate(), dto.getTimePlaced(), dto.getOriginatingCountry());
        return commandGateway.send(command);
    }
    
    @EventHandler
    public void on(TradeProcessedEvent event) {
    	if(!accountSet.contains(Constants.HOUSE_ACC_ID))
    	{
    		commandGateway.send(new CreateAccountCommand(Constants.HOUSE_ACC_ID));
    	}
    	HouseTradeCommand command = new HouseTradeCommand(event.getId(), event.getFromCurrency(), event.getToCurrency(), event.getFromAmount(), event.getToAmount());
        commandGateway.send(command);
    }

    @EventHandler
    public void on(AccountCreatedEvent event) {
    	//for simplicity, keep it in memory
    	accountSet.add(event.getId());
    }
}
