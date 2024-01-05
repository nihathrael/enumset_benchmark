# Intro 
Benchmark to compare different ways of representing sets of {@link Enum}s in Java. This work builds on the ideas presented by
[Chris Wellons in "The cost of Java's EnumSet"](https://nullprogram.com/blog/2021/04/23/).

We compare:
* Using an integer Bitmask
* Using `EnumSet`
* Using `Set#of(Object, Object, Object)`
* Using `HashSet`

You can find the benchmark implementation in [EnumSetBenchmark.java](https://github.com/nihathrael/enumset_benchmark/blob/main/app/src/jmh/java/de/kinnen/enumset_benchmark/EnumSetBenchmark.java).

# How to run the Benchmark

You can run the benchmark using `./gradlew jmh` (Linux/Mac) or `./gradlew.bat jmh` (Windows). Gradle will also 
automatically download the necessary dependencies.

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
