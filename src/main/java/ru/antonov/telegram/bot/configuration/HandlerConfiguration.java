/**
 * Создал Андрей Антонов 4/3/2024 12:30 PM.
 **/

package ru.antonov.telegram.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.antonov.telegram.bot.service.textmessage.TextMessageHandler;
import ru.antonov.telegram.bot.service.textmessage.TextMessageHandlerType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class HandlerConfiguration {
    @Bean(name = "textMessageHandlers")
    public Map<TextMessageHandlerType, TextMessageHandler> textMessageHandlers(final List<TextMessageHandler> textMessageHandlerList) {
        return textMessageHandlerList.stream()
                .collect(Collectors.toMap(TextMessageHandler::getHandlerType, handler -> handler));
    }
}
