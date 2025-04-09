package io.ddakker.test.otel.exporter.testotelexporter;

import io.ddakker.test.otel.exporter.testotelexporter.otel.LogExporterService;

import java.time.Instant;

public class TestExporter {
    public static void main(String[] args) {
        System.out.println("start");
        LogExporterService.getInstance().send("ac9ac80fd7ab426fb87f0e0b9acb29ad", "ac9ac80fd7ab426fb87f0e0b9acb29ad", null, Instant.now(), "INFO", "error~~~~~~~~~", "test", null);
        System.out.println("end");
    }
}
