package register.domain.repository;

import register.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
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
    public void delete(String id) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager
                    .createQuery("DELETE FROM employees e WHERE e.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().commit();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @Override
    public Employee findById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            Employee employee = this.entityManager
                    .createQuery("SELECT e FROM employees e WHERE e.id=:id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return employee;
        } catch (Exception e) {
            this.entityManager.getTransaction().commit();
            return null;
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            CriteriaQuery<Employee> query = this.entityManager.getCriteriaBuilder()
                    .createQuery(Employee.class);
            query.from(Employee.class);
            return this.entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().commit();
            return null;
        }
    }
}
