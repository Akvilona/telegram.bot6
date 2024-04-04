/**
 * Создал Андрей Антонов 4/2/2024 6:28 PM.
 **/

package ru.antonov.telegram.bot.service.textmessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessages;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.antonov.telegram.bot.model.entity.UserData;
import ru.antonov.telegram.bot.service.MyTelegramBot;
import ru.antonov.telegram.bot.service.UserDataService;

import java.util.List;

@Slf4j
@Service
public abstract class TextMessageHandler {
    @Autowired
    protected MyTelegramBot myTelegramBot;
    @Autowired
    protected UserDataService userDataService;

    public abstract TextMessageHandlerType getHandlerType();

    public abstract void handle(Update update) throws TelegramApiException;

    protected void clearChat(final Long chatId, final UserData userData) {
        final List<Integer> messageIds = userDataService.getAllMessageIdsForDelete(userData);
        if (CollectionUtils.isEmpty(messageIds)) {
            return;
        }
        try {
            myTelegramBot.execute(DeleteMessages.builder()
                    .chatId(chatId)
                    .messageIds(messageIds)
                    .build());
        } catch (TelegramApiException e) {
            log.error("Date selected message not found {}", e.getMessage());
        }
    }
}
