package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.models.Journals;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.JournalsRepository;
import com.wasil.journal_app.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JournalService {
    private JournalsRepository journalsRepository;
    private UserRepository userRepository;

    public JournalService(JournalsRepository journalsRepository, UserRepository userRepository){
        this.journalsRepository = journalsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public JournalResponse createJournal(JournalRequest journal, Long userId) {
        Journals newJournal = new Journals();
        User user = userRepository.findById(userId).orElseThrow();
        newJournal.setTitle(journal.getTitle());
        newJournal.setDescription(journal.getDescription());
        newJournal.setUser(user);
        journalsRepository.save(newJournal);
        return response(newJournal);
    }

    private JournalResponse response(Journals journal){
        return new JournalResponse(journal.getJournalId(), journal.getTitle(), journal.getDescription(), journal.getUser().getUserId());
    }
}
