package com.philaporter.activities;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.UUID;
import java.util.logging.Logger;

public class Activity {

  private static final Logger LOGGER = Logger.getLogger(Activity.class.getName());

  public JsonObject activate(Future future) {
    LOGGER.info("ACTIVITY UNDERWAY...");
    future.complete();
    return new JsonObject().put("UUID", UUID.randomUUID().toString());
  }
}
