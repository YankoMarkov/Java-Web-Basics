package panda.repository;

import panda.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl extends BaseRepository implements ReceiptRepository {
	
	@Inject
	public ReceiptRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Receipt save(Receipt entity) {
		return this.executeTransaction((em) -> {
			em.persist(entity);
			return entity;
		});
	}
	
	@Override
	public Receipt findById(String id) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT r FROM receipts r WHERE r.id=:id", Receipt.class)
					.setParameter("id", id)
					.getSingleResult();
		});
	}
	
	@Override
	public List<Receipt> findAllByUser(String username) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT r FROM receipts r WHERE r.recipient.username=:username", Receipt.class)
					.setParameter("username", username)
					.getResultList();
		});
	}
	
	@Override
	public Receipt findByPackage(String packageId) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT r FROM receipts r WHERE r.aPackage.id=:id", Receipt.class)
					.setParameter("id", packageId)
					.getSingleResult();
		});
	}
	
	@Override
	public List<Receipt> findAll() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT r FROM receipts r", Receipt.class)
					.getResultList();
		});
	}
	
	@Override
	public Long size() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT count(r) FROM receipts r", Long.class)
					.getSingleResult();
		});
	}
}
