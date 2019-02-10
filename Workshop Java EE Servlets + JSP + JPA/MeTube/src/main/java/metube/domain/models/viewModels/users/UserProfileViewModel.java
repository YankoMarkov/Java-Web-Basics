package metube.domain.models.viewModels.users;

import metube.domain.models.viewModels.tubes.TubeDetailsViewModel;

import java.util.HashSet;
import java.util.Set;

public class UserProfileViewModel {
	
	private String id;
	private String username;
	private String email;
	private Set<TubeDetailsViewModel> tubes;
	
	public UserProfileViewModel() {
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<TubeDetailsViewModel> getTubes() {
		return tubes;
	}
	
	public void setTubes(Set<TubeDetailsViewModel> tubes) {
		this.tubes = tubes;
	}
}
