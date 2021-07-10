package de.wienczny.vertx.sample.base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Base64RequestTest {
  /**
   * {@link Logger} for this class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(Base64RequestTest.class);

  @Test
  public void postJSONBase64() throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();

    final ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("data", "Kit+Cg==");
    objectNode.put("mimeType", "application/test");

    final String requestBody = objectMapper
      .writerWithDefaultPrettyPrinter()
      .writeValueAsString(objectNode);

    LOGGER.info("Request {}", requestBody);

    final URI uri = URI.create("http://localhost:8080/label");

    final HttpRequest request = HttpRequest.newBuilder(uri)
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(requestBody))
      .build();

    final HttpResponse<String> response = HttpClient.newHttpClient()
      .send(request, HttpResponse.BodyHandlers.ofString());

    LOGGER.info("Response {}: {}", response.statusCode(), response.body());
  }

  @Test
  public void postJSONBase64URL() throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();

    final ObjectNode objectNode = objectMapper.createObjectNode();
    objectNode.put("data", "Kit-Cg==");
    objectNode.put("mimeType", "application/test");

    final String requestBody = objectMapper
      .writerWithDefaultPrettyPrinter()
      .writeValueAsString(objectNode);

    LOGGER.info("Request {}", requestBody);

    final URI uri = URI.create("http://localhost:8080/label");

    final HttpRequest request = HttpRequest.newBuilder(uri)
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(requestBody))
      .build();

    final HttpResponse<String> response = HttpClient.newHttpClient()
      .send(request, HttpResponse.BodyHandlers.ofString());

    LOGGER.info("Response {}: {}", response.statusCode(), response.body());
  }
}
