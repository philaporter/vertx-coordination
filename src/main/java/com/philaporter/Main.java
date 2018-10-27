package com.philaporter;

import com.philaporter.verticles.ActivityVerticle;
import com.philaporter.verticles.StarterVerticle;
import io.vertx.core.Vertx;

public class Main {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(
        ActivityVerticle.class.getName(),
        outerAsyncResult -> {
          if (outerAsyncResult.succeeded()) {
            vertx.deployVerticle(
                StarterVerticle.class.getName(),
                innerAsyncResult -> {
                  System.out.println(
                      "Number of verticles deployed: " + vertx.deploymentIDs().size());
                });
          } else {
            vertx.close();
          }
        });
  }
}
