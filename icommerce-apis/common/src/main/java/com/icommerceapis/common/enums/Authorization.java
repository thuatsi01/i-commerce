package com.icommerceapis.common.enums;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum Authorization {
    USER("USER", "User's in system");

    public static final ImmutableSet<Authorization> ALL = ImmutableSet.copyOf(values());
    private static final ImmutableMap<String, Authorization> CODE_MAP = Arrays.stream(values())
            .collect(ImmutableMap.toImmutableMap(Authorization::getDatabaseValue, val -> val));

    String databaseValue;
    String description;

    public static Optional<Authorization> of(String databaseValue) {
        return Optional.ofNullable(CODE_MAP.get(databaseValue));
    }
}
