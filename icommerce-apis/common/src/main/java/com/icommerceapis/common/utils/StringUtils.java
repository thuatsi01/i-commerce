package com.icommerceapis.common.utils;

import java.util.Map;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String format(String template, Map<String, String> data) {
        if (template != null && !template.isEmpty()) {
            var buffer = new StringBuffer();
            var pattern = Pattern.compile("\\$\\{([a-zA-z0-9_]+)\\}");
            var matcher = pattern.matcher(template);

            while (matcher.find()) {
                var key = matcher.group(1);
                if (data.containsKey(key)) {
                    matcher.appendReplacement(buffer, data.get(key));
                }
            }

            matcher.appendTail(buffer);
            return buffer.toString();
        }

        return template;
    }
}
