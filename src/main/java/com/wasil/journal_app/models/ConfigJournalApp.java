package com.wasil.journal_app.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "config_journal_app")
@Data
public class ConfigJournalApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String value;
}
