package com.wasil.journal_app.dto.journals;

import com.wasil.journal_app.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalResponse {
    private Long journalId;
    private String title;
    private String description;
    private Long userId;
}
