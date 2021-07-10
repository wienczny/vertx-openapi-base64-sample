package de.wienczny.vertx.sample.base64;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  /**
   * {@link Logger} for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start(final Promise<Void> promise) {
    vertx.deployVerticle(SampleServiceVerticle.class, new DeploymentOptions(), this::deployHandler);
    vertx.deployVerticle(HttpServerVerticle.class, new DeploymentOptions().setConfig(config().getJsonObject("http", new JsonObject())), this::deployHandler);
    promise.complete();
  }

  public void deployHandler(final AsyncResult<String> ar) {
    if (ar.succeeded()) {
      LOGGER.debug("Deployed {}", ar.result());
    } else {
      LOGGER.debug("Deploy failed", ar.cause());
    }
  }
}
