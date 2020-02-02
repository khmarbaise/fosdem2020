package com.soebes.a.junit4;

import static com.soebes.Precondition.requireGreaterThanZero;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.soebes.Precondition;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit tests for {@link Precondition} with JUnit 4
 *
 * @author Karl Heinz Marbaise
 */
public class PreconditionTest {

  @BeforeClass
  public static void beforeClass() {
    System.out.println("PreconditionTest.beforeClass");
  }

  @Before
  public void before() {
    System.out.println("PreconditionTest.before");
  }

  @Test
  public void int_requireGreaterThanZeroIAE() {
    System.out.println("PreconditionTest.int_requireGreaterThanZeroIAE");
    assertThatIllegalArgumentException().isThrownBy(
        () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"));
  }

  @Test
  public void int_requireGreaterThanZeroIAEWithGivenMessage() {
    assertThatIllegalArgumentException().isThrownBy(
        () -> Precondition.requireGreaterThanZero((Integer) null, "The Null Message"))
        .withMessage("The Null Message");
  }

  @Test
  public void int_requireGreaterThanZeroWithZeroValue() {
    assertThatIllegalArgumentException().isThrownBy(
        () -> Precondition.requireGreaterThanZero(Integer.valueOf(0), "The Zero Message"))
        .withMessage("The Zero Message");
  }


  @Test
  public void int_should_return_same_object() {
    Integer integerValue = Integer.MAX_VALUE;
    assertThat(Precondition.requireGreaterThanZero(integerValue, "The failure message"))
        .isSameAs(integerValue);
  }

  @Test
  public void requireGreaterThanZeroIAE() {
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "Null Message"));
  }

  @Test
  public void requireGreaterThanZeroIAEWithGivenMessage() {
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero((Long) null, "The given Message"))
        .withMessage("The given Message");
  }

  @Test
  public void requireGreaterThanZeroWithZeroValue() {
    assertThatIllegalArgumentException().isThrownBy(() -> requireGreaterThanZero(0L, "The Zero Message"))
        .withMessage("The Zero Message");
  }

  @Test
  public void should_return_same_object() {
    Long longValue = Long.MAX_VALUE;
    assertThat(requireGreaterThanZero(longValue, "The failure message")).isSameAs(longValue);
  }
}
