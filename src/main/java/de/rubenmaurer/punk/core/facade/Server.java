package de.rubenmaurer.punk.core.facade;

import de.rubenmaurer.punk.Pricefield;
import de.rubenmaurer.punk.core.util.Settings;

import java.io.File;
import java.io.IOException;

/**
 * Class for representing an irc server.
 *
 * @author Ruben Maurer
 */
public class Server {

    /**
     * Server executable path.
     */
    private String path;

    private String[] args;

    /**
     * The Server process.
     */
    private Process server;

    /**
     * The amount of tries to shut the server down.
     */
    private int shutdownTries = 1;

    /**
     * The constant self.
     */
    private static Server self;

    /**
     * Creates a new ServerManager.
     *
     * @param path the servers executable path.
     */
    private Server(String path) {
        this.path = path;
    }

    /**
     * Start boolean.
     *
     * @return the boolean
     */
    static boolean start() {
        if (self != null) {
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "-jar", self.path);

                if (!Settings.javaMode) {
                    pb = new ProcessBuilder(self.path);
                }

                pb.redirectOutput(ProcessBuilder.Redirect.appendTo(
                        new File(String.format("%s/%s/server_log.log", Settings.logPath, Pricefield.ID))));

                pb.redirectError(ProcessBuilder.Redirect.appendTo(
                        new File(String.format("%s/%s/server_error.log", Settings.logPath, Pricefield.ID))));

                self.server = pb.start();
                Thread.sleep(Settings.defaultServerStartDelay * 1000);

                return self.server.isAlive();
            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }

        return false;
    }

    /**
     * Try to stop the server
     *
     * @return server has stopped?
     */
    static boolean stop() {
        if (self != null) {
            try {
                self.server.destroy();

                while (self.server.isAlive()) {
                    Thread.sleep(Settings.defaultServerStopDelay * 1000);

                    if (self.shutdownTries < Settings.defaultServerShutdownTries) {
                        self.server.destroy();
                        continue;
                    }

                    self.server = self.server.destroyForcibly();
                }

                return !self.server.isAlive();
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }

        return false;
    }

    /**
     * Is server alive?
     *
     * @return server alive?
     */
    static boolean isAlive() {
        if (self != null) {
            return self.server.isAlive();
        }

        return false;
    }

    /**
     * Create a new server.
     *
     * @param path the executables path
     */
    public static void create(String path) {
        self = new Server(path);
    }
}
