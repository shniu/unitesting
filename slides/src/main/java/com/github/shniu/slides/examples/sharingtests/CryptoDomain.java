package com.github.shniu.slides.examples.sharingtests;

import java.math.BigDecimal;

public class CryptoDomain {
    private DomainWhitelist domainWhitelist;

    public CryptoDomain(DomainWhitelist domainWhitelist) {
        this.domainWhitelist = domainWhitelist;
    }

    public boolean claim(String domain, BigDecimal price) {
        if (domainWhitelist.contains(domain)) {
            return false;
        }

        /// do claim
        // ...
        return true;
    }
}
