package io.github.pak3nuh.kotlin.playground.invokedynamic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

/**
 * <p>Proc to see how the invokedynamic works</p>
 * <p>Use the command <code>javap -v InvokeDynamicPoc</code> nest to the class file to see the details</p>
 */
public final class InvokeDynamicPoc {

    /**
     * Writes to the output the square of a random int using only invokedynamic
     */
    void randomSquareDynamicResolution() {
        IntSupplier random = () -> ThreadLocalRandom.current().nextInt();
        IntUnaryOperator square = value -> value * value;
        IntConsumer printer = System.out::println;
        printer.accept(square.applyAsInt(random.getAsInt()));
    }

    /**
     * Writes to the output the square of a random int using all other invoke instructions
     */
    void randomSquareStaticResolution() {
        Printer printer = new SoutPrinter();
        printer.print((squareInt(randomInt())));
    }

    int randomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    static int squareInt(int value) {
        return value * value;
    }

    void printInt(int value) {
        System.out.println(value);
    }
}

interface Printer {
    void print(int value);
}

class SoutPrinter implements Printer {
    @Override
    public void print(int value) {
        System.out.println(value);
    }
}
