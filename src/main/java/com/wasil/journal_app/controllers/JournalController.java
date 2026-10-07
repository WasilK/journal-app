package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.services.JournalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {
    private final JournalService journalService;
    public JournalController(JournalService journalService){
        this.journalService = journalService;
    }
    @PostMapping("/me")
    public JournalResponse createJournal(@RequestBody JournalRequest journal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalService.createJournal(journal, username);
    }
    @GetMapping("/me")
    public List<JournalResponse> getJournalsByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalService.getJournalsByUsername(username);
    }
    @PutMapping("/me/{journalId}")
    public JournalResponse updateJournal(@RequestBody JournalRequest request, @PathVariable Long journalId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalService.updateJournal(request, journalId, username);
    }
    @DeleteMapping("me/{journalId}")
    public void deleteJournal(@PathVariable Long journalId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalService.deleteJournal(journalId, username);
    }
}
