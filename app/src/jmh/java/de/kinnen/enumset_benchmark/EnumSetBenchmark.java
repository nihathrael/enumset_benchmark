package de.kinnen.enumset_benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;

/**
 * <p>Benchmark to compare different ways of representing sets of {@link Enum}s in Java. This work builds on the ideas
 * by <a href="https://nullprogram.com/blog/2021/04/23/">Chris Wellons in "The cost of Java's EnumSet"</a></p>
 * <br>
 * <p>We compare:
 *     <ul>
 *         <li>Using an integer Bitmask</li>
 *         <li>Using {@link EnumSet}</li>
 *         <li>Using {@link Set#of(Object, Object, Object)}</li>
 *         <li>Using {@link HashSet}</li>
 *     </ul>
 * </p>
 */
@State(Scope.Benchmark)
public class EnumSetBenchmark {

    public enum Flag {A, B, C, D, E, F}

    /**
     * State object to avoid constant inlining by the JVM.
     */
    @State(Scope.Thread)
    public static class FlagState {
        public Flag a = Flag.A;
        public Flag b = Flag.B;
        public Flag g = Flag.E;
    }

    /**
     * State object to avoid constant inlining by the JVM.
     */
    @State(Scope.Thread)
    public static class SetOfState {
        public Set<Flag> setOfA = Set.of(Flag.A, Flag.B, Flag.E);
        public Set<Flag> setOfB = Set.of(Flag.C, Flag.D, Flag.F);
    }


    @Benchmark
    public void benchmarkSetOfEquals(SetOfState state, Blackhole bh) {
        bh.consume(state.setOfA.equals(state.setOfB));
    }

    @Benchmark
    public void benchmarkSetOfCreate(FlagState state, Blackhole bh) {
        Set<Flag> setOfA = Set.of(state.a, state.b, state.g);
        bh.consume(setOfA);
    }

    @State(Scope.Thread)
    public static class HastSetState {
        public Set<Flag> hashSetA = new HashSet<>(Arrays.asList(Flag.A, Flag.B, Flag.E));
        public Set<Flag> hastSetB = new HashSet<>(Arrays.asList(Flag.C, Flag.D, Flag.F));
    }


    @Benchmark
    public void benchmarkHashSetEquals(HastSetState state, Blackhole bh) {
        bh.consume(state.hashSetA.equals(state.hastSetB));
    }

    @Benchmark
    public void benchmarkHashSetCreate(FlagState state, Blackhole bh) {
        Set<Flag> hashSetA = new HashSet<>(Arrays.asList(state.a, state.b, state.g));
        bh.consume(hashSetA);
    }

    /**
     * State object to avoid constant inlining by the JVM.
     */
    @State(Scope.Thread)
    public static class EnumSetState {
        public Set<Flag> enumSetA = EnumSet.of(Flag.A, Flag.B, Flag.E);
        public Set<Flag> enumSetB = EnumSet.of(Flag.C, Flag.D, Flag.F);
    }

    @Benchmark
    public void benchmarkEnumSetEquals(EnumSetState state, Blackhole bh) {
        bh.consume(state.enumSetA.equals(state.enumSetB));
    }

    /**
     * Benchmark to see if the jvm will be able inline the creation of the {@link EnumSet}s,
     * even though we consume the result of the equals call.
     */
    @Benchmark
    public void benchmarkEnumSetEqualsInline(Blackhole bh) {
        Set<Flag> enumSetA = EnumSet.of(Flag.A, Flag.B, Flag.E);
        Set<Flag> enumSetB = EnumSet.of(Flag.C, Flag.D, Flag.F);
        bh.consume(enumSetA.equals(enumSetB));
    }

    @Benchmark
    public void benchmarkEnumSetCreate(FlagState state, Blackhole bh) {
        Set<Flag> hashSetA = EnumSet.of(state.a, state.b, state.g);
        bh.consume(hashSetA);
    }

    /**
     * Benchmark to see if the jvm will be able inline the creation of the {@link EnumSet},
     * even though we still consume it.
     */
    @Benchmark
    public void benchmarkEnumSetCreateInline(Blackhole bh) {
        Set<Flag> hashSetA = EnumSet.of(Flag.A, Flag.B, Flag.E);
        bh.consume(hashSetA);
    }

    static final int A = 1;
    static final int B = 1 << 1;
    static final int C = 1 << 2;
    static final int D = 1 << 3;
    static final int E = 1 << 4;
    static final int F = 1 << 5;


    /**
     * State object to avoid constant inlining by the JVM.
     */
    @State(Scope.Thread)
    public static class BitMaskState {
        public int a = A | B | E;
        public int b = C | D | F;

        // these are just redirections to avoid constant inlining
        public int valueOne = A;
        public int valueTwo = B;
        public int valueThree = E;
    }

    @Benchmark
    public void benchmarkBitmaskCreation(BitMaskState state, Blackhole bh) {
        int a = state.valueOne | state.valueTwo | state.valueThree;
        bh.consume(a);
    }

    /**
     * Benchmark to demonstrate that the jvm is very good at inlining constants like this.
     * This should be much faster than the non-inlined version.
     */
    @Benchmark
    public void benchmarkBitmaskCreationInline(Blackhole bh) {
        int a = A | B | E;
        bh.consume(a);
    }


    @Benchmark
    public void benchmarkBitmaskEquals(BitMaskState state, Blackhole bh) {
        bh.consume(state.a == state.b);
    }


    /**
     * Benchmark to demonstrate that the JVM is very good at inlining constants like this.
     * This should be much faster than the non-inlined version.
     */
    @Benchmark
    public void benchmarkBitmaskEqualsInline(Blackhole bh) {
        int a = A | B | E;
        int b = C | D | F;
        //noinspection ConstantValue - The point of this demonstration is to show that the JVM will be able to calculate the constant
        bh.consume(a == b);
    }
}