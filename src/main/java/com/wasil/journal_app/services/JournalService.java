package com.wasil.journal_app.services;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.exceptions.ResourceNotFoundException;
import com.wasil.journal_app.models.Journals;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.repository.JournalsRepository;
import com.wasil.journal_app.repository.UserRepository;
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
    public JournalResponse createJournal(JournalRequest journal, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with name : " + username));
        Journals newJournal = new Journals();
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
    public List<JournalResponse> getJournalsByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with name : " + username));
        return journalsRepository.findByUser(user).stream().map(this::response).toList();
    }
    public JournalResponse getJournalById(Long id){
        Journals journal = journalsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Journal not found with id : " + id));
        return response(journal);
    }
    public JournalResponse updateJournal(JournalRequest request, Long journalId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with this name"));
        Journals journal = journalsRepository.findByJournalIdAndUser(journalId, user).orElseThrow(() -> new ResourceNotFoundException("Journal not found with id : " + journalId));
        journal.setTitle(request.getTitle());
        journal.setDescription(request.getDescription());
        journalsRepository.save(journal);
        return response(journal);
    }
    public void deleteJournal(Long journalId, String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username not found."));
        Journals journal = journalsRepository.findByJournalIdAndUser(journalId, user).orElseThrow(() -> new ResourceNotFoundException("Journal not found."));
        journalsRepository.delete(journal);
    }
    private JournalResponse response(Journals journal){
        return new JournalResponse(journal.getJournalId(), journal.getTitle(), journal.getDescription(), journal.getUser().getUserId());
    }
}
