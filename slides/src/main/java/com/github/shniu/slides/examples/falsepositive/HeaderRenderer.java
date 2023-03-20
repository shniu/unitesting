package com.github.shniu.slides.examples.falsepositive;

public class HeaderRenderer implements Renderer {
    @Override
    public String render(Message message) {
        return "<h>" + message.getHeader() + "</h>";
    }
}
