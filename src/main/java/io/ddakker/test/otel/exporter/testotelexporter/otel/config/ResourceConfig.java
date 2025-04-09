package io.ddakker.test.otel.exporter.testotelexporter.otel.config;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.sdk.resources.Resource;

public class ResourceConfig {
  public static Resource create() {
    return Resource.getDefault().toBuilder()
        .put(ServiceAttributes.SERVICE_NAME, "my-service")
        .put("traceId", "traceId111")
        .put(AttributeKey.stringKey("traceId"), "traceId222")
        .put(AttributeKey.stringKey("trace.id"), "traceId333")
        .build();
  }
}
