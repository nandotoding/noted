package com.nandotoding.noted_notes_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "notes")
@Data
public class Notes {
    @Id
    private String id;
    private String accountId;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime writeTime;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime lastEdited;
    private boolean isDeleted;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String note;
}
