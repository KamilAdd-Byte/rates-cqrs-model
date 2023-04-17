package com.rates.account.cmd;

import com.rates.account.cmd.api.command.*;
import com.rates.account.cmd.api.info.BeanContextDisplayer;
import com.rates.account.cmd.domain.CurrencyRequestAggregate;
import com.rates.account.cmd.infrastructure.CurrencyEventSourcingHandler;
import com.rates.account.cmd.infrastructure.CurrencyRequestCommandDispatcher;
import com.rates.account.cmd.infrastructure.CurrencyRequestEventStore;
import com.rates.core.handlers.EventSourcingHandler;
import com.rates.core.infrastructures.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import javax.annotation.PostConstruct;


//@ComponentScan(basePackages = {"com.rates.core.*","com.rates.core.infrastructures"})
@ComponentScan(basePackageClasses= {
		EventSourcingHandler.class,
		CurrencyRequestEventStore.class,
		CurrencyRequestCommandDispatcher.class,
		CurrencyEventSourcingHandler.class,
		CurrencyRequestCommandHandler.class, CommandHandler.class, CommandDispatcher.class,
		CurrencyRequestAggregate.class
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
		commandDispatcher.registerHandler(OpenCurrencyRequestCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(ExportCurrencyCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}
}
