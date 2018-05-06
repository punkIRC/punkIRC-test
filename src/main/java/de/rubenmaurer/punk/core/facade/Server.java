package de.rubenmaurer.punk.core.facade;

import de.rubenmaurer.punk.core.util.Settings;

import java.io.IOException;

public class Server {

    /**
     * Server executable path.
     */
    private String path;

    /**
     * The Server process.
     */
    private Process server;

    private static Server self;

    /**
     * Creates a new ServerManager.
     *
     * @param path the servers executable path.
     */
    private Server(String path) {
        this.path = path;
    }

    public static boolean start() {
        if (self != null) {
            try {
                self.server = Runtime.getRuntime().exec(self.path);
                Thread.sleep(Settings.defaultServerStartDelay * 1000);

                return self.server.isAlive();
            } catch (IOException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        return false;
    }

    public static boolean stop() {
        if (self != null) {
            return !self.server.destroyForcibly().isAlive();
        }

        return false;
    }

    public static boolean isAlive() {
        if (self != null) {
            return self.server.isAlive();
        }

        return false;
    }

    public static void create(String path) {
        self = new Server(path);
    }
}
