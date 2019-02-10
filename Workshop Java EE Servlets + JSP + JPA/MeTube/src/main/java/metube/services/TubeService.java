package metube.services;

import metube.domain.models.serviceModels.TubeServiceModel;

import java.util.List;

public interface TubeService {
	
	boolean saveTube(TubeServiceModel tubeService);
	
	void updateTube(TubeServiceModel tubeService);
	
	List<TubeServiceModel> getAllTubes();
	
	TubeServiceModel getTubeByName(String name);
	
	TubeServiceModel getTubeById(String id);
	
	boolean existTube(String youTubeId);
}
