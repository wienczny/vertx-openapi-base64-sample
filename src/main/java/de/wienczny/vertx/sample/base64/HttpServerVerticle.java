package de.wienczny.vertx.sample.base64;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServerVerticle extends AbstractVerticle {
  /**
   * {@link Logger} for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);

  /**
   * Managed {@link HttpServer}.
   */
  private HttpServer httpServer;

  @Override
  public void start(final Promise<Void> promise) throws Exception {
    LOGGER.debug("Start HTTP Server:\n{}", config().encodePrettily());

    RouterBuilder.create(this.vertx, "sample.yaml")
      .onFailure(t -> {
        LOGGER.error("Failed creating router", t);
      }) // In case the contract loading failed print the stacktrace
      .compose(routerBuilder -> {
        // Mount services on event bus based on extensions
        routerBuilder.mountServicesFromExtensions();

        // Generate the router
        final Router router = routerBuilder.createRouter();
        router.errorHandler(400, ctx -> {
          LOGGER.debug("Bad Request", ctx.failure());
        });

        httpServer = vertx.createHttpServer(new HttpServerOptions()
          .setPort(config().getInteger("port", 8080))
          .setHost(config().getString("host", "0.0.0.0")
          )).requestHandler(router);
        return httpServer.listen().mapEmpty();
      }).onComplete(ar -> promise.complete());
  }

  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {
    if (httpServer != null) {
      httpServer.close(stopPromise);
    } else {
      stopPromise.complete();
    }
  }
}
