package io.ddakker.test.otel.exporter.testotelexporter.otel;

import io.ddakker.test.otel.exporter.testotelexporter.otel.config.OpenTelemetrySdkConfig;
import io.ddakker.test.otel.exporter.testotelexporter.otel.util.StackTraceUtil;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.logs.LogRecordBuilder;
import io.opentelemetry.api.logs.Logger;
import io.opentelemetry.api.logs.Severity;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

import java.time.Instant;

public class LogExporterService {

    private static OpenTelemetry otel = null;

    private static class SingletonHelper {

        private static final LogExporterService INSTANCE = new LogExporterService();
    }
    private LogExporterService() {
        this.otel = OpenTelemetrySdkConfig.create();
    }

    public static LogExporterService getInstance() {
        return SingletonHelper.INSTANCE;
    }
    public void send(String otelTraceId, String otelSpanId, String otelParentSpanId, Instant instant, String level, String message, String sourceClassName, Throwable throwable) {
        Severity severity = convertToCustomSeverity(level);
        Logger otelLogger = otel.getLogsBridge().get("test");
//        Tracer tracer = otel.getTracer("test");

//        Span span = tracer.spanBuilder("start my wonderful use case").startSpan();
//        System.out.println("span : " + span);
//        System.out.println("span getSpanContext : " + span.getSpanContext());
//        span.addEvent("Event 0");
//        span.addEvent("Event 1");
//        span.end();

        LogRecordBuilder logRecordBuilder = otelLogger.logRecordBuilder();

        logRecordBuilder.setTimestamp(instant)
                .setSeverity(severity)
                .setSeverityText(level)
                .setBody(message);

        logRecordBuilder.setAttribute(AttributeKey.stringKey("traceId"), otelTraceId);
        logRecordBuilder.setAttribute(AttributeKey.stringKey("trace_id"), otelTraceId);
        logRecordBuilder.setAttribute(AttributeKey.stringKey("trace-id"), otelTraceId);
        logRecordBuilder.setAttribute(AttributeKey.stringKey("trace.id"), otelTraceId);
        if (throwable != null) {
            logRecordBuilder.setAttribute(AttributeKey.stringKey("exception.message"), throwable.getMessage());
            logRecordBuilder.setAttribute(AttributeKey.stringKey("exception.stacktrace"), StackTraceUtil.getContentsOfStackTrace(throwable.getStackTrace()));
        }

        logRecordBuilder.emit();
        //https://opentelemetry.io/docs/specs/otel/configuration/sdk-environment-variables/#logrecord-limits
    }

    // https://github.com/open-telemetry/opentelemetry-specification/blob/main/specification/logs/data-model.md
    public Severity convertToCustomSeverity(String level) {
        switch (level) {
            case "TRACE":
            case "CONFIG":
            case "FINE":
            case "FINER":
            case "FINEST":
                return Severity.TRACE;
            case "DEBUG":
                return Severity.DEBUG;
            case "INFO":
                return Severity.INFO;
            case "WARN":
            case "WARNING":
                return Severity.WARN;
            case "ERROR":
            case "SEVERE":
                return Severity.ERROR;
            case "FATAL":
                return Severity.FATAL;
            default:
                return Severity.UNDEFINED_SEVERITY_NUMBER;
        }
    }

}
