/**
 * Создал Андрей Антонов 3/29/2024 11:00 AM.
 **/

package ru.antonov.telegram.bot.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.antonov.telegram.bot.configuration.TelegramBotProperties;
import ru.antonov.telegram.bot.exceptions.ServiceException;
import ru.antonov.telegram.bot.service.textmessage.TextMessageHandler;
import ru.antonov.telegram.bot.service.textmessage.TextMessageHandlerType;

import java.util.Map;

@Service
@Slf4j
public class MyTelegramBot extends TelegramLongPollingBot {
    private final Map<TextMessageHandlerType, TextMessageHandler> textMessageHandlers;
    private final TelegramBotProperties telegramBotProperties;

    public MyTelegramBot(
              final @Lazy Map<TextMessageHandlerType, TextMessageHandler> textMessageHandlers,
                final TelegramBotProperties telegramBotProperties) {
        super(telegramBotProperties.getToken()); // Прописываем токен
        this.textMessageHandlers = textMessageHandlers;
        this.telegramBotProperties = telegramBotProperties;
    }

    @Override
    public String getBotUsername() {
        return telegramBotProperties.getUsername(); // Прописываем название телеграм бота
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) { // все сообщения из телеграм бота
        log.info("ПРИШЛО СООБЩЕНИЕ!");
        log.info(update.getMessage().getFrom().getFirstName());
        log.info(update.getMessage().getText());

        try {
//            if (update.hasCallbackQuery()) {
//                processCallbackQuery(update);
//            } else {
                processTextMessage(update);
//            }
        } catch (ServiceException e) {
            log.error("ServiceException {}", e.getMessage(), e);
            execute(SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text(e.getMessage())
                    .build());
        }
    }

    //TODO: Зачем здесь нужно наследоваться от -> throws TelegramApiException
    private void processTextMessage(final Update update) throws TelegramApiException {
        final String messageText = update.getMessage().getText();
        if (messageText.contains("/start")) {
            textMessageHandlers.get(TextMessageHandlerType.START_COMMAND).handle(update);

        }
    }
}
