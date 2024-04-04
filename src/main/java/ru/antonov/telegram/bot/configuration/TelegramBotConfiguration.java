/**
 * Создал Андрей Антонов 3/29/2024 12:44 PM.
 **/

package ru.antonov.telegram.bot.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.antonov.telegram.bot.service.MyTelegramBot;

@Configuration
@Slf4j
public class TelegramBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(MyTelegramBot myTelegramBot) {
        try {
            TelegramBotsApi   telegramBotsApi= new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(myTelegramBot);
            return telegramBotsApi;
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            throw new IllegalStateException(e);
        }
    }
}
