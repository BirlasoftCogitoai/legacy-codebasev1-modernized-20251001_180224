```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.CaseRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Modernized Case Data Access Object
 */
@Stateless
public class CaseDAO {

    private static final Logger logger = LoggerFactory.getLogger(CaseDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    public List<CaseRecord> findAllCases() {
        try {
            TypedQuery<CaseRecord> query = entityManager.createQuery("SELECT c FROM CaseRecord c", CaseRecord.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all cases", e);
            throw new RuntimeException("Error fetching all cases", e);
        }
    }

    public CaseRecord findCaseById(Long id) {
        try {
            return entityManager.find(CaseRecord.class, id);
        } catch (NoResultException e) {
            logger.warn("No case found with id: {}", id, e);
            return null;
        } catch (Exception e) {
            logger.error("Error fetching case with id: {}", id, e);
            throw new RuntimeException("Error fetching case with id: " + id, e);
        }
    }

    public void saveCase(CaseRecord caseRecord) {
        try {
            entityManager.persist(caseRecord);
        } catch (Exception e) {
            logger.error("Error saving case", e);
            throw new RuntimeException("Error saving case", e);
        }
    }

    public void updateCase(CaseRecord caseRecord) {
        try {
            entityManager.merge(caseRecord);
        } catch (Exception e) {
            logger.error("Error updating case", e);
            throw new RuntimeException("Error updating case", e);
        }
    }

    public void deleteCase(Long id) {
        try {
            CaseRecord caseRecord = findCaseById(id);
            if (caseRecord != null) {
                entityManager.remove(caseRecord);
            } else {
                logger.warn("No case found with id: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error deleting case with id: {}", id, e);
            throw new RuntimeException("Error deleting case with id: " + id, e);
        }
    }
}
```