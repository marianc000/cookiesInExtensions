package com.example.appengine.springboot;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    Cookie samesite(String val) {
        Cookie c = new Cookie(val, val);
        c.setAttribute("SameSite", val);
        c.setHttpOnly(true);
        c.setSecure(true);
        return c;
    }

    @RequestMapping(path = "/test", produces = "text/plain")
    public String hello(HttpServletRequest req, HttpServletResponse res) {

        var receivedCookies = Stream.ofNullable(req.getCookies()).flatMap(Stream::of)
                .collect(Collectors.toMap(c -> c.getName(), c -> c));

        Stream.of("Strict", "Lax", "None").forEach(s -> {
            if (!receivedCookies.containsKey(s)) {
                res.addCookie(samesite(s));
            }
        });

        return req.getMethod() + " " + req.getRequestURL()
                + "\n" + "sessionId=" + req.getSession().getId()
                + "\n" + "origin=" + req.getHeader("Origin")
                + "\n" + "cookies=" + req.getHeader("Cookie");
    }
}
