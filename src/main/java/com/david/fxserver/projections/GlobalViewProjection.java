package com.david.fxserver.projections;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.david.fxserver.dtos.GlobalViewDTO;
import com.david.fxserver.events.HouseTradeProcessedEvent;
import com.david.fxserver.queries.GlobalViewQuery;

@Component
public class GlobalViewProjection {

	private Map<String, Integer> tradeByUser = new ConcurrentHashMap<>();
	private Map<String, Integer> tradeByCountry = new ConcurrentHashMap<>();
	private Map<String, BigDecimal> houseAccountBalance = new ConcurrentHashMap<>();
	
	@EventHandler
    public void on(HouseTradeProcessedEvent event) {
		Integer tradeCount = tradeByUser.getOrDefault(event.getUserId(), Integer.valueOf(0));
		tradeByUser.put(event.getUserId(), ++tradeCount);
		
		tradeCount = tradeByCountry.getOrDefault(event.getOriginatingCountry(), Integer.valueOf(0));
        tradeByCountry.put(event.getOriginatingCountry(), ++tradeCount);
        
        BigDecimal fromCurrencyBalance = houseAccountBalance.getOrDefault(event.getFromCurrency(), BigDecimal.ZERO);
        houseAccountBalance.put(event.getFromCurrency(), fromCurrencyBalance.add(event.getFromAmount()));
        
        BigDecimal toCurrencyBalance = houseAccountBalance.getOrDefault(event.getToCurrency(), BigDecimal.ZERO);
        houseAccountBalance.put(event.getToCurrency(), toCurrencyBalance.subtract(event.getToAmount()));
    }
	
	@QueryHandler
    public GlobalViewDTO fetch(GlobalViewQuery query) {
        return new GlobalViewDTO(tradeByUser, tradeByCountry, houseAccountBalance);
    }
}
