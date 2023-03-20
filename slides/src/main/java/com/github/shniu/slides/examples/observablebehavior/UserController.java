package com.github.shniu.slides.examples.observablebehavior;

// 暴露了实现细节
public class UserController {
    // Inject.
    Database database;

    public String rename(String uid, String newName) {
        User user = database.getUserById(uid);

        // here
        String normalizeName = user.normalizeName(newName);
        user.setName(normalizeName);

        database.saveUser(user);

        return "Success";
    }

    public String rename0(String uid, String newName) {
        User user = database.getUserById(uid);

        ///// 领域逻辑泄漏
        // String normalizeName = user.normalizeName(newName);
        String val = newName.trim();
        if (val.length() > 50) {
            val = val.substring(0, 50);
        }
        user.setName(val);

        database.saveUser(user);

        return "Success";
    }

    /// 隐藏实现细节的例子
    public String renameV2(String uid, String newName) {
        UserV2 user = database.getUserByIdV2(uid);

        user.setName(newName);

        database.saveUserV2(user);

        return "Success";
    }
}
