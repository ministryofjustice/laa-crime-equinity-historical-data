package uk.gov.justice.laa.crime.equinity.historicaldata.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppUtil {
    private static final String ARCHIVE = "archive";

    @Value("${app.environment}")
    private String environment;

    public boolean isArchiveEnvironment() {
        return ARCHIVE.equalsIgnoreCase(environment);
    }
}
