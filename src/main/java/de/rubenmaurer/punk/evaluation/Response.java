package de.rubenmaurer.punk.evaluation;

public enum Response {
    NONE (0),
    WELCOME (001),
    YOURHOST (002),
    CREATED (003),
    MY_INFO (004),
    LUSER_CLIENT (251),
    LUSER_OP (252),
    LUSER_UNKNOWN (253),
    LUSER_CHANNEL (254),
    LUSER_ME (255),
    WHO_IS_USER (311),
    WHO_IS_SERVER (312),
    END_OF_WHO_IS (318),
    MOTD (372),
    MOTD_START (375),
    END_OF_MOTD (376),
    NO_SUCH_NICK (401),
    CANNOT_SEND_TO_CHANNEL (404),
    UNKNOWN_COMMAND (421),
    NO_MOTD (422);

    public final int value;

    Response(int code) {
        value = code;
    }
}
