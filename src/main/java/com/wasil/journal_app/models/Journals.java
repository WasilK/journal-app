package com.wasil.journal_app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "journals")
@Data
public class Journals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    private String title;
    private String description;

    private Instant createdAt;
    private Instant updatedAt;
}
