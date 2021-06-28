package com.icommerceapis.restapis.configuration.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class RequestLogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogInterceptor.class);
    public static final String REQUEST_TIME_KEY = RequestLogInterceptor.class.getName() + ".TIME_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_TIME_KEY, LocalDateTime.now());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        var startTime = (LocalDateTime) request.getAttribute(REQUEST_TIME_KEY);
        var logListBuilder = ImmutableMap.<String, Object>builder();
        logListBuilder.put("method", request.getMethod());
        logListBuilder.put("path", request.getRequestURL().toString());
        logListBuilder.put("headers", getRequestHeaders(request));
        logListBuilder.put("status", response.getStatus());
        logListBuilder.put("exception", getException(ex));
        logListBuilder.put("request_time", startTime.toString());
        logListBuilder.put("duration", Math.abs(ChronoUnit.MILLIS.between(LocalDateTime.now(), startTime)));

        safeWriteLog(logListBuilder.build());
    }

    private String getRequestHeaders(HttpServletRequest request) {

        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headers = new LinkedList<>();

        while (headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            headers.add(String.format("%s=%s", headerName, headerValue));
        }

        return headers.isEmpty() ? "null" : String.join("\n", headers);
    }

    private String getException(Exception ex) {
        if (ex != null) {
            StringWriter writer = new StringWriter();
            try {
                ex.printStackTrace(new PrintWriter(writer));

                //Limit stacktrace 2048
                StringBuffer buffer = writer.getBuffer();
                if (buffer.length() > 2048) {
                    buffer.delete(2048, buffer.length() - 1);
                }

                var message = buffer.toString();
                writer.close();
                return message;
            } catch (IOException ignore) {
            }
        }
        return "null";
    }

    private void safeWriteLog(Object data) {
        try {
            var log = new ObjectMapper().writeValueAsString(data);
            LOGGER.info("{}", log);
        } catch (Exception ignore) {
        }
    }
}
