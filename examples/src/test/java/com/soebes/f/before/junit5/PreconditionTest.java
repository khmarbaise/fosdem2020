package com.soebes.f.before.junit5;

import static com.soebes.Precondition.requireGreaterThanZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.soebes.Precondition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * Unit tests for {@link Precondition} with JUnit Jupiter (aka JUnit 5).
 *
 * Using {@link Disabled}
 *
 * @author Karl Heinz Marbaise
 */
class PreconditionTest {

  @BeforeAll
  static void beforeAll() {
    System.out.println("PreconditionTest.beforeAll");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("PreconditionTest.afterAll");
  }

  @BeforeEach
  void beforeEach() {
    System.out.println("PreconditionTest.beforeEach");
  }

  @AfterEach
  void afterEach() {
    System.out.println("PreconditionTest.afterEach");
  }

  @Nested
  @DisplayName("Long tests")
  class LongTests {

    @BeforeEach
    void beforeEach() {
      System.out.println("LongTests.beforeEach");
    }

    @Test
    @DisplayName("require greater than zero should result in illegal argument exception with null parameter.")
    @Disabled("This is before cause it's currently not working.")
    void requireGreaterThanZeroIAE() {
      System.out.println("LongTests.requireGreaterThanZeroIAE");
      assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "Null Message"));
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void requireGreaterThanZeroIAEWithGivenMessage() {
      System.out.println("LongTests.requireGreaterThanZeroIAEWithGivenMessage");
      assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "The given Message"))
          .withMessage("The given Message");
    }

    @Test
    void requireGreaterThanZeroWithZeroValue() {
      System.out.println("LongTests.requireGreaterThanZeroWithZeroValue");
      assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero(0L, "The Zero Message"))
          .withMessage("The Zero Message");
    }

    @Test
    void should_return_same_object() {
      System.out.println("LongTests.should_return_same_object");
      Long longValue = Long.MAX_VALUE;
      assertThat(requireGreaterThanZero(longValue, "The failure message")).isSameAs(longValue);
    }
  }

  @Nested
  @DisplayName("Integer tests")
  @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
  class IntegerTests {

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter() {
      System.out.println(
          "IntegerTests.require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter");
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"));
    }

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter_and_the_message() {
      System.out.println(
          "IntegerTests.require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter_and_the_message");
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"))
          .withMessage("The Null Message");
    }

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_zero_parameter() {
      System.out.println(
          "IntegerTests.require_greater_than_zero_should_fail_with_illegal_argument_exception_with_zero_parameter");
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero(Integer.valueOf(0), "The Zero Message"))
          .withMessage("The Zero Message");
    }

    @Test
    void require_greater_than_zero_should_return_the_same_object() {
      System.out.println("IntegerTests.require_greater_than_zero_should_return_the_same_object");
      Integer integerValue = Integer.MAX_VALUE;
      assertThat(Precondition.requireGreaterThanZero(integerValue, "The failure message")).isSameAs(integerValue);
    }
  }
}
