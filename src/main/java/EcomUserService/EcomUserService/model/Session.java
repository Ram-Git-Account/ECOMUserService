package EcomUserService.EcomUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    private Date expiringAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiringAt() {
        return expiringAt;
    }

    public void setExpiringAt(Date expiringAt) {
        this.expiringAt = expiringAt;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SessionStatus getSessionstatus() {
        return sessionstatus;
    }

    public void setSessionstatus(SessionStatus sessionstatus) {
        this.sessionstatus = sessionstatus;
    }

    private Date loginAt;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionstatus;
}
