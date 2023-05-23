package com.rates.account.query;

import com.rates.account.query.api.queries.FindAllCurrencyRequests;
import com.rates.account.query.api.queries.FindCurrencyRequestByIdQuery;
import com.rates.account.query.api.queries.QueryHandler;
import com.rates.core.infrastructures.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}


	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllCurrencyRequests.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindCurrencyRequestByIdQuery.class, queryHandler::handle);
	}

}
