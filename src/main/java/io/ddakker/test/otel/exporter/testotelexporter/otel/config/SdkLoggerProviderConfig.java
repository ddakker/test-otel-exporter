package io.ddakker.test.otel.exporter.testotelexporter.otel.config;

import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.resources.Resource;

public class SdkLoggerProviderConfig {
  public static SdkLoggerProvider create(Resource resource) {
    return SdkLoggerProvider.builder()
        .setResource(resource)
        .addLogRecordProcessor(
//            LogRecordProcessorConfig.batchLogRecordProcessor(LogRecordExporterConfig.otlpHttpLogRecordExporter("http://localhost:8080/v1/logs")))
            LogRecordProcessorConfig.batchLogRecordProcessor(LogRecordExporterConfig.otlpHttpLogRecordExporter("http://192.168.23.65:4318/v1/logs")))
        .setLogLimits(LogLimitsConfig::logLimits)
        .build();
  }
}
