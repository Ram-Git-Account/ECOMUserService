package EcomUserService.EcomUserService.security;

import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.source.*;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.client.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* =====================================================
       1️⃣ AUTHORIZATION SERVER (DO NOT TOUCH)
       ===================================================== */
    @Bean
    @Order(1)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(
                "/oauth2/**",
                "/.well-known/**"
        );

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        return http.build();
    }

    /* =====================================================
       2️⃣ USER APIs (LOGIN / LOGOUT / VALIDATE)
       ===================================================== */
    @Bean
    @Order(2)
    public SecurityFilterChain userApiSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/api/users/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/signup",
                                "/api/users/login",
                                "/api/users/logout",
                                "/api/users/validate"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // 🔥 VERY IMPORTANT: NO oauth2ResourceServer HERE
                .httpBasic(b -> b.disable())
                .formLogin(f -> f.disable());

        return http.build();
    }

    /* =====================================================
       3️⃣ RESOURCE SERVER (SERVICE-TO-SERVICE)
       ===================================================== */
    @Bean
    @Order(3)
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt());

        return http.build();
    }

    /* =====================================================
       4️⃣ REGISTER CLIENT
       ===================================================== */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("productservice")
                .clientSecret(passwordEncoder().encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("read")
                .build();

        return new InMemoryRegisteredClientRepository(client);
    }

    /* =====================================================
       5️⃣ JWT SIGNING KEY (OAUTH2)
       ===================================================== */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        try {
            KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                    .privateKey((RSAPrivateKey) keyPair.getPrivate())
                    .build();
            return new ImmutableJWKSet<>(new JWKSet(rsaKey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* =====================================================
       6️⃣ AUTH SERVER SETTINGS
       ===================================================== */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    /* =====================================================
       7️⃣ PASSWORD ENCODER
       ===================================================== */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
