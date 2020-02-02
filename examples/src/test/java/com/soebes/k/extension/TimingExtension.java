package com.soebes.k.extension;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

// Code has been taken from JUnit Jupiter Documentation
public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private static final Logger logger = Logger.getLogger(TimingExtension.class.getName());

  private static final String START_TIME = "start time";

  public void beforeTestExecution(ExtensionContext context) {
    getStore(context).put(START_TIME, System.currentTimeMillis());
  }

  public void afterTestExecution(ExtensionContext context) {
    Method testMethod = context.getRequiredTestMethod();
    long startTime = getStore(context).remove(START_TIME, long.class);
    long duration = System.currentTimeMillis() - startTime;

    logger.info(() -> String.format("Method [%s] took %s ms.", testMethod.getName(), duration));
  }

  private Store getStore(ExtensionContext context) {
    return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
  }

}