package com.philaporter.verticles;

import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class StarterVerticleTest {

  @ClassRule public static RunTestOnContext rule = new RunTestOnContext();

  @Test
  public void testStarterVerticleTest(TestContext context) {
    rule.vertx().deployVerticle(new StarterVerticle(), context.asyncAssertSuccess());
  }
}
