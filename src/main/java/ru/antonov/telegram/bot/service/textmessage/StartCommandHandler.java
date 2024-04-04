package ru.antonov.telegram.bot.service.textmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.antonov.telegram.bot.constant.TgBotConstants;
import ru.antonov.telegram.bot.model.entity.UserData;
import ru.antonov.telegram.bot.service.EventSearchCriteriaService;
import ru.antonov.telegram.bot.util.KeyboardUtil;

@Service
@RequiredArgsConstructor
public class StartCommandHandler extends TextMessageHandler {

    private final EventSearchCriteriaService eventSearchCriteriaService;

    @Override
    public TextMessageHandlerType getHandlerType() {
        return TextMessageHandlerType.START_COMMAND;
    }

    @Override
    public void handle(final Update update) throws TelegramApiException {
        final Long chatId = update.getMessage().getChatId();
        final UserData userData = userDataService.saveOrUpdateUserData(chatId);
        userDataService.saveUserMessageId(update.getMessage().getMessageId(), chatId);

        eventSearchCriteriaService.saveOrUpdateEventSearchCriteria(chatId);

        clearChat(chatId, userData);

        final SendMessage sendMessage = SendMessage.builder()
            .chatId(chatId)
            .text(TgBotConstants.EVENT_DATE_QUESTION.formatted())
            .replyMarkup(KeyboardUtil.createEventDateSelectionKeyboard())
            .build();

        final Message message = myTelegramBot.execute(sendMessage);
        userDataService.updateLastBotMessageId(message.getMessageId(), chatId);
    }
}
