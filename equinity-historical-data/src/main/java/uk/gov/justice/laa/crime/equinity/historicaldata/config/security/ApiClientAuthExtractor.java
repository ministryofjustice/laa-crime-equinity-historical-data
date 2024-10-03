package uk.gov.justice.laa.crime.equinity.historicaldata.config.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApiClientAuthExtractor {
    @Value("${server.security.client-id.secret}")
    private String apiClientId;
    @Value("${server.security.client-secret.secret}")
    private String apiClientSecret;

    @Value("${server.security.client-id.header}")
    private String HEADER_CLIENT_ID;
    @Value("${server.security.client-secret.header}")
    private String HEADER_CLIENT_SECRET;


    public Optional<Authentication> extract(HttpServletRequest request) {
        String requestClientId = request.getHeader(HEADER_CLIENT_ID);
        String requestClientSecret = request.getHeader(HEADER_CLIENT_SECRET);

        if (!isValidApiClient(requestClientId, requestClientSecret))
            return Optional.empty();

        return Optional.of(new ApiClientAuthToken(requestClientId, requestClientSecret, AuthorityUtils.NO_AUTHORITIES));
    }

    private boolean isValidApiClient(String requestClientId, String requestClientSecret) {
        return (apiClientId.equals(requestClientId) && apiClientSecret.equals(requestClientSecret));
    }
}
