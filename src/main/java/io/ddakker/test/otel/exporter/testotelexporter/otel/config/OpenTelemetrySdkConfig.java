package io.ddakker.test.otel.exporter.testotelexporter.otel.config;

import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

public class OpenTelemetrySdkConfig {
  public static OpenTelemetrySdk create() {
    Resource resource = ResourceConfig.create();

    return OpenTelemetrySdk.builder()
//        .setTracerProvider(SdkTracerProviderConfig.create(resource))
//            .setTracerProvider(SdkTracerProvider.builder().setSampler(Sampler.alwaysOn()).build())
            .setTracerProvider(SdkTracerProvider.builder()
                    .addSpanProcessor(SimpleSpanProcessor.create(LoggingSpanExporter.create()))
                    .build())
//        .setMeterProvider(SdkMeterProviderConfig.create(resource))
        .setLoggerProvider(SdkLoggerProviderConfig.create(resource))
        .setPropagators(ContextPropagatorsConfig.create())
        .build();
  }
}
