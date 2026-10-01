package com.wasil.journal_app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wasil.journal_app.models.Journals;

@Repository
public interface EntryRepository extends JpaRepository<Journals, Long> {

}
