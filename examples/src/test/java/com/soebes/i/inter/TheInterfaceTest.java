package com.soebes.i.inter;

import static org.assertj.core.api.Assertions.assertThat;

import com.soebes.TheInterface;
import org.junit.jupiter.api.Test;

class TheInterfaceTest implements TheInterface {

  @Test
  void first() {
    assertThat(thisIsADefaultMethod()).isEqualTo("XX");
  }
}
