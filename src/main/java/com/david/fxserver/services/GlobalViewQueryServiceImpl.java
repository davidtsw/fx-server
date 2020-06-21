package com.david.fxserver.services;

import java.util.concurrent.ExecutionException;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.stereotype.Service;

import com.david.fxserver.dtos.GlobalViewDTO;
import com.david.fxserver.queries.GlobalViewQuery;

@Service
public class GlobalViewQueryServiceImpl implements GlobalViewQueryService {

    private final QueryGateway queryGateway;

    public GlobalViewQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public GlobalViewDTO currentGlobalView()
    {
    	try {
			return queryGateway.query(new GlobalViewQuery(), ResponseTypes.instanceOf(GlobalViewDTO.class)).get();
		} catch (InterruptedException | ExecutionException e) {
			//ignore
			return null;
		}
    }
}
