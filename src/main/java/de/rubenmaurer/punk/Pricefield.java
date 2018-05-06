package de.rubenmaurer.punk;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Server;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientUtils;

public class Pricefield {

    public static void main(String[] args) throws Exception {
        Session.initiate("java -jar C:\\punkIRC\\punkIRC.jar");

        if (Session.startServer()) {
            System.out.println("=== Server started ===");

            Client schrott = Client.create("Schrotty", "Schrottler", "Rodolf Schrottler");
            Client max = Client.create("max", "maxine", "Maxine Caulfield");
            Client chloe = Client.create("chloe", "chloe", "Chloe Price");
            Client rachel = Client.create("rachel", "rachel", "Rachel Amber");

            if(schrott.connect()) {
                System.out.println("== Schrotty ==");
                System.out.println(schrott.sendAndReceive("NICK schrotty\r\nUSER schrottler * * :Herman\r\n", 4));

                schrott.disconnect();
            }

            if (max.connect()) {
                System.out.println("== Max ==");
                System.out.println(max.sendAndReceive("NICK max\r\nUSER maxine * * :Maxine Caulfield\r\n", 4));

                max.disconnect();
            }

            if (chloe.connect()) {
                System.out.println("== Chloe ==");
                System.out.println(chloe.sendAndReceive(ClientUtils.auth(chloe), 4));

                chloe.disconnect();
            }

            if (rachel.connect()) {
                System.out.println("== Rachel ==");
                System.out.println(rachel.sendAndReceive(ClientUtils.auth(rachel), 4));
            }

            rachel.disconnect();

            if (Server.stop()) {
                System.out.println("=== Server stopped ===");
            }

            return;
        }

        System.out.println("=== Server not started ===");
    }
}
