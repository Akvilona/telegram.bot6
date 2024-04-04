/**
 * Создал Андрей Антонов 4/2/2024 3:56 PM.
 **/

package ru.antonov.telegram.bot.exceptions;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String code;

    public ServiceException(final ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
    }
}
