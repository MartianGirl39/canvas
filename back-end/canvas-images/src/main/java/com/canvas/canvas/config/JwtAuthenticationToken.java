package com.canvas.canvas.config;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.List;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String username;
    private final List<String> roles;
    private final String token;

    public JwtAuthenticationToken(String username, List<String> roles, String token) {
        super(null);  // No authorities needed (could be set if you wish)
        this.username = username;
        this.roles = roles;
        this.token = token;
        setAuthenticated(true);  // Token is validated
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}


