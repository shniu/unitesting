package com.github.shniu.slides.examples.observablebehavior;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class UserV2 {
    private String uid;
    private String name;

    // 设置名称是一个可观察行为
    public void setName(String newName) {
        Preconditions.checkState(!Strings.isNullOrEmpty(newName), "New name must not be null or empty.");

        // 业务规则最好放在领域对象中实现，将业务内聚在一起
        this.name = normalizeName(newName);
    }

    public String getName() {
        return this.name;
    }

    // normalizeName 应该是一个私有方法，隐藏实现细节
    private String normalizeName(String newName) {
        String val = newName.trim();
        if (val.length() > 50) {
            return val.substring(0, 50).trim();
        }

        return val;
    }
}
