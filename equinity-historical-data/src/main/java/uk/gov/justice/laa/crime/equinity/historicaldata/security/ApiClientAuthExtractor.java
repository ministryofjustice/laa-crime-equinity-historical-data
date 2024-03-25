package uk.gov.justice.laa.crime.equinity.historicaldata.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApiClientAuthExtractor {
    @Value("${server.api.client-id}")
    private String apiClientId;
    @Value("${server.api.client-secret}")
    private String apiClientSecret;

    @Value("${server.api.header-client-id}")
    private String HEADER_CLIENT_ID;
    @Value("${server.api.header-client-secret}")
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
