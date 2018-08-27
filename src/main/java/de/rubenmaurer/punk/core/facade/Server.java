package de.rubenmaurer.punk.core.facade;

import de.rubenmaurer.punk.Pricefield;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.core.util.Terminal;
import de.rubenmaurer.punk.messages.Template;

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

                if (!Settings.java()) {
                    pb = new ProcessBuilder(self.path);
                }

                pb.redirectOutput(ProcessBuilder.Redirect.appendTo(
                        new File(String.format("%s/%s/server_log.log", Settings.logs(), Pricefield.ID))));

                pb.redirectError(ProcessBuilder.Redirect.appendTo(
                        new File(String.format("%s/%s/server_error.log", Settings.logs(), Pricefield.ID))));

                self.server = pb.start();
                Thread.sleep(Settings.startDelay() * 1000);

                return self.server.isAlive();
            } catch (IOException | InterruptedException e) {
                Terminal.printError(e.getMessage());
                System.out.println(Terminal.center(Template.get("TERMINATE_MESSAGE").single("id", Pricefield.ID).render()));

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
                    Thread.sleep(Settings.stopDelay() * 1000);

                    if (self.shutdownTries < Settings.shutdownTries()) {
                        self.server.destroy();
                        continue;
                    }

                    self.server = self.server.destroyForcibly();
                }

                return !self.server.isAlive();
            } catch(InterruptedException e) {
                Terminal.printError(e.getMessage());
                System.out.println(Terminal.center(Template.get("TERMINATE_MESSAGE").single("id", Pricefield.ID).render()));

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
