package com.soebes.a.junit5;

import static com.soebes.Precondition.requireGreaterThanZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.soebes.Precondition;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Precondition} with
 * JUnit Jupiter (aka JUnit 5).
 *
 * @author Karl Heinz Marbaise
 */
class PreconditionTest {

    @Test
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

    @Test
    void int_requireGreaterThanZeroIAE() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"));
    }

    @Test
    void int_requireGreaterThanZeroIAEWithGivenMessage() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"))
          .withMessage("The Null Message");
    }

    @Test
    void int_requireGreaterThanZeroWithZeroValue() {
      assertThatIllegalArgumentException().isThrownBy(
          () -> Precondition.requireGreaterThanZero(Integer.valueOf(0), "The Zero Message"))
          .withMessage("The Zero Message");
    }

    @Test
    void int_should_return_same_object() {
      Integer integerValue = Integer.MAX_VALUE;
      assertThat(Precondition.requireGreaterThanZero(integerValue, "The failure message")).isSameAs(integerValue);
    }
}
