```java
package com.legacy.egp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity representing a note attached to a case record in the EGP system.
 */
@Entity
@Table(name = "egp_notes")
@NamedQueries({
    @NamedQuery(
        name = "Note.findByCase",
        query = "SELECT n FROM Note n WHERE n.caseRecord.caseId = :caseId ORDER BY n.createdDate DESC"
    )
})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private CaseRecord caseRecord;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // Getters and Setters

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public CaseRecord getCaseRecord() {
        return caseRecord;
    }

    public void setCaseRecord(CaseRecord caseRecord) {
        this.caseRecord = caseRecord;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
```