/**
 * Создал Андрей Антонов 4/2/2024 3:59 PM.
 **/

package ru.antonov.telegram.bot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ERR_CODE_001("ERR.CODE.001", "Пожалуйста, введите команду /start для начала работы с ботом"),
    ERR_CODE_999("ERR.CODE.999", "Произошла ошибка в работе бота. Пожалуйста, попробуйте позже");

    private final String code;
    private final String description;

    //TODO: Почему в исходном коде стоит три точки после Object
    // public String formatDescription(final Object... args) {
    public String formatDescription(final Object args) {
        return String.format(description, args);
    }

}
