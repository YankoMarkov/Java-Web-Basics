package metube.repositories;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
	
	private final EntityManager entityManager;
	
	public TubeRepositoryImpl() {
		this.entityManager = Persistence
				.createEntityManagerFactory("metube")
				.createEntityManager();
	}
	
	@Override
	public Tube save(Tube entity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(entity);
		this.entityManager.getTransaction().commit();
		return entity;
	}
	
	@Override
	public Tube findById(String id) {
		this.entityManager.getTransaction().begin();
		Tube tube = this.entityManager
				.createQuery("SELECT t FROM tubes t WHERE t.id=:id", Tube.class)
				.setParameter("id", id)
				.getSingleResult();
		this.entityManager.getTransaction().commit();
		return tube;
	}
	
	@Override
	public Tube findByName(String name) {
		this.entityManager.getTransaction().begin();
		Tube tube = this.entityManager
				.createQuery("SELECT t FROM tubes t WHERE t.name=:name", Tube.class)
				.setParameter("name", name)
				.getSingleResult();
		this.entityManager.getTransaction().commit();
		return tube;
	}
	
	@Override
	public List<Tube> findAll() {
		CriteriaQuery<Tube> query = this.entityManager.getCriteriaBuilder()
				.createQuery(Tube.class);
		query.from(Tube.class);
		return this.entityManager.createQuery(query).getResultList();
	}
}
