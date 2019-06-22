package de.rubenmaurer.punk.util.version;

import de.rubenmaurer.punk.util.Template;

class VersionParseException extends RuntimeException {

    VersionParseException(String versionString, Throwable cause) {
        super(Template.get("VERSION_PARSE_EXCEPTION").single("versionString", versionString).render(), cause);
    }
}
