package metube.repositories;

import metube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
	
	private final EntityManager entityManager;
	
	@Inject
	public UserRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public User save(User entity) {
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
	public User findById(String id) {
		try {
			this.entityManager.getTransaction().begin();
			User user = this.entityManager
					.createQuery("SELECT u FROM users u WHERE u.id=:id", User.class)
					.setParameter("id", id)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public User findByUsername(String username) {
		try {
			this.entityManager.getTransaction().begin();
			User user = this.entityManager
					.createQuery("SELECT u FROM users u WHERE u.username=:username", User.class)
					.setParameter("username", username)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return user;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return null;
		}
	}
	
	@Override
	public List<User> findAll() {
		try {
			CriteriaQuery<User> query = this.entityManager.getCriteriaBuilder()
					.createQuery(User.class);
			query.from(User.class);
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
			long size = this.entityManager.createQuery("SELECT count(u) FROM users u", long.class)
					.getSingleResult();
			this.entityManager.getTransaction().commit();
			return size;
		} catch (Exception e) {
			this.entityManager.getTransaction().commit();
			return 0;
		}
	}
}
