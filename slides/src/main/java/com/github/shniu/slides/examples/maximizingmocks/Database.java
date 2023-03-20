package com.github.shniu.slides.examples.maximizingmocks;

import com.github.shniu.slides.examples.maximizingmocks.domain.Company;
import com.github.shniu.slides.examples.maximizingmocks.domain.User;

public class Database {
    private String conn;
    public Database(String connection) {
        this.conn = connection;
        // create the database connection.
    }

    public Object[] getUserById(int userId) {
        return new Object[]{};
    }

    public Object[] getCompany(int companyId) {
        return new Object[]{};
    }

    public void saveUser(User user) {
        // ...
        // Executing the SQL...
    }

    public void saveCompany(Company company) {
        // ...
        // Executing the SQL...
    }
}
