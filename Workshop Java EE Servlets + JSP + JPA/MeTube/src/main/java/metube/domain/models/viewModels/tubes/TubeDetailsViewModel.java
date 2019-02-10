package metube.domain.models.viewModels.tubes;

import metube.domain.models.viewModels.users.UserProfileViewModel;

public class TubeDetailsViewModel {
	
	private String id;
	private String title;
	private String author;
	private String description;
	private String youTubeLink;
	private long views;
	private UserProfileViewModel uploader;
	
	public TubeDetailsViewModel() {
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getYouTubeLink() {
		return youTubeLink;
	}
	
	public void setYouTubeLink(String youTubeLink) {
		this.youTubeLink = youTubeLink;
	}
	
	public long getViews() {
		return views;
	}
	
	public void setViews(long views) {
		this.views = views;
	}
	
	public UserProfileViewModel getUploader() {
		return uploader;
	}
	
	public void setUploader(UserProfileViewModel uploader) {
		this.uploader = uploader;
	}
}
