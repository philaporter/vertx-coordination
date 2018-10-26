package com.philaporter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;
import java.util.logging.Logger;

import static com.philaporter.util.Constants.ACTIVITY_LOCATION;

public class StarterVerticle extends AbstractVerticle {

  private static final Logger LOGGER = Logger.getLogger(StarterVerticle.class.getName());

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
                        LOGGER.info(messageAsyncResult.result().body().toString());
                      });
            });
    startFuture.complete();
    LOGGER.info("Deployed " + this.getClass().getName() + " successfully");
  }
}
