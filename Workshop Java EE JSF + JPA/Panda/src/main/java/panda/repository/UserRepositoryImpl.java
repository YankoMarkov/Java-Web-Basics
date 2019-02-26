package panda.repository;

import panda.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRepository {
	
	@Inject
	public UserRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public User save(User entity) {
		return this.executeTransaction((em) -> {
			em.persist(entity);
			return entity;
		});
	}
	
	@Override
	public User findById(String id) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT u FROM users u WHERE u.id=:id", User.class)
					.setParameter("id", id)
					.getSingleResult();
		});
	}
	
	@Override
	public User findByUsername(String username) {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT u FROM users u WHERE u.username=:username", User.class)
					.setParameter("username", username)
					.getSingleResult();
		});
	}
	
	@Override
	public List<User> findAll() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT u FROM users u", User.class)
					.getResultList();
		});
	}
	
	@Override
	public Long size() {
		return this.executeTransaction((em) -> {
			return em.createQuery("SELECT COUNT(u) FROM users u", Long.class)
					.getSingleResult();
		});
	}
}
