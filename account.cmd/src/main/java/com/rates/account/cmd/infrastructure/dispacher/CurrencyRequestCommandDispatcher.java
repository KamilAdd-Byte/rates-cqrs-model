package com.rates.account.cmd.infrastructure.dispacher;

import com.rates.core.commands.BaseCommand;
import com.rates.core.commands.CommandHandlerMethod;
import com.rates.core.infrastructures.CommandDispatcher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Primary
public class CurrencyRequestCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handlerMethod) {
        List<CommandHandlerMethod> commandHandlerMethods = routes.computeIfAbsent(type, c -> new LinkedList<>());
        commandHandlerMethods.add(handlerMethod);

    }

    @Override
    public void send(BaseCommand command) {
        var commandHandlerMethods = routes.get(command.getClass());
        if(commandHandlerMethods==null || commandHandlerMethods.size()==0) {
            throw new RuntimeException("No command handler was registered");
        }
        if (commandHandlerMethods.size() > 1) {
            throw new RuntimeException();
        }
        commandHandlerMethods.get(0).handle(command);
    }
}
