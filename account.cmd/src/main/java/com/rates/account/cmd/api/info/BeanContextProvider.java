package com.rates.account.cmd.api.info;

import com.rates.account.cmd.CommandApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.logging.Logger;

@Component(value = "beanApiCounter")
public class BeanContextProvider {
    Logger logger = Logger.getLogger(String.valueOf(BeanContextProvider.class));

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    public ConfigurableApplicationContext displayBeans (String[] args) {
        applicationContext = SpringApplication.run(CommandApplication.class, args);
        logger.severe("Beginning beans counter");
        System.out.println("# BEANS: " + applicationContext.getBeanDefinitionCount());
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.asList(beanDefinitionNames).forEach(System.out::println);

        return applicationContext;
    }
}
