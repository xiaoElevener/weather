package com.xiao.weather.common.security;

public class SecurityContextHolder {

    private static ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();

    public static void initContext(SecurityContext securityContext) {
        contextHolder.set(securityContext);
    }

    public static SecurityContext getContext() {
        return contextHolder.get();
    }

    public static void clearContext() {
        contextHolder.set(null);
    }

    public static void removeContext() {
        contextHolder.remove();
    }
}
