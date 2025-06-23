package sv.edu.udb.security;


import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.stereotype.Component;

@Component
public class OAuth2AuthenticationFilter extends OAuth2AuthenticationProcessingFilter {

    public OAuth2AuthenticationFilter(TokenExtractor tokenExtractor) {
        super();
        setTokenExtractor(tokenExtractor);
    }
}