package com.philaporter.activities.wrapper;

import com.philaporter.activities.Activity;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

@RunWith(VertxUnitRunner.class)
public class ActivityWrapperTest {

  @ClassRule public static RunTestOnContext rule = new RunTestOnContext();

  @Test
  public void testActivityWrapper() {

    Activity[] activities = new Activity[6];
    Arrays.asList(activities)
        .forEach(
            v -> {
              v = new Activity();
            });
    ActivityWrapper activityWrapper = new ActivityWrapper();
    Future<JsonObject> future = activityWrapper.activateAll(activities);

    future.setHandler(
        ar -> {
          System.out.println(ar.result().encodePrettily());
        });
  }
}
