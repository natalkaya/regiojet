package org.example.qa.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class ConfigReader {

    private ConfigReader() {
    }

    private static final Config config = ConfigFactory.load();

    public static ConfigReader getInstance() {
        return new ConfigReader();
    }

    public static String domainPageUrl = config.getString("ui.baseUrl");

    public static String apiBaseUrl = config.getString("api.baseUrl");

}