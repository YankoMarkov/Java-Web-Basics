package panda.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Role role;
    private Set<Package> packages;
    private Set<Receipt> receipts;

    public User() {
        this.packages = new HashSet<>();
        this.receipts = new HashSet<>();
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @OneToMany(mappedBy = "recipient")
    public Set<Package> getPackages() {
        return packages;
    }
    
    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }
    
    @OneToMany(mappedBy = "recipient")
    public Set<Receipt> getReceipts() {
        return receipts;
    }
    
    public void setReceipts(Set<Receipt> receipts) {
        this.receipts = receipts;
    }
}
