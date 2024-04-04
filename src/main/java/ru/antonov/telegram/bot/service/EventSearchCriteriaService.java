package ru.antonov.telegram.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonov.telegram.bot.exceptions.ErrorCode;
import ru.antonov.telegram.bot.exceptions.ServiceException;
import ru.antonov.telegram.bot.model.entity.EventSearchCriteria;
import ru.antonov.telegram.bot.repository.EventSearchCriteriaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventSearchCriteriaService {
    private final EventSearchCriteriaRepository eventSearchCriteriaRepository;

    @Transactional
    public void updateSearchCriteria(final Long chatId,
                                     final String searchCriteria) {
        final EventSearchCriteria eventSearchCriteria = eventSearchCriteriaRepository.findByChatId(chatId)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_999));
        eventSearchCriteria.setDate(searchCriteria);
    }

    @Transactional
    public EventSearchCriteria toggleLocationName(final Long chatId,
                                                  final String locationName) {
        final EventSearchCriteria eventSearchCriteria = eventSearchCriteriaRepository.findByChatId(chatId)
            .orElseThrow(() -> new ServiceException(ErrorCode.ERR_CODE_999));
        eventSearchCriteria.toggleLocationName(locationName);
        return eventSearchCriteria;
    }

    @Transactional
    public EventSearchCriteria saveOrUpdateEventSearchCriteria(final Long chatId) {
        final Optional<EventSearchCriteria> userDataOptional = eventSearchCriteriaRepository.findByChatId(chatId);
        if (userDataOptional.isPresent()) {
            final EventSearchCriteria userData = userDataOptional.get();
            userData.setLocationNameList(List.of());
            userData.setDate("");
            return userData;
        }
        return eventSearchCriteriaRepository.save(EventSearchCriteria.builder()
            .chatId(chatId)
            .build());
    }
}
