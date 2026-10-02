package com.wasil.journal_app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "journals")
@Getter
@Setter
public class Journals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    private String title;
    private String description;

    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
