package com.github.shniu.slides.examples.sharingtests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CryptoDomainTest {
    DomainWhitelist whitelist = new DomainWhitelist();

//    @Test
//    void should_claim_successful_when_domain_not_in_whitelist() {
//        whitelist.addWhitelist("abc.nft");
//        CryptoDomain sut = new CryptoDomain(whitelist);
//
//        boolean claimed = sut.claim("abc.nft", BigDecimal.ONE);
//
//        assertFalse(claimed);
//    }

    @Test
    void should_claim_successful_when_domain_not_in_whitelist0() {
        CryptoDomain sut = new CryptoDomain(whitelist);

        boolean claimed = sut.claim("abc.nft", BigDecimal.ONE);

        assertTrue(claimed);
    }

    @Test
    void should_claim_failure_when_domain_in_whitelist() {
        whitelist.addWhitelist("abc.nft");
        CryptoDomain sut = new CryptoDomain(whitelist);

        boolean claimed = sut.claim("abc.nft", BigDecimal.ONE);

        assertFalse(claimed);
    }


    /// 伦敦派

    @Test
    void should_claim_successful_when_domain_in_whitelist_london_style() {
        DomainWhitelist domainWhitelist = Mockito.mock(DomainWhitelist.class);
        Mockito.when(domainWhitelist.contains(Mockito.anyString())).thenReturn(false);

        CryptoDomain sut = new CryptoDomain(domainWhitelist);

        boolean claimed = sut.claim("abc.nft", BigDecimal.ONE);

        assertTrue(claimed);
    }
}
