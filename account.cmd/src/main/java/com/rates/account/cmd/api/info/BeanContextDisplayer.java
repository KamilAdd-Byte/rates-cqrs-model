package com.rates.account.cmd.api.info;

import lombok.NoArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component(value = "beanApiCounter")
@NoArgsConstructor
public class BeanContextDisplayer {
    private static Logger logger = Logger.getLogger(String.valueOf(BeanContextDisplayer.class));
    private String[] beanDefinitionNames;
    private ConfigurableApplicationContext applicationContext;

    private BeanContextDisplayer(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        displayBeans();
    }

    public static BeanContextDisplayer of (ConfigurableApplicationContext applicationContext) {
        return new BeanContextDisplayer(applicationContext);
    }

    public void displayBeans () {
        logger.log(Level.WARNING, ()-> "# BEANS: " + applicationContext.getBeanDefinitionCount());
        beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.asList(beanDefinitionNames).forEach(System.out::println);
    }
}
