package io.github.pak3nuh.kotlin.playground.basics;

import java.lang.annotation.Annotation;

/**
 * Custom implementation of the {@link Example} kotlin annotation.
 */
public final class CustomExampleImplementation {

    public static class ExampleMock implements Example {
        @Override
        public String name() {
            return "ExampleMock";
        }

        @Override
        public boolean isActive() {
            return true;
        }

        @Override
        public Class<?>[] classes() {
            return new Class[0];
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return ExampleMock.class;
        }
    }

}
