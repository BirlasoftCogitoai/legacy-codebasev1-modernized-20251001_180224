package com.legacy.egp.ejb;

import com.legacy.egp.entity.CaseRecord;

import javax.ejb.Local;
import java.util.List;

/**
 * Case Bean Local Interface
 */
@Local
public interface CaseBeanLocal {

    CaseRecord createCase(CaseRecord caseRecord);

    CaseRecord updateCase(CaseRecord caseRecord);

    CaseRecord getCase(Long caseId);

    CaseRecord getCaseByCaseNumber(String caseNumber);

    List<CaseRecord> getCasesByCustomer(Long customerId);

    List<CaseRecord> getAllCases();
}