package de.rubenmaurer.punk.util.version;

import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.util.Terminal;

public class Version {
    public static final Version NONE = new Version();

    private int major;

    private int minor;

    private int patch;

    private String prefix = "";

    private String suffix = "";

    private Version(String data) {
        try {
            tryParse(data);
        } catch (VersionParseException e) {
            Terminal.debugErro(e.getMessage());
        }
    }

    private Version() {

    }

    private Version tryParse(String data) throws VersionParseException {
        try {
            String[] values = data.split("\\.");

            if (values.length > 0) {
                if (values[0].length() > 1) {
                    prefix = values[0].substring(0, 1);
                    values[0] = values[0].substring(1);
                }

                major = Integer.parseInt(values[0]);

                if (values.length > 1) {
                    minor = Integer.parseInt(values[1]);

                    if (values.length > 2) {
                        if (values[2].contains("-")) {
                            String[] splits = values[2].split("-");

                            suffix = splits[1];
                            values[2] = splits[0];
                        }

                        patch = Integer.parseInt(values[2]);
                    }
                }
            }
        } catch (Exception e) {
            prefix = "";
            if (!Settings.devMode()) {
                throw new VersionParseException(data, e.getCause());
            }
        }

        return this;
    }

    public static Version parse(String versionString) {
        return new Version(versionString);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Version) {
            return o.toString().equals(this.toString());
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%s%s.%s.%s%s",
                prefix, major, minor, patch, suffix.isEmpty() ? "" : String.format("-%s", suffix));
    }
}
