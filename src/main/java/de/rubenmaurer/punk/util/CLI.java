package de.rubenmaurer.punk.util;

import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.test.channel.*;
import de.rubenmaurer.punk.test.connection.BasicConnection;
import de.rubenmaurer.punk.test.connection.FullConnection;
import de.rubenmaurer.punk.test.connection.MultiUserConnection;
import de.rubenmaurer.punk.test.connection.QuitConnection;
import de.rubenmaurer.punk.test.motd.Motd;
import de.rubenmaurer.punk.test.ping.Ping;
import de.rubenmaurer.punk.test.ping.Pong;
import de.rubenmaurer.punk.test.privmsg.NoticePrivmsg;
import de.rubenmaurer.punk.test.robustness.Robustness;
import de.rubenmaurer.punk.test.unknown.Unknown;
import de.rubenmaurer.punk.test.whois.Whois;

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
        String onlineVersion = Settings.getCurrentVersion();
        if (onlineVersion != null && !Settings.version().equals(onlineVersion) && Settings.versionCheck()) {
            System.out.print(Terminal.getDivider());
            System.out.print(Terminal.center(Template.get("VERSION_UPDATE_MESSAGE").render()));
            System.out.print(
                    Terminal.center(Template.get("VERSION_UPDATE")
                            .single("old", Settings.version())
                            .single("new", onlineVersion)
                            .render()
                    )
            );

            System.out.print(Terminal.getDivider());

            System.exit(42);
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

            // Select test classes
            if (command.equals("--tests") || command.equals("-t")) {
                Settings.storeTests(storeTests(argument[1]));
            }

            // Logging
            if (command.equals("--log") || command.equals("-l")) {
                Settings.storeOverride("log", "true");
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

        for (String cls : tests.split(" ")) {
            if (cls.equals("Channel")) {
                classes.add(AssigmentChannel.class);
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

            if (cls.equals("MOTD")) {
                classes.add(Motd.class);
            }

            if (cls.equals("Ping")) {
                classes.add(Ping.class);
                classes.add(Pong.class);
            }

            if (cls.equals("PrivateMsg")) {
                classes.add(NoticePrivmsg.class);
            }

            if (cls.equals("Robustness")) {
                classes.add(Robustness.class);
            }

            if (cls.equals("Unknown")) {
                classes.add(Unknown.class);
            }

            if (cls.equals("WhoIs")) {
                classes.add(Whois.class);
            }
        }

        return classes;
    }
}
