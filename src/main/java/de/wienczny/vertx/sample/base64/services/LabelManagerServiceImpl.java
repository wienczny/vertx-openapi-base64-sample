package de.wienczny.vertx.sample.base64.services;

import de.wienczny.vertx.sample.base64.models.Label;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabelManagerServiceImpl implements LabelManagerService {
  /**
   * {@link Logger} for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LabelManagerServiceImpl.class);

  public LabelManagerServiceImpl() {
  }

  @Override
  public void createLabel(
    final Label body,
    final ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler) {

    LOGGER.debug("createLabel Body: {}", body);

    resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(body.toJson())));
  }
}
