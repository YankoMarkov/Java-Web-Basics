package panda.domain.models.serviceModels;

import panda.domain.entities.Role;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Set<PackageServiceModel> packages;
    private Set<ReceiptServiceModel> receipts;

    public UserServiceModel() {
        this.packages = new HashSet<>();
        this.receipts = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<PackageServiceModel> getPackages() {
        return packages;
    }

    public void setPackages(Set<PackageServiceModel> packages) {
        this.packages = packages;
    }

    public Set<ReceiptServiceModel> getReceipts() {
        return receipts;
    }

    public void setReceipts(Set<ReceiptServiceModel> receipts) {
        this.receipts = receipts;
    }
}
