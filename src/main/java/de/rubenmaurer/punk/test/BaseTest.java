package de.rubenmaurer.punk.test;

import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.Settings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Session.initiate(Settings.hostname, Settings.port, Settings.executable);
    }

    @AfterAll
    static void afterAll() {
        Session.terminate();
    }

    @BeforeEach
    void before() {
        Session.startServer();
    }

    @AfterEach
    void after() throws Exception {
        if (!Session.stopServer()) {
            throw new Exception("MÖÖÖP!");
        }
    }
}
