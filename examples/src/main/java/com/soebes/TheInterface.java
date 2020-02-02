package com.soebes;

public interface TheInterface {

  default String thisIsADefaultMethod() {
    return "XX";
  }
}
