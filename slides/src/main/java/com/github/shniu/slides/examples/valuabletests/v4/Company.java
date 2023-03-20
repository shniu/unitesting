package com.github.shniu.slides.examples.valuabletests.v4;

import com.google.common.base.Preconditions;

public class Company {
    private String companyDomainName;
    private int numberOfEmployees;

    public Company(String companyDomainName, int numberOfEmployees) {
        this.companyDomainName = companyDomainName;
        this.numberOfEmployees = numberOfEmployees;
    }

    public boolean isEmailCorporate(String email) {
        String newEmailDomain = email.split("@")[1];
        return newEmailDomain.equals(companyDomainName);
    }

    public void changeNumberOfEmployees(int delta) {
        ////// Preconditions.check 应该被测试吗 ？
        Preconditions.checkState(this.numberOfEmployees + delta >= 0, "....");
        this.numberOfEmployees += delta;
    }

    public int getNumberOfEmployees() {
        return this.numberOfEmployees;
    }
}
