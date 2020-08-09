package io.ropi.jersey.junit5;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

class JerseyTestExtensionTest {

    @Test
    void afterEachPass() throws Exception {
        JerseyTestExtension subject = new JerseyTestExtension();

        ExtensionContext context = mock(ExtensionContext.class);
        JerseyTest jerseyTest = mock(JerseyTest.class);

        when(context.getTestInstance()).thenReturn(Optional.of(jerseyTest));

        assertThatCode(() -> subject.afterEach(context)).doesNotThrowAnyException();

        verify(jerseyTest, times(1)).tearDown();
    }

    @Test
    void afterEachFail() throws Exception {
        JerseyTestExtension subject = new JerseyTestExtension();

        ExtensionContext context = mock(ExtensionContext.class);
        Object jerseyTest = mock(Object.class);

        when(context.getTestInstance()).thenReturn(Optional.of(jerseyTest));

        assertThatThrownBy(() -> subject.afterEach(context)).isInstanceOf(ClassCastException.class);
    }

    @Test
    void beforeEachPass() throws Exception {
        JerseyTestExtension subject = new JerseyTestExtension();

        ExtensionContext context = mock(ExtensionContext.class);
        JerseyTest jerseyTest = mock(JerseyTest.class);

        when(context.getTestInstance()).thenReturn(Optional.of(jerseyTest));

        assertThatCode(() -> subject.beforeEach(context)).doesNotThrowAnyException();

        verify(jerseyTest, times(1)).setUp();
    }

    @Test
    void beforeEachFail() throws Exception {
        JerseyTestExtension subject = new JerseyTestExtension();

        ExtensionContext context = mock(ExtensionContext.class);
        Object jerseyTest = mock(Object.class);

        when(context.getTestInstance()).thenReturn(Optional.of(jerseyTest));

        assertThatThrownBy(() -> subject.beforeEach(context)).isInstanceOf(ClassCastException.class);
    }
}
