package com.wasil.journal_app.respository;

import com.wasil.journal_app.models.Journals;
import com.wasil.journal_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalsRepository extends JpaRepository<Journals, Long> {
    List<Journals> findByUser(User user);
    Optional<Journals> findByJournalIdAndUser(Long journalId, User user);
}