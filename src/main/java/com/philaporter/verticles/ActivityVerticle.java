package com.philaporter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.Instant;

import static com.philaporter.util.Constants.ACTIVITY_LOCATION;

public class ActivityVerticle extends AbstractVerticle {

  @Override
  public void start(Future startFuture) {
    Vertx.currentContext()
        .owner()
        .eventBus()
        .consumer(
            ACTIVITY_LOCATION,
            message -> {
              System.out.println(message.body().toString());
              message.reply(
                  ((JsonObject) message.body()).put(Instant.class.getSimpleName(), Instant.now()));
            });
    startFuture.complete();
    System.out.println("Deployed " + this.getClass().getName() + " successfully");
  }
}
