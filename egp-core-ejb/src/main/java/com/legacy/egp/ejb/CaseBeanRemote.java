package com.legacy.egp.ejb;

import com.legacy.egp.entity.CaseRecord;

import javax.ejb.Remote;
import java.util.List;

/**
 * Case Bean Remote Interface
 */
@Remote
public interface CaseBeanRemote {

    CaseRecord createCase(CaseRecord caseRecord);

    CaseRecord updateCase(CaseRecord caseRecord);

    CaseRecord getCase(Long caseId);

    CaseRecord getCaseByCaseNumber(String caseNumber);

    List<CaseRecord> getCasesByCustomer(Long customerId);

    List<CaseRecord> getAllCases();
}