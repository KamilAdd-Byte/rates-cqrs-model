package com.rates.account.cmd;

import com.rates.account.cmd.api.command.*;
import com.rates.account.cmd.api.handler.CommandHandler;
import com.rates.account.cmd.api.handler.CurrencyRequestCommandHandler;
import com.rates.account.cmd.api.info.BeanContextDisplayer;
import com.rates.account.cmd.controller.CurrencyRequestController;
import com.rates.account.cmd.controller.RestoreReadDbController;
import com.rates.account.cmd.domain.aggregate.CurrencyRequestAggregate;
import com.rates.account.cmd.domain.EventStoreRepository;
import com.rates.account.cmd.infrastructure.dispacher.CodesEventCommandDispatcher;
import com.rates.account.cmd.infrastructure.handler.CodesEventSourcingHandler;
import com.rates.account.cmd.infrastructure.handler.CurrencyEventSourcingHandler;
import com.rates.account.cmd.infrastructure.dispacher.CurrencyRequestCommandDispatcher;
import com.rates.account.cmd.infrastructure.producer.CurrencyEventProducer;
import com.rates.account.cmd.infrastructure.store.CurrencyRequestEventStore;
import com.rates.core.commands.CommandHandlerMethod;
import com.rates.core.domain.AggregateRoot;
import com.rates.core.handlers.EventSourcingHandler;
import com.rates.core.infrastructures.CommandDispatcher;
import com.rates.core.infrastructures.EventStore;
import com.rates.core.kafka.EventProducer;
import com.rates.currency.nbp.mapper.CurrencyWebMapper;
import com.rates.currency.nbp.mapper.impl.CurrencyWebMapperImpl;
import com.rates.currency.scrapp.cantor.service.impl.RatesCantorServiceImpl;
import com.rates.currency.scrapp.currency.service.CurrencyCodesService;
import com.rates.currency.scrapp.utils.DocumentCreator;
import com.rates.currency.service.CurrencyService;
import com.rates.currency.service.impl.CurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import javax.annotation.PostConstruct;

@ComponentScan(basePackageClasses= {
		EventSourcingHandler.class,
		CurrencyRequestEventStore.class,
		CurrencyRequestCommandDispatcher.class, CodesEventCommandDispatcher.class,
		CurrencyEventSourcingHandler.class, CodesEventSourcingHandler.class,
		CurrencyRequestCommandHandler.class, CommandHandler.class, CommandDispatcher.class,
		CurrencyRequestAggregate.class, AggregateRoot.class,
		EventProducer.class, CurrencyEventProducer.class, CommandHandlerMethod.class,
		CurrencyRequestController.class, RestoreReadDbController.class,
		EventStore.class, EventStoreRepository.class, EventProducer.class, CurrencyEventSourcingHandler.class,
		CurrencyService.class, CurrencyServiceImpl.class, CurrencyWebMapperImpl.class, CurrencyWebMapper.class,
		CurrencyWebMapperImpl.class, DocumentCreator.class, CurrencyCodesService.class, RatesCantorServiceImpl.class
})
@EnableAutoConfiguration
@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		 BeanContextDisplayer.of(SpringApplication.run(CommandApplication.class, args));
	}

	@Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(CurrencyRequestCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ExportCurrencyCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CodesCurrenciesCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);

	}
}
