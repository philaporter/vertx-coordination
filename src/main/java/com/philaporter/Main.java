package com.philaporter;

import com.philaporter.verticles.ActivityVerticle;
import com.philaporter.verticles.StarterVerticle;
import io.vertx.core.Vertx;

import java.util.logging.Logger;

public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(
        ActivityVerticle.class.getName(),
        outerAsyncResult -> {
          if (outerAsyncResult.succeeded()) {
            vertx.deployVerticle(
                StarterVerticle.class.getName(),
                innerAsyncResult -> {
                  LOGGER.info("Number of verticles deployed: " + vertx.deploymentIDs().size());
                });
          } else {
            vertx.close();
          }
        });
  }
}
