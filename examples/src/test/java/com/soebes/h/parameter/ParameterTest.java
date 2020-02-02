package com.soebes.h.parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParameterTest {

  @ParameterizedTest
  @MethodSource
  void testWithExplicitLocalMethodSource(String argument) {
    assertThat(argument).isNotNull();
  }

  static Stream<String> testWithExplicitLocalMethodSource() {
    return Stream.of("apple", "banana");
  }

  @ParameterizedTest
  @MethodSource("stringIntAndListProvider")
  void testWithMultiArgMethodSource(String str, int num, List<String> list) {
    assertThat(str).hasSize(5);
    assertThat(num).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(2);
    assertThat(list).hasSize(2);
  }

  static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        arguments("apple", 1, List.of("a", "b")),
        arguments("lemon", 2, List.of("x", "y"))
    );
  }
}
