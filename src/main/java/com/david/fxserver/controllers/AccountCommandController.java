package com.david.fxserver.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.fxserver.dtos.TradeDTO;
import com.david.fxserver.services.AccountCommandService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/account")
@Api(value = "Account Commands", tags = "Account Commands")
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    public AccountCommandController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping("/trade")
    public CompletableFuture<String> doTrade(@RequestBody TradeDTO tradeDTO){
        return accountCommandService.doTrade(tradeDTO);
    }

}
