package io.github.pak3nuh.kotlin.playground.invokedynamic;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public final class PolymorphicSignatureExample {

    /**
     * Despite having the same signature as {@link java.lang.invoke.MethodHandle#invokeExact(Object...) invokeExact}
     * will emit different bytecode for the invoke instruction
     * because is not a PolymorphicSignature
     */
    public static Object localInvokeExact(Object... args) throws Throwable {
        return 1L;
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static void printGetTime() throws Throwable {
        var handle = MethodHandles.publicLookup()
                .findStatic(PolymorphicSignatureExample.class, "getTime",
                        MethodType.methodType(long.class));

//         Object result = handle.invokeExact(); // will not work
        long result = (long) handle.invokeExact(); // signature must match
        System.out.println(result);

        long result2 = (long) localInvokeExact();
        System.out.println(result);
    }

    public static void main(String[] args) throws Throwable {
        printGetTime();
    }
}
