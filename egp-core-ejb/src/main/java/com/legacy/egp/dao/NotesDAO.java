```java
package com.legacy.egp.dao;

import com.legacy.egp.entity.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Modernized Notes Data Access Object
 */
@Stateless
public class NotesDAO {

    private static final Logger logger = LoggerFactory.getLogger(NotesDAO.class);

    @PersistenceContext(unitName = "egp-pu")
    private EntityManager entityManager;

    public List<Note> findAllNotes() {
        try {
            TypedQuery<Note> query = entityManager.createQuery("SELECT n FROM Note n", Note.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all notes", e);
            throw new RuntimeException("Error fetching all notes", e);
        }
    }

    public Note findNoteById(Long id) {
        try {
            return entityManager.find(Note.class, id);
        } catch (Exception e) {
            logger.error("Error fetching note with id: {}", id, e);
            throw new RuntimeException("Error fetching note with id: " + id, e);
        }
    }

    public void saveNote(Note note) {
        try {
            entityManager.persist(note);
        } catch (Exception e) {
            logger.error("Error saving note", e);
            throw new RuntimeException("Error saving note", e);
        }
    }

    public void updateNote(Note note) {
        try {
            entityManager.merge(note);
        } catch (Exception e) {
            logger.error("Error updating note", e);
            throw new RuntimeException("Error updating note", e);
        }
    }

    public void deleteNote(Long id) {
        try {
            Note note = findNoteById(id);
            if (note != null) {
                entityManager.remove(note);
            } else {
                logger.warn("No note found with id: {}", id);
            }
        } catch (Exception e) {
            logger.error("Error deleting note with id: {}", id, e);
            throw new RuntimeException("Error deleting note with id: " + id, e);
        }
    }
}
```