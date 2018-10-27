package com.philaporter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

import static com.philaporter.util.Constants.ACTIVITY_LOCATION;

public class StarterVerticle extends AbstractVerticle {

  @Override
  public void start(Future startFuture) {

    Vertx.currentContext()
        .owner()
        .setPeriodic(
            5000,
            periodicAction -> {
              Vertx.currentContext()
                  .owner()
                  .eventBus()
                  .send(
                      ACTIVITY_LOCATION,
                      new JsonObject()
                          .put(UUID.class.getSimpleName(), UUID.randomUUID().toString()),
                      messageAsyncResult -> {
                        System.out.println(messageAsyncResult.result().body().toString());
                      });
            });
    startFuture.complete();
    System.out.println("Deployed " + this.getClass().getName() + " successfully");
  }
}
