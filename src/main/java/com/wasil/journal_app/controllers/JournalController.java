package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.services.JournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {
    private final JournalService journalService;
    public JournalController(JournalService journalService){
        this.journalService = journalService;
    }
    @PostMapping("/{userId}")
    public JournalResponse createJournal(@PathVariable Long userId, @RequestBody JournalRequest journal){
        return journalService.createJournal(journal, userId);
    }
    @GetMapping
    public List<JournalResponse> getAllJournals() {
        return journalService.getAllJournals();
    }
    @GetMapping("/{userId}")
    public List<JournalResponse> getJournalsByUserId(@PathVariable Long userId){
        return journalService.getJournalsByUserId(userId);
    }
    @DeleteMapping("/{journalId}")
    public void deleteJournal(@PathVariable Long journalId){
        journalService.deleteJournal(journalId);
    }
}
