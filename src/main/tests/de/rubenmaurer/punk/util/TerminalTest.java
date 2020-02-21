package de.rubenmaurer.punk.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerminalTest {

    @Test
    void printLastResponse() {
        Terminal.printLastResponse("Welcome to the Internet Relay Network chloe!elisabeth@Hostname.de\n" +
                "Your host is www.creativ.de, running version 0.0.1a\n" +
                "This server was created 2019.11.27 10:27:52\n" +
                "www.creativ.de 0.0.1a <available user modes> <available channel modes>");
    }

    @Test
    void printLastResponse2() {
        Terminal.printLastResponse("Welcome to the Internet Relay Network chloe!elisabeth@Hostname.de",
                "Your host is www.creativ.de, running version 0.0.1a",
                "This server was created 2019.11.27 10:27:52",
                "www.creativ.de 0.0.1a <available user modes> <available channel modes>");
    }
}