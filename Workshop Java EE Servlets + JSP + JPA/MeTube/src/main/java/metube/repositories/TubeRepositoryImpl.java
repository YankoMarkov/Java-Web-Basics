package metube.repositories;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
	
	private final EntityManager entityManager;
	
	@Inject
	public TubeRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public Tube save(Tube entity) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(entity);
			this.entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@Override
	public Tube update(Tube entity) {
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(entity);
			this.entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@Override
	public Tube findById(String id) {
		try {
			this.entityManager.getTransaction().begin();
			Tube tube = this.entityManager
					.createQuery("SELECT t FROM tubes t WHERE t.id=:id", Tube.class)
					.setParameter("id", id)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return tube;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public Tube findByTitle(String title) {
		try {
			this.entityManager.getTransaction().begin();
			Tube tube = this.entityManager
					.createQuery("SELECT t FROM tubes t WHERE t.title=:title", Tube.class)
					.setParameter("title", title)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return tube;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public Tube findByYouTubeId(String youTubeId) {
		try {
			this.entityManager.getTransaction().begin();
			Tube tube = this.entityManager
					.createQuery("SELECT t FROM tubes t WHERE t.youTubeLink=:youTubeId", Tube.class)
					.setParameter("youTubeId", youTubeId)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return tube;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public List<Tube> findAll() {
		try {
			CriteriaQuery<Tube> query = this.entityManager.getCriteriaBuilder()
					.createQuery(Tube.class);
			query.from(Tube.class);
			return this.entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public long size() {
		try {
			this.entityManager.getTransaction().begin();
			long size = this.entityManager.createQuery("SELECT count(t) FROM tubes t", long.class)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return size;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return 0;
		}
	}
}
