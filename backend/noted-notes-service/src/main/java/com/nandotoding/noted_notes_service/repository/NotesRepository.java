package com.nandotoding.noted_notes_service.repository;

import com.nandotoding.noted_notes_service.model.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, String> {
    @Query(value = "SELECT * FROM notes n WHERE n.account_id = :accountId AND n.is_deleted = false ORDER BY write_time DESC", nativeQuery = true)
    List<Notes> getAllNotes(String accountId);

    @Query(value = "SELECT * FROM notes n WHERE n.id = :id AND n.account_id = :accountId", nativeQuery = true)
    Notes getNote(String id, String accountId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE notes SET title = :title, note = :note, last_edited = :lastEdited WHERE id = :id AND account_id = :accountId", nativeQuery = true)
    void editNote(String id, String accountId, String title, String note, OffsetDateTime lastEdited);

    @Transactional
    @Modifying
    @Query(value = "UPDATE notes SET is_deleted = true WHERE id = :id AND account_id = :accountId", nativeQuery = true)
    void deleteNote(String id, String accountId);
}
