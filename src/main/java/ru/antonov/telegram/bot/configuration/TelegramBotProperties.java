/**
 * Создал Андрей Антонов 3/29/2024 11:12 AM.
 **/

package ru.antonov.telegram.bot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.balievent.bot")
public class TelegramBotProperties {
    private String username;
    private String token;
}
