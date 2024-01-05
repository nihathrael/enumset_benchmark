# Intro 
Benchmark to compare different ways of representing sets of {@link Enum}s in Java. This work builds on the ideas
<a href="https://nullprogram.com/blog/2021/04/23/">Chris Wellons in "The cost of Java's EnumSet"</a>

We compare:
* Using an integer Bitmask
* Using `EnumSet`
* Using `Set#of(Object, Object, Object)`
* Using `HashSet`

# How to run the Benchmark

You can run the benchmark using `gradlew jmh`.

# Results

These are the results on my machine (Windows 11, AMD Ryzen 7 2700X with 8 cores, 16GB RAM, SSD):

```
Benchmark                                         Mode  Cnt           Score   Error  Units
EnumSetBenchmark.benchmarkBitmaskCreation        thrpt    2  1298680375.982          ops/s
EnumSetBenchmark.benchmarkBitmaskCreationInline  thrpt    2  2598894568.390          ops/s
EnumSetBenchmark.benchmarkBitmaskEquals          thrpt    2  1577106765.317          ops/s
EnumSetBenchmark.benchmarkBitmaskEqualsInline    thrpt    2  2613609064.838          ops/s
EnumSetBenchmark.benchmarkEnumSetCreate          thrpt    2   208029293.900          ops/s
EnumSetBenchmark.benchmarkEnumSetCreateInline    thrpt    2   223747278.743          ops/s
EnumSetBenchmark.benchmarkEnumSetEquals          thrpt    2   582076757.320          ops/s
EnumSetBenchmark.benchmarkEnumSetEqualsInline    thrpt    2   496808984.154          ops/s
EnumSetBenchmark.benchmarkHashSetCreate          thrpt    2    19542178.720          ops/s
EnumSetBenchmark.benchmarkHashSetEquals          thrpt    2    55482402.525          ops/s
EnumSetBenchmark.benchmarkSetOfCreate            thrpt    2    26231241.949          ops/s
EnumSetBenchmark.benchmarkSetOfEquals            thrpt    2    39374454.123          ops/s
```

)
