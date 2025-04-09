package io.ddakker.test.otel.exporter.testotelexporter.otel.config;

import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;

public class SdkTracerProviderConfig {
  public static SdkTracerProvider create(Resource resource) {
    return SdkTracerProvider.builder()
        .setResource(resource)
        /*.addSpanProcessor(
            SpanProcessorConfig.batchSpanProcessor(
                SpanExporterConfig.otlpHttpSpanExporter("http://192.168.23.65:4318/v1/spans")))
        .setSampler(SamplerConfig.parentBasedSampler(SamplerConfig.traceIdRatioBased(.25)))
        .setSpanLimits(SpanLimitsConfig::spanLimits)*/
        .build();
  }
}
