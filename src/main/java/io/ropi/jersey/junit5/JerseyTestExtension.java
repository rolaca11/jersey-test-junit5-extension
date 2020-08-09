package io.ropi.jersey.junit5;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Extension to enable {@link JerseyTest} to work under JUnit 5. Can only be used on classes that extend
 *
 * @author rolaca11
 */
public class JerseyTestExtension implements BeforeEachCallback, AfterEachCallback {

    public void afterEach(ExtensionContext context) throws Exception {
        getJerseyTest(context).tearDown();
    }

    public void beforeEach(ExtensionContext context) throws Exception {
        getJerseyTest(context).setUp();
    }

    private static JerseyTest getJerseyTest(ExtensionContext context) {
        return context.getTestInstance()
                .filter(object -> object instanceof JerseyTest)
                .map(JerseyTest.class::cast)
                .orElseThrow(ClassCastException::new);
    }
}
