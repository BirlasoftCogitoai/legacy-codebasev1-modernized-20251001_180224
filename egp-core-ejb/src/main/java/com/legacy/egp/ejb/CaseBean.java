package com.legacy.egp.ejb;

import com.legacy.egp.dao.CaseDAO;
import com.legacy.egp.dao.CustomerDAO;
import com.legacy.egp.entity.CaseRecord;
import com.legacy.egp.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Date;
import java.util.List;

/**
 * Case Management Session Bean
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CaseBean implements CaseBeanLocal, CaseBeanRemote {

    private static final Logger logger = LoggerFactory.getLogger(CaseBean.class);

    @EJB
    private CaseDAO caseDAO;

    @EJB
    private CustomerDAO customerDAO;

    @Override
    public CaseRecord createCase(CaseRecord caseRecord) {
        try {
            caseRecord.setCreatedDate(new Date());
            return caseDAO.save(caseRecord);
        } catch (Exception e) {
            logger.error("Error creating case: ", e);
            throw new RuntimeException("Error creating case", e);
        }
    }

    @Override
    public CaseRecord updateCase(CaseRecord caseRecord) {
        try {
            return caseDAO.update(caseRecord);
        } catch (Exception e) {
            logger.error("Error updating case: ", e);
            throw new RuntimeException("Error updating case", e);
        }
    }

    @Override
    public CaseRecord getCase(Long caseId) {
        try {
            return caseDAO.findById(caseId);
        } catch (Exception e) {
            logger.error("Error fetching case: ", e);
            throw new RuntimeException("Error fetching case", e);
        }
    }

    @Override
    public CaseRecord getCaseByCaseNumber(String caseNumber) {
        try {
            return caseDAO.findByCaseNumber(caseNumber);
        } catch (Exception e) {
            logger.error("Error fetching case by case number: ", e);
            throw new RuntimeException("Error fetching case by case number", e);
        }
    }

    @Override
    public List<CaseRecord> getCasesByCustomer(Long customerId) {
        try {
            return caseDAO.findByCustomer(customerId);
        } catch (Exception e) {
            logger.error("Error fetching cases by customer: ", e);
            throw new RuntimeException("Error fetching cases by customer", e);
        }
    }

    @Override
    public List<CaseRecord> getAllCases() {
        try {
            return caseDAO.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all cases: ", e);
            throw new RuntimeException("Error fetching all cases", e);
        }
    }
}