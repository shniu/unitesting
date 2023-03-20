package com.github.shniu.slides.examples.valuabletests.v4;

public class CompanyFactory {

    public static Company create(Object[] data) {
        String companyDomainName = (String) data[0];
        int numberOfEmployees = (int) data[1];

        return new Company(companyDomainName, numberOfEmployees);
    }
}
