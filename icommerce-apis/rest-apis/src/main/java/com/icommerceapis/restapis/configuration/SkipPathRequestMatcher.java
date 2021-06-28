package com.icommerceapis.restapis.configuration;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SkipPathRequestMatcher implements RequestMatcher {
    private final OrRequestMatcher matchers;

    public SkipPathRequestMatcher(String[] ignorePaths) {
        List<RequestMatcher> m = new ArrayList<>();
        for (String ignorePath : ignorePaths) {
            m.add(new AntPathRequestMatcher(ignorePath));
        }
        matchers = new OrRequestMatcher(m);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return !matchers.matches(request);
    }
}