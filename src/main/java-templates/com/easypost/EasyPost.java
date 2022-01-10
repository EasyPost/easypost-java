package com.easypost;

public abstract class EasyPost {
    @SuppressWarnings("checkstyle:variablename")
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static String API_BASE = "https://api.easypost.com/v2";
    @SuppressWarnings("checkstyle:variablename")
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static final String VERSION = "${project.version}";
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static String apiKey;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static String apiVersion;
    @SuppressWarnings("checkstyle:visibilitymodifier")
    public static int readTimeout;
}
