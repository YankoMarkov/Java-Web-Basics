package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl extends BaseRepository implements PackageRepository {
	
	@Inject
	public PackageRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Package save(Package entity) {
		return this.executeTransaction((em) -> {
			em.persist(entity);
			return entity;
		});
	}
	
	@Override
	public Package update(Package entity) {
		return this.executeTransaction((em) -> {
			em.merge(entity);
			return entity;
		});
	}
	
	@Override
	public Package findById(String id) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT p FROM packages p WHERE p.id=:id", Package.class)
					.setParameter("id", id)
					.getSingleResult();
		});
	}
	
	@Override
	public List<Package> findAll() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT p FROM packages p", Package.class)
					.getResultList();
		});
	}
	
	@Override
	public List<Package> findAllByStatus(Status status) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT p FROM packages p WHERE p.status=:status", Package.class)
					.setParameter("status", status)
					.getResultList();
		});
	}
	
	@Override
	public List<Package> findAllByUserAndStatus(String username, Status status) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT p FROM packages p WHERE p.recipient.username=:username AND p.status=:status", Package.class)
					.setParameter("username", username)
					.setParameter("status", status)
					.getResultList();
		});
	}
	
	@Override
	public Long size() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT count(p) FROM packages p", Long.class)
					.getSingleResult();
		});
	}
}
