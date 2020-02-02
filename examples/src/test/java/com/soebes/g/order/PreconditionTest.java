package com.soebes.g.order;

import static com.soebes.Precondition.requireGreaterThanZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.soebes.Precondition;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Unit tests for {@link Precondition} with JUnit Jupiter (aka JUnit 5).
 *
 * Using {@link TestMethodOrder}.
 *
 * @author Karl Heinz Marbaise
 */
@TestMethodOrder(OrderAnnotation.class)
class PreconditionTest {

  @Test
  @Order(10)
  void order_10() {
    System.out.println("RequireGreaterThanZeroLongTests.requireGreaterThanZeroIAE");
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "Null Message"));
  }

  @Test
  @Order(20)
  void order_20() {
    System.out.println("RequireGreaterThanZeroLongTests.requireGreaterThanZeroIAEWithGivenMessage");
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "The given Message"))
        .withMessage("The given Message");
  }

  @Test
  void no_order_define_two() {
    System.out.println("RequireGreaterThanZeroLongTests.should_return_same_object");
    Long longValue = Long.MAX_VALUE;
    assertThat(requireGreaterThanZero(longValue, "The failure message")).isSameAs(longValue);
  }

  @Test
  void no_order_defined_one() {
    System.out.println("RequireGreaterThanZeroLongTests.requireGreaterThanZeroWithZeroValue");
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero(0L, "The Zero Message"))
        .withMessage("The Zero Message");
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class NestedClass {

    @Test
    @Order(10)
    void first_nested_class() {
      System.out.println("NestedClass.first_nested_class");
    }

    @Test
    @Order(5)
    void second_nested_class() {
      System.out.println("NestedClass.second_nested_class");
    }
  }
}
