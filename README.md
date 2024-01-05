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
EnumSetBenchmark.bitmaskAdd             thrpt    5  1122953845.662 ± 14487321.908  ops/s
EnumSetBenchmark.bitmaskCreation        thrpt    5  1292682130.158 ± 33722811.879  ops/s
EnumSetBenchmark.bitmaskCreationInline  thrpt    5  2640638042.506 ± 37382005.672  ops/s
EnumSetBenchmark.bitmaskEquals          thrpt    5  1582210639.358 ± 33561406.255  ops/s
EnumSetBenchmark.bitmaskEqualsInline    thrpt    5  2634237498.969 ± 37811030.834  ops/s
EnumSetBenchmark.bitmaskRemove          thrpt    5  1279302750.101 ± 22906668.356  ops/s
EnumSetBenchmark.enumSetAdd             thrpt    5   201261050.245 ±  3047021.038  ops/s
EnumSetBenchmark.enumSetCreate          thrpt    5   215090629.176 ±   827035.414  ops/s
EnumSetBenchmark.enumSetCreateInline    thrpt    5   239108143.810 ±  2845003.880  ops/s
EnumSetBenchmark.enumSetEquals          thrpt    5   586193130.841 ±  5057355.446  ops/s
EnumSetBenchmark.enumSetEqualsInline    thrpt    5   500615966.717 ±  3552721.301  ops/s
EnumSetBenchmark.enumSetRemove          thrpt    5   204125597.157 ±  1477564.086  ops/s
EnumSetBenchmark.hashSetCreate          thrpt    5    21140166.919 ±   331772.905  ops/s
EnumSetBenchmark.hashSetEquals          thrpt    5    53184863.238 ±   289455.786  ops/s
EnumSetBenchmark.setOfCreate            thrpt    5    26619186.610 ±   370502.131  ops/s
EnumSetBenchmark.setOfEquals            thrpt    5    54846987.946 ±  1022244.569  ops/s
```
