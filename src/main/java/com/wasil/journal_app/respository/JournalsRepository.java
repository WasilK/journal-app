package com.wasil.journal_app.respository;

import com.wasil.journal_app.models.Journals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalsRepository extends JpaRepository<Journals, Long> {
}