package com.github.shniu.slides.examples.maximizingmocks;

import com.github.shniu.slides.examples.maximizingmocks.domain.Company;

public class CompanyFactory {
    public static Company create(Object[] companyData) {
        return new Company();
    }
}
