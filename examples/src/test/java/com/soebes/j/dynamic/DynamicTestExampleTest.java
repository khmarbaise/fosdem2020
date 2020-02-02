package com.soebes.j.dynamic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import com.soebes.Calculator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

class DynamicTestExampleTest {

  private final Calculator calculator = new Calculator();

  private boolean isPalindrome(String value) {
    return value.equals(new StringBuilder(value).reverse().toString());
  }

  // This will result in a JUnitException!
  @TestFactory
  List<String> dynamicTestsWithInvalidReturnType() {
    return List.of("Hello");
  }

  @TestFactory
  Collection<DynamicTest> dynamicTestsFromCollection() {
    return List.of(
        dynamicTest("1st dynamic test", () -> assertThat(isPalindrome("madam")).isTrue()),
        dynamicTest("2nd dynamic test", () -> assertThat(calculator.multiply(2, 2)).isEqualTo(4))
    );
  }

  @TestFactory
  List<DynamicTest> DynamicTestsFromIterable() {
    return List.of(
        dynamicTest("3rd dynamic test", () -> assertThat(isPalindrome("madam")).isTrue()),
        dynamicTest("4th dynamic test", () -> assertThat(calculator.multiply(2, 2)).isEqualTo(4))
    );
  }

  @TestFactory
  Iterator<DynamicTest> dynamicTestsFromIterator() {
    return Arrays.asList(
        dynamicTest("5th dynamic test", () -> assertThat(isPalindrome("madam")).isTrue()),
        dynamicTest("6th dynamic test", () -> assertThat(calculator.multiply(2, 2)).isEqualTo(4))
    ).iterator();
  }

  @TestFactory
  DynamicTest[] dynamicTestsFromArray() {
    return new DynamicTest[] {
        dynamicTest("7th dynamic test", () -> assertThat(isPalindrome("madam")).isTrue()),
        dynamicTest("8th dynamic test", () -> assertThat(calculator.multiply(2, 2)).isEqualTo(4))
    };
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromStream() {
    return Stream.of("racecar", "radar", "mom", "dad")
        .map(text -> dynamicTest(text, () -> assertThat(isPalindrome("madam")).isTrue()));
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromIntStream() {
    // Generates tests for the first 10 even integers.
    return IntStream.iterate(0, n -> n + 2)
        .limit(10)
        .mapToObj(n -> dynamicTest("test" + n, () -> assertThat(n % 2).isZero()));
  }

  @TestFactory
  Stream<DynamicTest> generateRandomNumberOfTests() {

    // Generates random positive integers between 0 and 100 until
    // a number evenly divisible by 7 is encountered.
    Iterator<Integer> inputGenerator = new Iterator<Integer>() {

      Random random = new Random();
      int current;

      @Override
      public boolean hasNext() {
        current = random.nextInt(100);
        return current % 7 != 0;
      }

      @Override
      public Integer next() {
        return current;
      }
    };

    // Generates display names like: input:5, input:37, input:85, etc.
    Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

    // Executes tests based on the current input value.
    ThrowingConsumer<Integer> testExecutor = (input) -> assertThat(input %7).isNotZero();

    // Returns a stream of dynamic tests.
    return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
  }

  @TestFactory
  Stream<DynamicNode> dynamicTestsWithContainers() {
    return Stream.of("A", "B", "C")
        .map(input -> DynamicContainer.dynamicContainer("Container " + input, Stream.of(
            dynamicTest("not null", () -> assertThat(input).isNotNull()),
            dynamicContainer("properties", Stream.of(
                dynamicTest("length > 0", () -> assertThat(input).hasSizeGreaterThan(0)),
                dynamicTest("not empty", () -> assertThat(input).isNotEmpty())
            ))
        )));
  }

  @TestFactory
  DynamicNode dynamicNodeSingleTest() {
    return dynamicTest("'pop' is a palindrome",
        () -> assertThat(isPalindrome("pop")).isTrue()
    );
  }

  @TestFactory
  DynamicNode dynamicNodeSingleContainer() {
    return dynamicContainer("palindromes",
        Stream.of("racecar", "radar", "mom", "dad")
            .map(text -> dynamicTest(text, () -> assertThat(isPalindrome(text)).isTrue())
            ));
  }
}
