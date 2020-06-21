package com.david.fxserver.dtos;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class GlobalViewDTO {
	
	private Map<String, Integer> tradeByUser;
	private Map<String, Integer> tradeByCountry;
	private Map<String, BigDecimal> houseAccountBalance;
	
	public GlobalViewDTO(Map<String, Integer> tradeByUser, Map<String, Integer> tradeByCountry, Map<String, BigDecimal> houseAccountBalance) {
		this.tradeByUser = tradeByUser;
		this.tradeByCountry = tradeByCountry;
		this.houseAccountBalance = houseAccountBalance;
	}
}
