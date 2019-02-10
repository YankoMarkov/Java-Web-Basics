package metube.repositories;

import metube.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {
	
	Tube findByTitle(String title);
	
	Tube findByYouTubeId(String youTubeId);
	
	Tube update(Tube entity);
}
