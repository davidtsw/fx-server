package com.david.fxserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.fxserver.dtos.GlobalViewDTO;
import com.david.fxserver.services.GlobalViewQueryService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/global-view")
@Api(value = "Global View Queries", tags = "Global View Queries")
public class GlobalViewQueryController {

    private final GlobalViewQueryService globalViewQueryService;

    public GlobalViewQueryController(GlobalViewQueryService globalViewQueryService) {
        this.globalViewQueryService = globalViewQueryService;
    }

    @GetMapping()
    public GlobalViewDTO getGlobalView(){
        return globalViewQueryService.currentGlobalView();
    }
}
