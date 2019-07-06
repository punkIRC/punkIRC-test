package de.rubenmaurer.punk;

import de.rubenmaurer.punk.util.CLI;
import de.rubenmaurer.punk.util.Terminal;
import org.fusesource.jansi.AnsiConsole;

/**
 * The main entry point.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Pricefield {

    /**
     * The runtime runtimeID.
     */
    public static String runtimeID;

    /**
     * Main entry point.
     *
     * @param args start arguments
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        CLI.parseParameter(args);
        CLI.doVersionCheck();

        Terminal.printHeader();
        Settings.checkDirectoriesAndPipe();

        //Launch the tests
        TestLauncher.launch();
    }
}
