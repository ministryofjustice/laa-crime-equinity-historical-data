package uk.gov.justice.laa.crime.equinity.historicaldata.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiClientAuthToken extends AbstractAuthenticationToken {
    private final String apiKey;
    private final String apiSecret;

    public ApiClientAuthToken(String requestApiKey, String requestApiSecret, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = requestApiKey;
        this.apiSecret = requestApiSecret;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
