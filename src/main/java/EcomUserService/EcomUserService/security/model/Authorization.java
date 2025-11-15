package EcomUserService.EcomUserService.security.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "`user_authorization`")
@Getter
@Setter
public class Authorization{
@Id
@Column
private String id;
        private String registeredClientId;
        private String principalName;
        private String authorizationGrantType;

        private String authorizedScopes;

        private String attributes;
        @Column(length = 500)
        private String state;


        private String authorizationCodeValue;
        private Instant authorizationCodeIssuedAt;
        private Instant authorizationCodeExpiresAt;
        private String authorizationCodeMetadata;


        private String accessTokenValue;
        private Instant accessTokenIssuedAt;
        @Column(name = "access_token_expires_at")
        private Instant accessTokenExpiresAt;

        private String accessTokenMetadata;
        private String accessTokenType;

        private String accessTokenScopes;

        private String refreshTokenValue;
        private Instant refreshTokenIssuedAt;
        private Instant refreshTokenExpiresAt;

        private String refreshTokenMetadata;


        private String oidcIdTokenValue;
        private Instant oidcIdTokenIssuedAt;
        private Instant oidcIdTokenExpiresAt;
        private String oidcIdTokenMetadata;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getRegisteredClientId() {
                return registeredClientId;
        }

        public void setRegisteredClientId(String registeredClientId) {
                this.registeredClientId = registeredClientId;
        }

        public String getPrincipalName() {
                return principalName;
        }

        public void setPrincipalName(String principalName) {
                this.principalName = principalName;
        }

        public String getAuthorizationGrantType() {
                return authorizationGrantType;
        }

        public void setAuthorizationGrantType(String authorizationGrantType) {
                this.authorizationGrantType = authorizationGrantType;
        }

        public String getAuthorizedScopes() {
                return authorizedScopes;
        }

        public void setAuthorizedScopes(String authorizedScopes) {
                this.authorizedScopes = authorizedScopes;
        }

        public String getAttributes() {
                return attributes;
        }

        public void setAttributes(String attributes) {
                this.attributes = attributes;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }

        public String getAuthorizationCodeValue() {
                return authorizationCodeValue;
        }

        public void setAuthorizationCodeValue(String authorizationCodeValue) {
                this.authorizationCodeValue = authorizationCodeValue;
        }

        public Instant getAuthorizationCodeIssuedAt() {
                return authorizationCodeIssuedAt;
        }

        public void setAuthorizationCodeIssuedAt(Instant authorizationCodeIssuedAt) {
                this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
        }

        public Instant getAuthorizationCodeExpiresAt() {
                return authorizationCodeExpiresAt;
        }

        public void setAuthorizationCodeExpiresAt(Instant authorizationCodeExpiresAt) {
                this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
        }

        public String getAuthorizationCodeMetadata() {
                return authorizationCodeMetadata;
        }

        public void setAuthorizationCodeMetadata(String authorizationCodeMetadata) {
                this.authorizationCodeMetadata = authorizationCodeMetadata;
        }

        public String getAccessTokenValue() {
                return accessTokenValue;
        }

        public void setAccessTokenValue(String accessTokenValue) {
                this.accessTokenValue = accessTokenValue;
        }

        public Instant getAccessTokenIssuedAt() {
                return accessTokenIssuedAt;
        }

