package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.serviceModels.TubeServiceModel;
import metube.repositories.TubeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
	
	private final TubeRepository tubeRepository;
	private final ModelMapper modelMapper;
	
	@Inject
	public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
		this.tubeRepository = tubeRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public boolean saveTube(TubeServiceModel tubeService) {
		Tube tube = this.modelMapper.map(tubeService, Tube.class);
		if (existTube(tubeService.getYouTubeLink())) {
			return false;
		}
		this.tubeRepository.save(tube);
		return true;
	}
	
	@Override
	public void updateTube(TubeServiceModel tubeService) {
		Tube tube = this.modelMapper.map(tubeService, Tube.class);
		this.tubeRepository.update(tube);
	}
	
	@Override
	public List<TubeServiceModel> getAllTubes() {
		List<Tube> tubes = this.tubeRepository.findAll();
		if (tubes == null) {
			return null;
		}
		return tubes.stream()
				.map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public TubeServiceModel getTubeByName(String name) {
		Tube tube = this.tubeRepository.findByTitle(name);
		if (tube == null) {
			return null;
		}
		return this.modelMapper.map(tube, TubeServiceModel.class);
	}
	
	@Override
	public TubeServiceModel getTubeById(String id) {
		Tube tube = this.tubeRepository.findById(id);
		if (tube == null) {
			return null;
		}
		return this.modelMapper.map(tube, TubeServiceModel.class);
	}
	
	@Override
	public boolean existTube(String youTubeId) {
		Tube tube = this.tubeRepository.findByYouTubeId(youTubeId);
		if (tube == null) {
			return false;
		}
		return true;
	}
}
