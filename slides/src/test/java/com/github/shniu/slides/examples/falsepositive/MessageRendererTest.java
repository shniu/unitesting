package com.github.shniu.slides.examples.falsepositive;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class MessageRendererTest {

    /// This is a bad test.
    @Test
    void should_uses_correct_sub_renderers() {
        MessageRenderer messageRenderer = new MessageRenderer();
        List<Renderer> subRenderers = messageRenderer.getSubRenderers();

        // messageRenderer.render(null);

        assertEquals(3, subRenderers.size());
        assertInstanceOf(HeaderRenderer.class, subRenderers.get(0));
        assertInstanceOf(BodyRenderer.class, subRenderers.get(1));
        assertInstanceOf(FooterRenderer.class, subRenderers.get(2));
    }

    /// This is a good test.
    @Test
    void should_render_successful() {
        // given
        Message message = new Message();
        message.setHeader("i am header");
        message.setBody("i am body");
        message.setFooter("i am footer");

        // when
        MessageRenderer messageRenderer = new MessageRenderer();
        String content = messageRenderer.render(message);

        // then
        assertEquals("<h>i am header</h><b>i am body</b><f>i am footer</f>", content);
    }
}
