/**
 * Создал Андрей Антонов 4/3/2024 3:57 PM.
 **/

package ru.antonov.telegram.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonov.telegram.bot.model.entity.EventSearchCriteria;

import java.util.Optional;

public interface EventSearchCriteriaRepository extends JpaRepository<EventSearchCriteria, Long> {
    Optional<EventSearchCriteria> findByChatId(Long chatId);
}
