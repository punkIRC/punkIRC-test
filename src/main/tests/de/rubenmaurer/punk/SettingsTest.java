package de.rubenmaurer.punk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void getCurrentVersion() {
        assertNotNull(Settings.getCurrentVersion());
    }
}