/**
 * Создал Андрей Антонов 4/3/2024 3:52 PM.
 **/

package ru.antonov.telegram.bot.service;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonov.telegram.bot.model.entity.EventSearchCriteria;
import ru.antonov.telegram.bot.repository.EventSearchCriteriaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventSearchCriteriaService {
    private final EventSearchCriteriaRepository eventSearchCriteriaRepository;

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
