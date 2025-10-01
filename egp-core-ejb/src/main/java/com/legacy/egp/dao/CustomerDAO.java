```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Modernized Customer Data Access Object
 */
@Stateless
public class CustomerDAO {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    public List<Customer> findAllCustomers() {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all customers", e);
            throw new RuntimeException("Error fetching all customers", e);
        }
    }

    public Customer findCustomerById(Long id) {
        try {
            return entityManager.find(Customer.class, id);
        } catch (NoResultException e) {
            logger.warn("No customer found with id: {}", id, e);
            return null;
        } catch (Exception e) {
            logger.error("Error fetching customer with id: {}", id, e);
            throw new RuntimeException("Error fetching customer with id: " + id, e);
        }
    }

    public void saveCustomer(Customer customer) {
        try {
            entityManager.persist(customer);
        } catch (Exception e) {
            logger.error("Error saving customer", e);
            throw new RuntimeException("Error saving customer", e);
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            entityManager.merge(customer);
        } catch (Exception e) {
            logger.error("Error updating customer", e);
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public void deleteCustomer(Long id) {
        try {
            Customer customer = findCustomerById(id);
            if (customer != null) {
                entityManager.remove(customer);
            } else {
                logger.warn("No customer found with id: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error deleting customer with id: {}", id, e);
            throw new RuntimeException("Error deleting customer with id: " + id, e);
        }
    }
}
```