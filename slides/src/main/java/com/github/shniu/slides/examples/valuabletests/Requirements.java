package com.github.shniu.slides.examples.valuabletests;

public class Requirements {
    // 这是一个例子
    // 需求是这样的：CRM 注册模块，用户全部都存储在数据库中。现在需要实现一个 use case
    //  Use Case 1: 修改用户的邮箱
    //  但是有以下的业务规则 (business rules)
    //    1. 如果用户的邮箱属于公司，这个用户是这个公司下的雇员 (employee)；否则就是一个普通的客户 (customer)
    //    2. 系统需要跟踪公司的员工数量；如果用户的类型从雇员修改为客户，或者反过来，员工数是跟着变化的
    //    3. 当邮箱变更后，系统需要通过消息总线通知其他系统
}
