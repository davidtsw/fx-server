package com.david.fxserver.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TradeDTO {
	String userId;
	String currencyFrom;
	String currencyTo;
	BigDecimal amountSell;
	BigDecimal amountBuy;
	BigDecimal rate;
	String timePlaced; //use String for simplicity
	String originatingCountry;
}
