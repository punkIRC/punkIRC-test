package de.rubenmaurer.punk.core.util;

import de.rubenmaurer.punk.Pricefield;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {

    private Properties properties = new Properties();

    private Properties internal = new Properties();

    private static Settings self = new Settings();

    private static Map<String, String> overrides = new HashMap<>();

    private Settings() {
        String props = "resources/config.properties";
        String inter = "resources/build.properties";

        File f = new File("./config.properties");

        if(f.exists() && !f.isDirectory()) props = "./config.properties";

        try (InputStream input = Pricefield.class.getClassLoader().getResourceAsStream(props)) {
            properties.load(input);
        } catch (IOException e) {
            Terminal.printError(e.getMessage());
        }

        try (InputStream input = Pricefield.class.getClassLoader().getResourceAsStream(inter)) {
            internal.load(input);
        } catch (IOException e) {
            Terminal.printError(e.getMessage());
        }
    }

    private static String loadOverride(String key) {
        return overrides.getOrDefault(key, "none");
    }

    public static String storeOverride(String key, String value) {
        return overrides.put(key, value);
    }

    public static String version() {
        return self.internal.getProperty("version", "1.0");
    }

    public static String build() {
        return self.internal.getProperty("buildNumber", "501");
    }

    public static String delimiter() {
        return self.internal.getProperty("delimiter");
    }

    public static String logs() {
        return self.internal.getProperty("logs");
    }

    public static String results() {
        return self.internal.getProperty("results");
    }

    public static String executable() {
        String ovr = loadOverride("executable");
        if (!ovr.equals("none")) {
            return ovr;
        }

        return self.properties.getProperty("executable");
    }

    public static String hostname() {
        String ovr = loadOverride("hostname");
        if (!ovr.equals("none")) {
            return ovr;
        }

        return self.properties.getProperty("hostname");
    }

    private static String parameter(int id) {
        return self.properties.getProperty(String.format("parameter_%d", id));
    }

    public static String[] serverSetup(String server) {
        return new String[] {
                server, parameter(0), parameter(1), parameter(2)
        };
    }

    public static String[] javaServerSetup(String server) {
        return new String[] {
          "java", "-jar", server, parameter(0), parameter(1), parameter(2)
        };
    }

    public static boolean java() {
        String ovr = loadOverride("java");
        if (!ovr.equals("none")) {
            return Boolean.valueOf(ovr);
        }

        return Boolean.valueOf(self.properties.getProperty("java"));
    }

    public static int port() {
        String ovr = loadOverride("port");
        if (!ovr.equals("none")) {
            return Integer.parseInt(ovr);
        }

        return Integer.parseInt(self.properties.getProperty("port"));
    }

    public static int shutdownTries() {
        return Integer.parseInt(self.internal.getProperty("shutdownTries"));
    }

    public static int expectedLines() {
        return Integer.parseInt(self.internal.getProperty("expectedLines"));
    }

    public static int timeout() {
        return Integer.parseInt(self.properties.getProperty("timeout"));
    }

    public static int startDelay() {
        return Integer.parseInt(self.properties.getProperty("startDelay"));
    }

    public static int stopDelay() {
        return Integer.parseInt(self.properties.getProperty("stopDelay"));
    }
}
