package com.soebes.h.parameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.soebes.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit tests for {@link Calculator} with JUnit Jupiter (aka JUnit 5).
 */
class CalculatorTest {

  @Test
  @DisplayName("1 + 1 = 2")
  void addsTwoNumbers() {
    Calculator calculator = new Calculator();
    assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
  }

  //@formatter:off
  @ParameterizedTest(name = "{0} + {1} = {2}")
  @CsvSource({
                 "0,    1,   1",
                 "1,    2,   3",
                 "49,  51, 100",
                 "1,  100, 101"
             })
  void add(int first, int second, int expectedResult) {
    Calculator calculator = new Calculator();
    assertThat(calculator.add(first,second))
        .as("%d + %d should equal %d", first, second,expectedResult)
        .isEqualTo(expectedResult);
  }
  //@formatter:on

}
