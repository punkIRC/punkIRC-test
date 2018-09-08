package de.rubenmaurer.punk;

import de.rubenmaurer.punk.util.CLI;
import de.rubenmaurer.punk.util.Terminal;
import org.fusesource.jansi.AnsiConsole;

public class Pricefield {

    public static String ID;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        CLI.parseParameter(args);
        Terminal.printHeader();

        Settings.checkDirectoriesAndPipe();

        //Launch the tests
        TestLauncher.launch();
    }
}
