package de.wienczny.vertx.sample.base64.services;

import de.wienczny.vertx.sample.base64.models.Label;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.api.service.WebApiServiceGen;

/**
 * This interface describes the Transactions Manager Service. Note that all methods has same name of corresponding operation id
 */
@WebApiServiceGen
public interface LabelManagerService {

  static LabelManagerService create() {
    return new LabelManagerServiceImpl();
  }

  void createLabel(
    Label body,
    ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler);

}
