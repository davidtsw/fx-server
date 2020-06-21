package com.david.fxserver.services;

import java.util.concurrent.CompletableFuture;

import com.david.fxserver.dtos.TradeDTO;

public interface AccountCommandService {

	public CompletableFuture<String> doTrade(TradeDTO tradeDTO);
}