        public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
                this.accessTokenIssuedAt = accessTokenIssuedAt;
        }

        public Instant getAccessTokenExpiresAt() {
                return accessTokenExpiresAt;
        }

        public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
                this.accessTokenExpiresAt = accessTokenExpiresAt;
        }

        public String getAccessTokenMetadata() {
                return accessTokenMetadata;
        }

        public void setAccessTokenMetadata(String accessTokenMetadata) {
                this.accessTokenMetadata = accessTokenMetadata;
        }

        public String getAccessTokenType() {
                return accessTokenType;
        }

        public void setAccessTokenType(String accessTokenType) {
                this.accessTokenType = accessTokenType;
        }

        public String getAccessTokenScopes() {
                return accessTokenScopes;
        }

        public void setAccessTokenScopes(String accessTokenScopes) {
                this.accessTokenScopes = accessTokenScopes;
        }

        public String getRefreshTokenValue() {
                return refreshTokenValue;
        }

        public void setRefreshTokenValue(String refreshTokenValue) {
                this.refreshTokenValue = refreshTokenValue;
        }

        public Instant getRefreshTokenIssuedAt() {
                return refreshTokenIssuedAt;
        }

        public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
                this.refreshTokenIssuedAt = refreshTokenIssuedAt;
        }

        public Instant getRefreshTokenExpiresAt() {
                return refreshTokenExpiresAt;
        }

        public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
                this.refreshTokenExpiresAt = refreshTokenExpiresAt;
        }

        public String getRefreshTokenMetadata() {
                return refreshTokenMetadata;
        }

        public void setRefreshTokenMetadata(String refreshTokenMetadata) {
                this.refreshTokenMetadata = refreshTokenMetadata;
        }

        public String getOidcIdTokenValue() {
                return oidcIdTokenValue;
        }

        public void setOidcIdTokenValue(String oidcIdTokenValue) {
                this.oidcIdTokenValue = oidcIdTokenValue;
        }

        public Instant getOidcIdTokenIssuedAt() {
                return oidcIdTokenIssuedAt;
        }

        public void setOidcIdTokenIssuedAt(Instant oidcIdTokenIssuedAt) {
                this.oidcIdTokenIssuedAt = oidcIdTokenIssuedAt;
        }

        public Instant getOidcIdTokenExpiresAt() {
                return oidcIdTokenExpiresAt;
        }

        public void setOidcIdTokenExpiresAt(Instant oidcIdTokenExpiresAt) {
                this.oidcIdTokenExpiresAt = oidcIdTokenExpiresAt;
        }

        public String getOidcIdTokenMetadata() {
                return oidcIdTokenMetadata;
        }

        public void setOidcIdTokenMetadata(String oidcIdTokenMetadata) {
                this.oidcIdTokenMetadata = oidcIdTokenMetadata;
        }

        public String getOidcIdTokenClaims() {
                return oidcIdTokenClaims;
        }

        public void setOidcIdTokenClaims(String oidcIdTokenClaims) {
                this.oidcIdTokenClaims = oidcIdTokenClaims;
        }

        public String getUserCodeValue() {
                return userCodeValue;
        }

        public void setUserCodeValue(String userCodeValue) {
                this.userCodeValue = userCodeValue;
        }

        public Instant getUserCodeIssuedAt() {
                return userCodeIssuedAt;
        }

        public void setUserCodeIssuedAt(Instant userCodeIssuedAt) {
                this.userCodeIssuedAt = userCodeIssuedAt;
        }

        public Instant getUserCodeExpiresAt() {
                return userCodeExpiresAt;
        }

        public void setUserCodeExpiresAt(Instant userCodeExpiresAt) {
                this.userCodeExpiresAt = userCodeExpiresAt;
        }

        public String getUserCodeMetadata() {
                return userCodeMetadata;
        }

        public void setUserCodeMetadata(String userCodeMetadata) {
                this.userCodeMetadata = userCodeMetadata;
        }

        public String getDeviceCodeValue() {
                return deviceCodeValue;
        }

        public void setDeviceCodeValue(String deviceCodeValue) {
                this.deviceCodeValue = deviceCodeValue;
        }

        public Instant getDeviceCodeIssuedAt() {
                return deviceCodeIssuedAt;
        }

        public void setDeviceCodeIssuedAt(Instant deviceCodeIssuedAt) {
                this.deviceCodeIssuedAt = deviceCodeIssuedAt;
        }

        public Instant getDeviceCodeExpiresAt() {
                return deviceCodeExpiresAt;
        }

        public void setDeviceCodeExpiresAt(Instant deviceCodeExpiresAt) {
                this.deviceCodeExpiresAt = deviceCodeExpiresAt;
        }

        public String getDeviceCodeMetadata() {
                return deviceCodeMetadata;
        }

        public void setDeviceCodeMetadata(String deviceCodeMetadata) {
                this.deviceCodeMetadata = deviceCodeMetadata;
        }

        private String oidcIdTokenClaims;

        private String userCodeValue;
        private Instant userCodeIssuedAt;
        private Instant userCodeExpiresAt;
        private String userCodeMetadata;

        private String deviceCodeValue;
        private Instant deviceCodeIssuedAt;
        private Instant deviceCodeExpiresAt;
        private String deviceCodeMetadata;

}
