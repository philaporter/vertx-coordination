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

@RunWith(VertxUnitRunner.class)
public class ActivityTest {

  @ClassRule public static RunTestOnContext rule = new RunTestOnContext();

  // BASIC COMPOSITE FUTURE
  @Test
  public void thing(TestContext context) {
    Async async = context.async();
    ArrayList<Future> futureList = new ArrayList<>(6);
    JsonObject jsonObject = new JsonObject();
    int size = 6;

    // Create futures to attach to activities
    for (int i = 0; i < size; i++) {
      futureList.add(Future.future());
    }

    // Fire off the activities and pass them the futures
    for (int i = 0; i < size; i++) {
      new Activity().activate(futureList.get(i), jsonObject);
    }

    // Setup handler for watching the futures
    CompositeFuture.join(futureList)
        .setHandler(
            handler -> {
              if (handler.succeeded()) {
                // TODO: Create a success path to trigger here
                System.out.println("All activities successfully succeeded");
              } else {
                // TODO: Create a failure path to trigger here
                System.out.println("At least one activity failed");
              }
            });

    rule.vertx()
        .setTimer(
            1,
            delayedAction -> {
              System.out.println(jsonObject.encodePrettily());
              async.complete();
            });
  }
}
