package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.exceptions.ResourceNotFoundException;
import com.wasil.journal_app.models.Journals;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.JournalsRepository;
import com.wasil.journal_app.respository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<JournalResponse> getAllJournals(){
        return journalsRepository.findAll().stream().map(this::response).toList();
    }
    public List<JournalResponse> getJournalsByUserId(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        return journalsRepository.findByUser(user).stream().map(this::response).toList();
    }
    public void deleteJournal(Long journalId){
        journalsRepository.deleteById(journalId);
    }
    private JournalResponse response(Journals journal){
        return new JournalResponse(journal.getJournalId(), journal.getTitle(), journal.getDescription(), journal.getUser().getUserId());
    }
}
