package com.philaporter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.util.logging.Logger;

import static com.philaporter.util.Constants.ACTIVITY_LOCATION;

public class ActivityVerticle extends AbstractVerticle {

  private static final Logger LOGGER = Logger.getLogger(ActivityVerticle.class.getName());

  @Override
  public void start(Future startFuture) {
    Vertx.currentContext()
        .owner()
        .eventBus()
        .consumer(
            ACTIVITY_LOCATION,
            message -> {
              LOGGER.info(message.body().toString());
              message.reply(
                  ((JsonObject) message.body()).put(Instant.class.getSimpleName(), Instant.now()));
            });
    startFuture.complete();
    LOGGER.info("Deployed " + this.getClass().getName() + " successfully");
  }
}
