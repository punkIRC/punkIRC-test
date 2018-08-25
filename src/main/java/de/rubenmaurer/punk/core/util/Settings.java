package de.rubenmaurer.punk.core.util;

import de.rubenmaurer.punk.Pricefield;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private Properties properties = new Properties();

    private Properties internal = new Properties();

    private static Settings self = new Settings();

    public static int defaultTimeout = 3;
    public static int defaultServerStartDelay = 5;
    public static int defaultServerStopDelay = 2;
    public static int defaultServerShutdownTries = 5;
    public static int defaultExpectedLineCount = 1;
    public static String defaultResponseDelimiter = "\r\n";
    public static String logPath = "logs";
    public static String resultPath = "results";

    public static String executable = "";
    public static String hostname = "localhost";
    public static int port = 6667;

    public static boolean silent = false;
    public static boolean javaMode = true;

    private Settings() {
        String props = "config.properties";
        String inter = "build.properties";

        File f = new File("./config.properties");

        if(f.exists() && !f.isDirectory()) props = "./config.properties";

        try (InputStream input = Pricefield.class.getClassLoader().getResourceAsStream(props)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try (InputStream input = Pricefield.class.getClassLoader().getResourceAsStream(inter)) {
            internal.load(input);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String version() {
        return self.internal.getProperty("version", "1.0");
    }

    public static String build() {
        return self.internal.getProperty("buildNumber", "501");
    }
}
