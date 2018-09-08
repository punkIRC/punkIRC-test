package de.rubenmaurer.punk.test;

import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.junit.PricefieldExtension;
import de.rubenmaurer.punk.Settings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(PricefieldExtension.class)
public abstract class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Session.initiate(Settings.hostname(), Settings.port(), Settings.executable());

        assumeTrue(Session.isInitiated());
    }

    @AfterAll
    static void afterAll() {
        assumeTrue(Session.terminate());
    }

    @BeforeEach
    void before() {
        assumeTrue(Session.startServer());
    }

    @AfterEach
    void after() {
        assumeTrue(Session.stopServer());
    }
}
