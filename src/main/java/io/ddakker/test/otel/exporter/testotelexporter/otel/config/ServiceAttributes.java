//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.ddakker.test.otel.exporter.testotelexporter.otel.config;

import io.opentelemetry.api.common.AttributeKey;

public final class ServiceAttributes {
    public static final AttributeKey<String> SERVICE_NAME = AttributeKey.stringKey("service.name");
    public static final AttributeKey<String> SERVICE_VERSION = AttributeKey.stringKey("service.version");

    private ServiceAttributes() {
    }
}
