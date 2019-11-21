package de.rubenmaurer.punk.util.version;

import de.rubenmaurer.punk.util.Terminal;

public class Build {

    public static final Build NONE = new Build();

    private int build;

    private Build() {
        build = 0;
    }

    private Build(String data) {
        try {
            tryParse(data);
        } catch (VersionParseException e) {
            Terminal.debugErro(e.getMessage());
        }
    }

    private void tryParse(String data) {
        try {
            build = Integer.parseInt(data);
        } catch (Exception e) {
            throw new VersionParseException(data, e.getCause());
        }
    }

    public static Build parse(String buildString) {
        return new Build(buildString);
    }

    @Override
    public String toString() {
        return Integer.toString(build);
    }
}
