package com.philaporter.activities;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class Activity {

  public String uuid;
  public Instant instant;
  public boolean successful;

  public void activate(Future future, JsonObject jsonObject) {
    uuid = UUID.randomUUID().toString();
    instant = Instant.now();

    //    System.out.println("Activity started at " + instant);

    int i = new Random().nextInt(6) + 1;
    // Create a random failure
    if (i == 3) {
      jsonObject.put(uuid, this.toString());
      future.fail(new Exception("random failure"));
    } else {
      successful = true;
      jsonObject.put(uuid, this.toString());
      future.complete();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Activity activity = (Activity) o;
    return successful == activity.successful
        && Objects.equals(uuid, activity.uuid)
        && Objects.equals(instant, activity.instant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, instant, successful);
  }

  @Override
  public String toString() {
    return new JsonObject()
        .put("uuid", uuid)
        .put("instant", instant)
        .put("successful", successful)
        .toString();
  }
}
