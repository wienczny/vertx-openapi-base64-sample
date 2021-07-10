package de.wienczny.vertx.sample.base64.models;

import com.google.common.base.MoreObjects;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
public class Label {

  private Buffer data;
  private String mimeType;

  public Label() {
  }

  public Label(final String barcode, final Buffer data, final String mimeType) {
    this.data = data;
    this.mimeType = mimeType;
  }

  public Label(final Label other) {
    this.data = other.getData();
    this.mimeType = other.getMimeType();
  }

  public Buffer getData() {
    return data;
  }

  @Fluent
  public Label setData(final Buffer data) {
    this.data = data;
    return this;
  }

  public String getMimeType() {
    return mimeType;
  }

  @Fluent
  public Label setMimeType(final String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  public Label(final JsonObject json) {
    LabelConverter.fromJson(json, this);
  }

  public JsonObject toJson() {
    final JsonObject json = new JsonObject();
    LabelConverter.toJson(this, json);
    return json;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("data", data)
      .add("mimeType", mimeType)
      .toString();
  }
}
