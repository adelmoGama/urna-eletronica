package com.adelmo.apiUrnaEletronica.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ElectoralPositionsEnum {
    PRESIDENT (1),
    GOVERNOR (2),
    MAYOR (3);

    private final Integer code;
    private ElectoralPositionsEnum(Integer code) {
        this.code = code;
    }
    public static ElectoralPositionsEnum value(Integer code) {
        for (ElectoralPositionsEnum value : ElectoralPositionsEnum.values()) {
            if(Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid electoral position code.");
    }
}
