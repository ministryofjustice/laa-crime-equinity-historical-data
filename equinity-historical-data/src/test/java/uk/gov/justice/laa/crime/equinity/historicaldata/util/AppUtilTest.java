package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppUtilTest {

    @Test
    void applySevenYearsLimit_ShouldReturnTrue() {
        AppUtil appUtil = new AppUtil();
        boolean applySevenYearsLimit = appUtil.applySevenYearsLimit();
        assertThat(applySevenYearsLimit).isTrue();
    }
}
