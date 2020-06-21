package com.david.fxserver.commands;

import java.math.BigDecimal;

import com.david.fxserver.dtos.Constants;

import lombok.Getter;

@Getter
public class HouseTradeCommand extends BaseCommand<String> {

	final String userId;
	final String fromCurrency;
	final String toCurrency;
	final BigDecimal fromAmount;
	final BigDecimal toAmount;
	final String originatingCountry;
	
    public HouseTradeCommand(String userId, String fromCurrency, String toCurrency, BigDecimal fromAmount, BigDecimal toAmount, String originatingCountry) {
        super(Constants.HOUSE_ACC_ID);
        this.userId = userId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.originatingCountry = originatingCountry;
    }
}
