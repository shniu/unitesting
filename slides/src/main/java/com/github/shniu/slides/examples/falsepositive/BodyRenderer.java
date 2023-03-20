package com.github.shniu.slides.examples.falsepositive;

public class BodyRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<b>" + message.getBody() + "</b>";
    }
}
