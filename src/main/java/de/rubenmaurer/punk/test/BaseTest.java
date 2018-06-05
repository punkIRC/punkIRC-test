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

    }

    @BeforeEach
    void before() {
        if(Session.isInitiated()) Session.startServer();
    }

    @AfterEach
    void after() {
        if(Session.isInitiated()) Session.stopServer();
    }
}
