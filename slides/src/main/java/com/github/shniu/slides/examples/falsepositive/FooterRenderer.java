package com.github.shniu.slides.examples.falsepositive;

public class FooterRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<f>" + message.getFooter() + "</f>";
    }
}
