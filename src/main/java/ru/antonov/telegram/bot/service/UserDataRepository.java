/**
 * Создал Андрей Антонов 4/3/2024 3:36 PM.
 **/

package ru.antonov.telegram.bot.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonov.telegram.bot.model.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
