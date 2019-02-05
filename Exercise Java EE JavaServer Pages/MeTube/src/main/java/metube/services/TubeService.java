package metube.services;

import metube.domain.models.serviceModels.TubeServiceModel;

import java.util.List;

public interface TubeService {
	
	void saveTube(TubeServiceModel tubeService);
	
	List<TubeServiceModel> getAllTubes();
	
	TubeServiceModel getTubeByName(String name);
}
