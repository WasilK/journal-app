package com.wasil.journal_app.controllers;

import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.services.JournalService;
import org.springframework.web.bind.annotation.*;

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
}
