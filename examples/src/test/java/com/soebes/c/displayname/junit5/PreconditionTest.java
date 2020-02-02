package com.soebes.c.displayname.junit5;

import static com.soebes.Precondition.requireGreaterThanZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.soebes.Precondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Precondition} with
 * JUnit Jupiter (aka JUnit 5).
 *
 * Using {@link DisplayName} and {@link DisplayNameGeneration}.
 *
 * @author Karl Heinz Marbaise
 */
@DisplayName("Precondition for")
class PreconditionTest {

  @Nested
  @DisplayName("longs")
  class RequireGreaterThanZeroLongTests {

    @Test
    @DisplayName("require greater than zero should result in illegal argument exception with null parameter.")
    void requireGreaterThanZeroIAE() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> requireGreaterThanZero((Long) null, "Null Message"));
    }

    @Test
    void requireGreaterThanZeroIAEWithGivenMessage() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> requireGreaterThanZero((Long) null, "The given Message")).withMessage("The given Message");
    }

    @Test
    void requireGreaterThanZeroWithZeroValue() {
      assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero(0L, "The Zero Message"))
          .withMessage("The Zero Message");
    }

    @Test
    void should_return_same_object() {
      Long longValue = Long.MAX_VALUE;
      assertThat(requireGreaterThanZero(longValue, "The failure message")).isSameAs(longValue);
    }
  }

  @Nested
  @DisplayName("integers")
  @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
  class RequireGreaterThanZeroIntegerTests {

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"));
    }

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_null_parameter_and_the_message() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"))
          .withMessage("The Null Message");
    }

    @Test
    void require_greater_than_zero_should_fail_with_illegal_argument_exception_with_zero_parameter() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero(Integer.valueOf(0), "The Zero Message"))
          .withMessage("The Zero Message");
    }

    @Test
    void require_greater_than_zero_should_return_the_same_object() {
      Integer integerValue = Integer.MAX_VALUE;
      assertThat(Precondition.requireGreaterThanZero(integerValue, "The failure message")).isSameAs(integerValue);
    }
  }
}
