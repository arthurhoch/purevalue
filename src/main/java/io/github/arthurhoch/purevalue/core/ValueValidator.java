package io.github.arthurhoch.purevalue.core;

public interface ValueValidator {

    boolean isValid(String value);

    default boolean isBlankOrNull(String value) {
        return value == null || value.isBlank();
    }

    default String clean(String value) {
        return value == null ? null : value.replaceAll("\\s+", "").trim();
    }
}
