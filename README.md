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
Benchmark                                         Mode  Cnt           Score          Error  Units
EnumSetBenchmark.benchmarkBitmaskAdd             thrpt    5  1115140405.688 ± 35250256.893  ops/s
EnumSetBenchmark.benchmarkBitmaskCreation        thrpt    5  1285201874.153 ±  9067144.888  ops/s
EnumSetBenchmark.benchmarkBitmaskCreationInline  thrpt    5  2615157193.879 ± 37822678.250  ops/s
EnumSetBenchmark.benchmarkBitmaskEquals          thrpt    5  1552854571.531 ± 67324741.854  ops/s
EnumSetBenchmark.benchmarkBitmaskEqualsInline    thrpt    5  2592155157.618 ± 81168350.007  ops/s
EnumSetBenchmark.benchmarkBitmaskRemove          thrpt    5  1299631242.484 ± 37262398.635  ops/s
EnumSetBenchmark.benchmarkEnumSetAdd             thrpt    5   192580988.636 ± 14345228.680  ops/s
EnumSetBenchmark.benchmarkEnumSetCreate          thrpt    5   195539058.507 ± 18609297.465  ops/s
EnumSetBenchmark.benchmarkEnumSetCreateInline    thrpt    5   214634406.147 ± 14063755.759  ops/s
EnumSetBenchmark.benchmarkEnumSetEquals          thrpt    5   578690429.129 ± 13834330.806  ops/s
EnumSetBenchmark.benchmarkEnumSetEqualsInline    thrpt    5   487304000.628 ± 11186639.817  ops/s
EnumSetBenchmark.benchmarkEnumSetRemove          thrpt    5   199864415.903 ± 10351114.126  ops/s
EnumSetBenchmark.benchmarkHashSetCreate          thrpt    5    18082928.924 ±  5850528.529  ops/s
EnumSetBenchmark.benchmarkHashSetEquals          thrpt    5    50955118.352 ±   311342.533  ops/s
EnumSetBenchmark.benchmarkSetOfCreate            thrpt    5    25196759.860 ±  1703847.053  ops/s
EnumSetBenchmark.benchmarkSetOfEquals            thrpt    5    98488312.932 ±  3050828.656  ops/s
```
