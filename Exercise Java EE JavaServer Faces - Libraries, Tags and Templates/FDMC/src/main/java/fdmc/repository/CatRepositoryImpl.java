package fdmc.repository;

import fdmc.entities.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat entity) {
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
    public Cat findById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            Cat cat = this.entityManager
                    .createQuery("SELECT c FROM cats c WHERE c.id=:id", Cat.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return cat;
        } catch (Exception e) {
            this.entityManager.getTransaction().commit();
            return null;
        }
    }

    @Override
    public List<Cat> findAll() {
        try {
            CriteriaQuery<Cat> query = this.entityManager.getCriteriaBuilder()
                    .createQuery(Cat.class);
            query.from(Cat.class);
            return this.entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().commit();
            return null;
        }
    }
}
