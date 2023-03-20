package com.github.shniu.slides.examples.falsepositive;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class MessageRenderer implements Renderer {

    @Getter
    private List<Renderer> subRenderers;

    public MessageRenderer() {
        subRenderers = Lists.newArrayList(
                new HeaderRenderer(),
                new BodyRenderer(),
                new FooterRenderer()
        );

//        subRenderers = Lists.newArrayList(
//                new HeaderRenderer(),
//                new BoldRenderer(),  /// Here, do refactor
//                new FooterRenderer()
//        );
    }

    @Override
    public String render(Message message) {
        if (CollectionUtils.isEmpty(subRenderers)) {
            return null;
        }

        return subRenderers.stream()
                .map(renderer -> renderer.render(message))
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
