package metube.domain.models.serviceModels;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;
    private Set<TubeServiceModel> tubes;

    public UserServiceModel() {
        this.tubes = new HashSet<>();
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

    public Set<TubeServiceModel> getTubes() {
        return tubes;
    }

    public void setTubes(Set<TubeServiceModel> tubes) {
        this.tubes = tubes;
    }
}
