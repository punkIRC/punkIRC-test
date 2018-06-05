package de.rubenmaurer.punk.core.util;

public enum ClientPreset {
    SCHROTTY ("schrotty", "schrottler", "Rodolf Schrottler", "schrotty@schrottler.net"),
    MAX ("max", "maxine", "Maxine Caulfield", "max@caulfield.net"),
    CHLOE ("chloe", "elisabeth", "Chloe Elisabeth Price", "chloe@price.net"),
    RACHEL ("rachel", "ramber", "Rachel Amber", "rachel@amber.net");

    private String nickname;

    public String nickname() {
        return nickname;
    }

    private String username;

    public String username() {
        return username;
    }

    private String realname;

    public String realname() {
        return realname;
    }

    private String hostname;

    public String hostname() {
        return hostname;
    }

    ClientPreset(String nickname, String username, String realname, String hostname) {
        this.nickname = nickname;
        this.realname = realname;
        this.username = username;
        this.hostname = hostname;
    }
}
