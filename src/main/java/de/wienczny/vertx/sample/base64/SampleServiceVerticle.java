package de.wienczny.vertx.sample.base64;

import de.wienczny.vertx.sample.base64.services.LabelManagerService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleServiceVerticle extends AbstractVerticle {

  /**
   * {@link Logger} for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceVerticle.class);

  /**
   * Managed {@link MessageConsumer}.
   */
  private MessageConsumer<JsonObject> consumer;

  @Override
  public void start(final Promise<Void> promise) throws Exception {
    final LabelManagerService labelManagerService = LabelManagerService.create();
    consumer = new ServiceBinder(vertx)
      .setAddress("sample.base64-manager")
      .register(LabelManagerService.class, labelManagerService);

    promise.complete();
  }

  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {
    if (consumer != null) {
      consumer.unregister(stopPromise);
      consumer = null;
    } else {
      stopPromise.complete();
    }
  }
}
