package com.soebes;

/**
 * Convenient utility methods for assertion of different conditions.
 *
 * @author Karl Heinz Marbaise
 */
public final class Precondition {

  private static final Long ZERO = Long.valueOf(0L);

  private static final Integer ZERO_INT = Integer.valueOf(0);

  private Precondition() {
    // no-op
  }

  /**
   * assert that the given {@code integerValue} is greater than {@code 0}.
   *
   * @param integerValue The instance which should be not {@code null} and has to be greater than {@code 0}.
   * @param message The message will be part of the exception.
   * @return The supplied object as convenient.
   */
  public static Integer requireGreaterThanZero(Integer integerValue, String message) {
    if (integerValue == null) {
      throw new IllegalArgumentException(message);
    }

    if (integerValue.compareTo(ZERO_INT) <= 0) {
      throw new IllegalArgumentException(message);
    }
    return integerValue;
  }

  /**
   * assert that the given {@code longValue} is greater than {@code 0}.
   *
   * @param longValue The instance which should be not {@code null} and has to be greater than {@code 0}.
   * @param message The message will be part of the exception.
   * @return The supplied object as convenient.
   */
  public static Long requireGreaterThanZero(Long longValue, String message) {
    if (longValue == null) {
      throw new IllegalArgumentException(message);
    }

    if (longValue.compareTo(ZERO) <= 0) {
      throw new IllegalArgumentException(message);
    }
    return longValue;
  }

}
