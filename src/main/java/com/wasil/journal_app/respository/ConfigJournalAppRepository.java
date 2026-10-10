package com.wasil.journal_app.respository;

import com.wasil.journal_app.models.ConfigJournalApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepository extends JpaRepository<ConfigJournalApp, Long> {

}
