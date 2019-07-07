package de.rubenmaurer.punk.util;

import de.rubenmaurer.punk.util.version.Version;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    void parseValidVersions() {
        // complete from everything to only major, minor and patch
        assertEquals("v1.2.1-dev", Version.parse("v1.2.1-dev").toString());
        assertEquals("v1.2.1", Version.parse("v1.2.1").toString());
        assertEquals("1.2.1", Version.parse("1.2.1").toString());

        assertEquals("1.0.0", Version.parse("1").toString());
        assertEquals("1.2.0", Version.parse("1.2").toString());
        assertEquals("1.2.1", Version.parse("1.2.1").toString());
    }

    @Test
    void parseInvalidVersions() {
        assertEquals(Version.NONE, Version.parse("Hello there!"));
    }
}