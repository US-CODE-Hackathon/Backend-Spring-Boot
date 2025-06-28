package com.garliccastle.demo.domain.media.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmotionalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionalReportId;

    private String imageUrl;

    private String title;
    private String sentiment;
    private String summary;

    private LocalDateTime createdAt;
}
