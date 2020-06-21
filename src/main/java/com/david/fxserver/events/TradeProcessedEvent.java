package com.david.fxserver.events;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TradeProcessedEvent extends BaseEvent<String> {

	final String fromCurrency;
	final String toCurrency;
	final BigDecimal fromAmount;
	final BigDecimal toAmount;
	final BigDecimal rate;
	final String timePlaced;
	final String originatingCountry;
	
    public TradeProcessedEvent(String id, String fromCurrency, String toCurrency, BigDecimal fromAmount, BigDecimal toAmount,
    		BigDecimal rate, String timePlaced, String originatingCountry) {
        super(id);
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.rate = rate;
    	this.timePlaced = timePlaced;
    	this.originatingCountry = originatingCountry;
    }
}
