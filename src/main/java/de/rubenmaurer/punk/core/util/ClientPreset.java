package de.rubenmaurer.punk.core.util;

public enum ClientPreset {
    SCHROTTY ("schrotty", "schrottler", "Rodolf Schrottler"),
    MAX ("max", "maxine", "Maxine Caulfield"),
    CHLOE ("chloe", "elisabeth", "Chloe Elisabeth Price"),
    RACHEL ("rachel", "ramber", "Rachel Amber");

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

    ClientPreset(String nickname, String username, String realname) {
        this.nickname = nickname;
        this.realname = realname;
        this.username = username;
    }
}
