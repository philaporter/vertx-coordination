package com.philaporter.activities;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.logging.Logger;

@RunWith(VertxUnitRunner.class)
public class ActivityTest {

  @ClassRule public static RunTestOnContext rule = new RunTestOnContext();
  private static final Logger LOGGER = Logger.getLogger(ActivityTest.class.getName());

  // BASIC COMPOSITE FUTURE
  @Test
  public void thing(TestContext context) {
    Async async = context.async();
    Future firstFuture = Future.future();
    Future secondFuture = Future.future();

    ArrayList<JsonObject> list = new ArrayList<>();

    rule.vertx()
        .setTimer(
            2000,
            delayedAction -> {
              list.add(new Activity().activate(firstFuture));
            });

    rule.vertx()
        .setTimer(
            5000,
            delayedAction -> {
              list.add(new Activity().activate(secondFuture));
            });

    CompositeFuture.all(firstFuture, secondFuture)
        .setHandler(
            ar -> {
              if (ar.succeeded()) {
              } else {
                LOGGER.severe("FAILURE");
                async.complete();
              }
            });

    rule.vertx()
        .setTimer(
            8000,
            hkhj -> {
              System.out.println(list.size());
              list.forEach(
                  v -> {
                    LOGGER.info(v.encodePrettily());
                  });
              async.complete();
            });
  }
}
