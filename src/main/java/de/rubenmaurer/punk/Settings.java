package de.rubenmaurer.punk;

import de.rubenmaurer.punk.util.Template;
import de.rubenmaurer.punk.util.Terminal;

import java.io.*;
import java.util.*;

public class Settings {

    private Properties properties = new Properties();

    private Properties internal = new Properties();

    private Properties version = new Properties();

    private static Settings self = new Settings();

    private static Map<String, String> overrides = new HashMap<>();

    private static List<Class> tests = new LinkedList<>();

    private Settings() {
        String props = "resources/config.properties";
        String inter = "resources/system.properties";
        String versi = "resources/version.properties";

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

        try (InputStream input = Pricefield.class.getClassLoader().getResourceAsStream(versi)) {
            version.load(input);
        } catch (IOException e) {
            Terminal.printError(e.getMessage());
        }
    }

    public static void storeTests(List<Class> tests) {
        Settings.tests.addAll(tests);
    }

    public static String generateID() {
        Pricefield.ID = String.valueOf(System.nanoTime()).substring(0, 5);

        return Pricefield.ID;
    }

    private static String path() {
        return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
    }

    static void checkDirectoriesAndPipe() {
        try {
            File logDir = new File(String.format("%s", Settings.logs()));
            File testDir = new File(String.format("%s/%s", Settings.logs(), Pricefield.ID));

            if (!logDir.exists()) {
                if (!logDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_LOG_DIR").render());
                }
            }

            if (!testDir.exists()) {
                if (!testDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_TEST_DIR").render());
                }
            }

            System.setErr(new PrintStream(new FileOutputStream(
                    new File(String.format("%s/%s/pricefield.log", Settings.logs(), Pricefield.ID)))));

        } catch(IOException e) {
            Terminal.printError(e.getMessage());

            System.out.println(Terminal.center(Template.get("TERMINATE_MESSAGE").single("id", Pricefield.ID).render()));
            System.exit(-1);
        }
    }

    private static String loadOverride(String key) {
        return overrides.getOrDefault(key, "none");
    }

    public static void storeOverride(String key, String value) {
        overrides.put(key, value);
    }

    public static String version() {
        return self.version.getProperty("version", "1.0");
    }

    public static String build() {
        return self.version.getProperty("build", "501");
    }

    public static String delimiter() {
        return self.internal.getProperty("delimiter");
    }

    public static String logs() {
        return String.format("%s/%s", path(), self.internal.getProperty("logs"));
    }

    public static String results() {
        return String.format("%s/%s", path(), self.internal.getProperty("results"));
    }

    public static String executable() {
        String ovr = loadOverride("executable");
        if (!ovr.equals("none")) {
            return ovr;
        }

        return String.format("%s/%s", path(), self.properties.getProperty("executable"));
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

    public static List<Class> tests() {
        return tests;
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

    public static boolean debug() {
        return Boolean.valueOf(loadOverride("log"));
    }

    public static int stopDelay() {
        return Integer.parseInt(self.properties.getProperty("stopDelay"));
    }

    public static int authLines() {
        return Integer.parseInt(self.properties.getProperty("authLines"));
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {

        }
    }
}
