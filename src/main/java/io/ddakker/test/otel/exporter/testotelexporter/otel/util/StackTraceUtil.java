package io.ddakker.test.otel.exporter.testotelexporter.otel.util;

public class StackTraceUtil {

    public static String getContentsOfStackTrace(final StackTraceElement[] stackTrace) {
        StringBuffer sb = new StringBuffer();
        int index = 0;

        for (StackTraceElement element : stackTrace) {
            sb.append("\n");
            sb.append("[" + index++ + "]\t\t");
            sb.append(" " + element.getClassName() + ".");
            sb.append(" " + element.getMethodName() + " ");
            sb.append("(" + element.getFileName() + ":");
            sb.append("" + element.getLineNumber() + ")");
        }

        return sb.toString();
    }

    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable;
        while(cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }
}
