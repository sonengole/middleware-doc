package com.sbs.kyudo.middlewaredoc.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.security")
public class SecurityPropertiesConfig {
    private List<User> users;

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public static class User {
        private String username;
        private String password;
        private String roles;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRoles() { return roles; }
        public void setRoles(String roles) { this.roles = roles; }
    }
}
