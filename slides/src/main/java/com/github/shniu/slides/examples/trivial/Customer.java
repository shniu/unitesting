package com.github.shniu.slides.examples.trivial;

import lombok.Getter;
import lombok.Setter;

public class Customer {
    @Getter
    @Setter
    private String cid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer vipLevel;

    // ...
}
