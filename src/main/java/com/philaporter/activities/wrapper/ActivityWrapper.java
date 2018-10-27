package com.philaporter.activities.wrapper;

import com.philaporter.activities.Activity;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;

public class ActivityWrapper {

  public Future<JsonObject> activateAll(Activity[] activities) {
    ArrayList<Future> futureList = new ArrayList<>(activities.length);
    Future methodFuture = Future.future();
    JsonObject jsonObject = new JsonObject();

    // Create futures to attach to activities
    for (int i = 0; i < activities.length; i++) {
      futureList.add(Future.future());
    }

    // Fire off the activities and pass them the futures
    for (int i = 0; i < activities.length; i++) {
      new Activity().activate(futureList.get(i), jsonObject);
    }

    CompositeFuture.join(futureList)
        .setHandler(
            handler -> {
              if (handler.succeeded()) {
                // TODO: Create a success path to trigger here
                System.out.println("All activities successfully succeeded");
                methodFuture.complete(jsonObject);
                System.out.println("Trigger success path");
              } else {
                // TODO: Create a failure path to trigger here
                System.out.println("At least one activity failed");
                methodFuture.complete(jsonObject);
                System.out.println("Trigger failure path");
              }
            });

    return methodFuture;
  }
}
