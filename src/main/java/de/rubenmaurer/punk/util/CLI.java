package de.rubenmaurer.punk.util;

import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.test.TestOverview;
import de.rubenmaurer.punk.test.channel.*;
import de.rubenmaurer.punk.test.connection.BasicConnection;
import de.rubenmaurer.punk.test.connection.FullConnection;
import de.rubenmaurer.punk.test.connection.MultiUserConnection;
import de.rubenmaurer.punk.test.connection.QuitConnection;
import de.rubenmaurer.punk.test.privmsg.NoticePrivmsg;
import de.rubenmaurer.punk.util.version.Version;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for all the command line things.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class CLI {

    /**
     * Look for a new version available.
     */
    public static void doVersionCheck() {
        Version onlineVersion = Settings.getCurrentVersion();
        if (!Settings.devMode() && onlineVersion != null && !Settings.version().equals(onlineVersion)) {
            System.out.print(Terminal.getDivider());
            System.out.print(Terminal.center(Template.get("VERSION_UPDATE_MESSAGE").render()));
            System.out.print(
                    Terminal.center(Template.get("VERSION_UPDATE")
                            .single("old", Settings.version().toString())
                            .single("new", onlineVersion.toString())
                            .render()
                    )
            );

            System.out.print(Terminal.getDivider());
            System.exit(-1);
        }
    }

    /**
     * Parses the program parameter.
     *
     * @param parameter the parameter
     */
    public static void parseParameter(String[] parameter) {
        for (String arg : parameter) {
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
            if (command.equals("--noJava") || command.equals("-nj")) {
                Settings.storeOverride("java", "false");
            }

            // Activate java mode
            if (command.equals("--java") || command.equals("-j")) {
                Settings.storeOverride("java", "true");
            }

            // Select test classes
            if (command.equals("--tests") || command.equals("-t")) {
                Settings.storeTests(storeTests(argument[1]));
            }

            // Logging
            if (command.equals("--log") || command.equals("-l")) {
                Settings.storeOverride("log", "true");
            }

            // Version Check
            /*if (command.equals("--noVersionCheck") || command.equals("-nvc")) {
                Settings.storeOverride("doVersionCheck", "false");
            }*/

            // JUnit report
            if (command.equals("--report") || command.equals("-r")) {
                Settings.storeOverride("extendedReport", "true");
            }

            // Enable debug mode
            if (command.equals("--debug")) {
                Settings.storeOverride("debug", "true");
            }

            // Enable developer mode
            if (command.equals("--dev")) {
                Settings.storeOverride("dev", "true");
            }
        }
    }

    /**
     * Saves the tests to execute.
     *
     * @param tests string with all test classes
     * @return the list of tests to execute
     */
    private static List<Class> storeTests(String tests) {
        List<Class> classes = new LinkedList<>();

        for (String cls : tests.split(" +")) {
            if (cls.equals("All")) {
                classes.addAll(TestOverview.CLASSES);
            }

            if (cls.equals("Channel")) {
                classes.add(AssignmentChannel.class);
                classes.add(JoinChannel.class);
                classes.add(ListChannel.class);
                classes.add(PartChannel.class);
                classes.add(PrivateMessageChannel.class);
                classes.add(TopicChannel.class);
                classes.add(WhoChannel.class);
            }

            if (cls.equals("Connection")) {
                classes.add(BasicConnection.class);
                classes.add(FullConnection.class);
                classes.add(MultiUserConnection.class);
                classes.add(QuitConnection.class);
            }

            if (cls.equals("PrivateMsg")) {
                classes.add(NoticePrivmsg.class);
            }

            TestOverview.CLASSES.forEach(cs -> {
                if (cs.getSimpleName().equals(cls)) {
                    classes.add(cs);
                }
            });
        }

        return classes;
    }
}
