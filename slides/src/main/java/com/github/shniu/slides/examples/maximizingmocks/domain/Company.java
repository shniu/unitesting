package com.github.shniu.slides.examples.maximizingmocks.domain;

import java.util.Date;

public class Company {
    // ...
    private Date lastMaintainTime;
    public void emailUpdated() {
        this.lastMaintainTime = new Date();
    }

    public int members() {
        return 0;
    }
}
