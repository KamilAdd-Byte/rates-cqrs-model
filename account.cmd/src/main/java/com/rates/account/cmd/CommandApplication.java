package com.rates.account.cmd;

import com.rates.account.cmd.api.command.CommandHandler;
import com.rates.account.cmd.infrastructure.CurrencyEventSourcingHandler;
import com.rates.account.cmd.infrastructure.CurrencyRequestCommandDispatcher;
import com.rates.core.handlers.EventSourcingHandler;
import com.rates.core.infrastructures.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackageClasses= {
		EventSourcingHandler.class,
		CurrencyRequestCommandDispatcher.class,
		CurrencyEventSourcingHandler.class
})
@EnableAutoConfiguration
public class CommandApplication {

	@Autowired
	@Qualifier(value = "currencyRequestCommandDispatcher")
	private CommandDispatcher commandDispatcher;

	@Qualifier(value = "commandCurrencyHandler")
	private CommandHandler commandHandler;


	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

	@PostConstruct
	public void registerHandlers() {
//		commandDispatcher.registerHandler(OpenCurrencyRequestCommand.class, commandHandler::handle);
//		commandDispatcher.registerHandler(ExportCurrencyCommand.class, commandHandler::handle);
//		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}
}
