package io.github.arthurhoch.purevalue.core;

public interface PureValue<T> {

    T value();

    static <V extends PureValue<?>> V of(String value) {
        throw new UnsupportedOperationException("Use the specific type's of() method");
    }

    static <V extends PureValue<?>> V ofNullable(String value) {
        throw new UnsupportedOperationException("Use the specific type's ofNullable() method");
    }

    static boolean isValid(String value) {
        throw new UnsupportedOperationException("Use the specific type's isValid() method");
    }

    static boolean isFormatted(String value) {
        throw new UnsupportedOperationException("Use the specific type's isFormatted() method");
    }

    String format();

    static String clean(String value) {
        throw new UnsupportedOperationException("Use the specific type's clean() method");
    }

    String toString();
}
