package com.david.fxserver.services;

import java.util.List;

public interface AccountQueryService {

	public List<Object> listEventsForAccount(String accountId);
}
