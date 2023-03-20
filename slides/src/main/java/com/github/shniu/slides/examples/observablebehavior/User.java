package com.github.shniu.slides.examples.observablebehavior;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class User {
    private String uid;
    private String name;

    // 设置名称是一个可观察行为
    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    // normalizeName 应该是一个私有方法，隐藏实现细节
    public String normalizeName(String newName) {
        Preconditions.checkState(Strings.isNullOrEmpty(newName), "New name must not be null or empty.");

        String val = newName.trim();

        if (val.length() > 50) {
            return val.substring(0, 50);
        }
        return val;
    }
}
