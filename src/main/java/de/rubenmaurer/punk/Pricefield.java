package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.core.util.Terminal;
import de.rubenmaurer.punk.messages.Template;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.fusesource.jansi.Ansi.ansi;

public class Pricefield {

    public static String ID;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        for (String arg : args) {
            String[] argument = arg.split("=");
            String command = argument[0];

            // Set the executable path for the server
            if (command.equals("--executable") || command.equals("-e")) {
                Settings.storeOverride("executable", String.format("%s", argument[1]));
            }

            // Set the server port
            if (command.equals("--port") || command.equals("-p")) {
                Settings.storeOverride("port", argument[1]);
            }

            // Set the server hostname
            if (command.equals("--host") || command.equals("-h")) {
                Settings.storeOverride("hostname", argument[1]);
            }

            // Deactivate java mode
            if (command.equals("--java") || command.equals("-j")) {
                Settings.storeOverride("java", "false");
            }
        }

        // Erase screen
        System.out.println(ansi()
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("Project Pricefield | [Version]: %s | [Build]: %s", Settings.version(), Settings.build())))
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("ID: %s", generateID())))
                .render(Terminal.getDivider()));

        checkDirectoriesAndPipe();

        //Launch the tests
        TestLauncher.launch();

        System.exit(0); // temp (?!)
    }

    private static String generateID() {
        Pricefield.ID = String.valueOf(System.nanoTime()).substring(0, 5);

        return Pricefield.ID;
    }

    private static void checkDirectoriesAndPipe() {
        try {
            File resultDir = new File(Settings.results());
            File logDir = new File(Settings.logs());

            File testDir = new File(String.format("%s/%s", Settings.logs(), Pricefield.ID));
            File resDir = new File(String.format("%s/%s", Settings.results(), Pricefield.ID));

            if (!resultDir.exists()) {
                if (!resultDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_RESULT_DIR").render());
                }
            }

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

            if (!resDir.exists()) {
                if (!resDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_RESULT_DIR").render());
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
}
