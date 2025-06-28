package com.garliccastle.demo.domain.media.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class EmotionalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionalReportId;

    private Long conversationId;

    private String imageUrl;

    private String title;
    private String sentiment;
    private String summary;

    private LocalDateTime createdAt;

    @Column(name = "is_first")
    private boolean isFirst;

    public void updateIsFirst(boolean value) {
        this.isFirst = value;
    }
}
