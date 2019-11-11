package de.rubenmaurer.punk;

import de.rubenmaurer.punk.util.Template;
import de.rubenmaurer.punk.util.Terminal;
import de.rubenmaurer.punk.util.version.Version;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        try (InputStream propsStream = Pricefield.class.getClassLoader().getResourceAsStream(props);
             InputStream interStream = Pricefield.class.getClassLoader().getResourceAsStream(inter);
             InputStream versiStream = Pricefield.class.getClassLoader().getResourceAsStream(versi)) {

            properties.load(propsStream);
            internal.load(interStream);
            version.load(versiStream);
        } catch (IOException e) {
            Terminal.printError(e.getMessage());
        }
    }

    /**
     * Fetch the current github release version.
     *
     * @return the current version
     */
    public static Version getCurrentVersion() {
        Version version = Version.NONE;
        StringBuilder content = new StringBuilder();

        try (CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(Settings.updateURL()))) {
            if (response.getStatusLine().getStatusCode() == 200) {
                String inputLine;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }

                version = Version.parse(new JSONObject(content.toString()).getString("tag_name"));
            }
        } catch (IOException | JSONException e) {
            Terminal.debugErro(e.getMessage());
        }

        return version;
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
     * Generate a runtime id.
     *
     * @return the id
     */
    static String generateID() {
        return String.valueOf(System.nanoTime()).substring(0, 5);
    }

    /**
     * Get the current path.
     *
     * @return the path
     */
    private static String path() {
        return new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(".")).getPath()).getAbsolutePath();
    }

    /**
     * Create all needed directories and pipe the System.err stream.
     */
    static void checkDirectoriesAndPipe() {
        try {
            Path testDir = Paths.get(String.format("%s/%s", Settings.logs(), Pricefield.runtimeID));

            if (Files.notExists(testDir)) {
                if (Files.notExists(Files.createDirectories(testDir)) ||
                    Files.notExists(Files.createDirectories(Paths.get(String.format("%s/server_logs", testDir)))) ||
                    Files.notExists(Files.createDirectories(Paths.get(String.format("%s/server_error", testDir))))) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_TEST_DIR").render());
                }
            }

            System.setErr(new PrintStream(new FileOutputStream(
                    new File(String.format("%s/%s/pricefield.log", Settings.logs(), Pricefield.runtimeID)))));

        } catch(IOException e) {
            Terminal.printError(e.getMessage());

            System.out.println(Terminal.center(Template.get("TERMINATE_MESSAGE").single("id", Pricefield.runtimeID).render()));
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
    public static Version version() {
        return Version.parse(self.version.getProperty("version", "1.0"));
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
        String key = String.format("parameter_%d", id);
        if (self.properties.containsKey(key)) {
            return self.properties.getProperty(key);
        }

        return "";
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
     * Get the doVersionCheck value.
     *
     * @return do version check?
     */
    public static boolean versionCheck() {
        String key = "doVersionCheck";

        String ovr = loadOverride(key);
        if (ovr.equals("none")) {
            if (self.properties.containsKey(key)) {
                return Boolean.parseBoolean(self.properties.getProperty(key));
            }

            return false;
        }

        return Boolean.valueOf(ovr);
    }

    /**
     * Get the URL used for version check.
     *
     * @return the updateURL
     */
    private static String updateURL() {
        return self.internal.getProperty("updateURL");
    }

    /**
     * Generate an extended junit report?
     *
     * @return generate a report?
     */
    public static boolean generateJUnitReport() {
        String ovr = loadOverride("extendedReport");
        if (!ovr.equals("none")) {
            return Boolean.parseBoolean(ovr);
        }

        return false;
    }

    /**
     * Sleep.
     */
    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception ignore) {
            // ignore exception
        }
    }
}
