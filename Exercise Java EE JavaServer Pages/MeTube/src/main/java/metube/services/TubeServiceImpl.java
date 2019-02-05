package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.serviceModels.TubeServiceModel;
import metube.repositories.TubeRepository;
import metube.util.ModelMapper;

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
	public void saveTube(TubeServiceModel tubeService) {
		Tube tube = this.modelMapper.map(tubeService, Tube.class);
		this.tubeRepository.save(tube);
	}
	
	@Override
	public List<TubeServiceModel> getAllTubes() {
		return this.tubeRepository.findAll().stream()
				.map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public TubeServiceModel getTubeByName(String name) {
		Tube tube = this.tubeRepository.findByName(name);
		return this.modelMapper.map(tube, TubeServiceModel.class);
	}
}
