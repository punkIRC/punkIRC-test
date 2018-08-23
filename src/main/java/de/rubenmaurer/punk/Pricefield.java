package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.core.util.Terminal;
import de.rubenmaurer.punk.messages.Template;
import org.apache.commons.lang3.StringUtils;
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

        if (args == null || args.length == 0) {
            System.err.print(Template.get("HELP").render());
            System.exit(-1);
        }

        for (String arg : args) {
            String[] argument = arg.split("=");

            // Set the executable path for the server
            if (argument[0].equals("-exec")) {
                Settings.executable = String.format("%s", argument[1]);
            }

            // Set the server port
            if (argument[0].equals("-p")) {
                Settings.port = Integer.parseInt(argument[1]);
            }

            // Set the server hostname
            if (argument[0].equals("-h")) {
                Settings.hostname = argument[1];
            }

            // Enable silent mode
            if (argument[0].equals("-s")) {
                Settings.silent = true;
            }

            // Deactivate java mode
            if (argument[0].equals("-d")) {
                Settings.javaMode = false;
            }
        }

        if (Settings.executable.isEmpty()) {
            System.err.println(Template.get("NO_EXECUTABLE").render());
            System.exit(-1);
        }

        // Erase screen
        System.out.println(ansi()
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("Project Pricefield | [Version]: %s [Build]: %s", Settings.version(), Settings.build())))
                .render(Terminal.getDivider())
                .render(Terminal.center(String.format("ID: %s", generateID())))
                .render(Terminal.getDivider()));

        checkDirectoriesAndPipe();
        System.err.println("Hello there!");

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
            File resultDir = new File(Settings.resultPath);
            File testDir = new File(String.format("%s/%s", Settings.resultPath, Pricefield.ID));

            if (!resultDir.exists()) {
                if (!resultDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_RESULT_DIR").render());
                }
            }

            if (!testDir.exists()) {
                if (!testDir.mkdir()) {
                    throw new IOException(Template.get("UNABLE_TO_CREATE_TEST_DIR").render());
                }
            }

            System.setErr(new PrintStream(new FileOutputStream(
                    new File(String.format("%s/%s/pricefield.log", Settings.resultPath, Pricefield.ID)))));

        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
