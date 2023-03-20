package com.github.shniu.slides.examples.sharingtests;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import java.util.Set;

public class DomainWhitelist {
    private static final Set<String> whitelists = Sets.newHashSet();

    public boolean contains(String domain) {
        if (Strings.isNullOrEmpty(domain)) return false;

        return whitelists.contains(domain);
    }

    public void addWhitelist(String domain) {
        if (Strings.isNullOrEmpty(domain)) {
            return;
        }
        whitelists.add(domain);
    }
}
