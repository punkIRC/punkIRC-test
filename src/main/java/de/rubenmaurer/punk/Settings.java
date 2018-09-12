package de.rubenmaurer.punk;

import de.rubenmaurer.punk.util.Template;
import de.rubenmaurer.punk.util.Terminal;

import java.io.*;
import java.util.*;

/**
 * Class for accessing various settings.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Settings {

    /**
     * Properties set by user.
     */
    private Properties properties = new Properties();

    /**
     * Properties set by dev.
     */
    private Properties internal = new Properties();

    /**
     * Properties for version.
     */
    private Properties version = new Properties();

    /**
     * Settings singleton.
     */
    private static Settings self = new Settings();

    /**
     * Collection of settings overrides.
     */
    private static Map<String, String> overrides = new HashMap<>();

    /**
     * List of tests to execute.
     */
    private static List<Class> tests = new LinkedList<>();

    /**
     * Create new settings and init. everything needed.
     */
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

    /**
     * Add a test to the list of tests.
     *
     * @param tests the test to add
     */
    public static void storeTests(List<Class> tests) {
        Settings.tests.addAll(tests);
    }

    /**
     * Generate a runtiome id.
     *
     * @return the id
     */
    public static String generateID() {
        Pricefield.ID = String.valueOf(System.nanoTime()).substring(0, 5);

        return Pricefield.ID;
    }

    /**
     * Get the current path.
     *
     * @return the path
     */
    private static String path() {
        return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
    }

    /**
     * Create all needed directories and pipe the System.err stream.
     */
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

    /**
     * Load an override.
     *
     * @param key the override key
     * @return the override value
     */
    private static String loadOverride(String key) {
        return overrides.getOrDefault(key, "none");
    }

    /**
     * Store an override.
     *
     * @param key the override key
     * @param value the override value
     */
    public static void storeOverride(String key, String value) {
        overrides.put(key, value);
    }

    /**
     * Get the version.
     *
     * @return the version
     */
    public static String version() {
        return self.version.getProperty("version", "1.0");
    }

    /**
     * Get the build number.
     *
     * @return the build number
     */
    public static String build() {
        return self.version.getProperty("build", "501");
    }

    /**
     * Get the message delimiter.
     *
     * @return the delimiter.
     */
    public static String delimiter() {
        return self.internal.getProperty("delimiter");
    }

    /**
     * Get the logs path.
     *
     * @return the logs path
     */
    public static String logs() {
        return String.format("%s/%s", path(), self.internal.getProperty("logs"));
    }

    /**
     * Get the executable path.
     *
     * @return the paath
     */
    public static String executable() {
        String ovr = loadOverride("executable");
        if (!ovr.equals("none")) {
            return ovr;
        }

        return String.format("%s/%s", path(), self.properties.getProperty("executable"));
    }

    /**
     * Get the hostname.
     *
     * @return the hostname
     */
    public static String hostname() {
        String ovr = loadOverride("hostname");
        if (!ovr.equals("none")) {
            return ovr;
        }

        return self.properties.getProperty("hostname");
    }

    /**
     * Load a start parameter.
     *
     * @param id parameter id
     * @return the parameter
     */
    private static String parameter(int id) {
        return self.properties.getProperty(String.format("parameter_%d", id));
    }

    /**
     * Create a server setup for a non-java server.
     *
     * @param server the server
     * @return the setup
     */
    public static String[] serverSetup(String server) {
        return new String[] {
                server, parameter(0), parameter(1), parameter(2)
        };
    }
    /**
     * Create a server setup for a java server.
     *
     * @param server the server
     * @return the setup
     */
    public static String[] javaServerSetup(String server) {
        return new String[] {
          "java", "-jar", server, parameter(0), parameter(1), parameter(2)
        };
    }

    /**
     * Get the list of tests to execute.
     *
     * @return the tests
     */
    public static List<Class> tests() {
        return tests;
    }

    /**
     * Use java mode?
     *
     * @return the use java mode?
     */
    public static boolean java() {
        String ovr = loadOverride("java");
        if (!ovr.equals("none")) {
            return Boolean.valueOf(ovr);
        }

        return Boolean.valueOf(self.properties.getProperty("java"));
    }

    /**
     * Get the port.
     *
     * @return the port
     */
    public static int port() {
        String ovr = loadOverride("port");
        if (!ovr.equals("none")) {
            return Integer.parseInt(ovr);
        }

        return Integer.parseInt(self.properties.getProperty("port"));
    }

    /**
     * Get the max amount of shutdown tries.
     *
     * @return the amount of shutdown tries
     */
    public static int shutdownTries() {
        return Integer.parseInt(self.internal.getProperty("shutdownTries"));
    }

    /**
     * Get the amount of expected lines.
     *
     * @return the expected lines
     */
    public static int expectedLines() {
        return Integer.parseInt(self.internal.getProperty("expectedLines"));
    }

    /**
     * Get the timeout.
     *
     * @return the timeout
     */
    public static int timeout() {
        return Integer.parseInt(self.properties.getProperty("timeout"));
    }

    /**
     * Get the start delay.
     *
     * @return the start delay
     */
    public static int startDelay() {
        return Integer.parseInt(self.properties.getProperty("startDelay"));
    }

    /**
     * Get the debug value.
     *
     * @return is debug mode?
     */
    public static boolean debug() {
        return Boolean.valueOf(loadOverride("log"));
    }

    /**
     * Get the stop delay.
     *
     * @return the stop delay
     */
    public static int stopDelay() {
        return Integer.parseInt(self.properties.getProperty("stopDelay"));
    }

    /**
     * Get the amount of expected auth lines.
     *
     * @return the expected line count
     */
    public static int authLines() {
        return Integer.parseInt(self.properties.getProperty("authLines"));
    }

    /**
     * Sleep.
     */
    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {

        }
    }
}
