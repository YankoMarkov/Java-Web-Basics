package register.domain.repository;

import java.util.List;

public interface GenericRepository<E, K> {
	
	E save(E entity);
	
	void delete(K id);
	
	E findById(K id);
	
	List<E> findAll();
}