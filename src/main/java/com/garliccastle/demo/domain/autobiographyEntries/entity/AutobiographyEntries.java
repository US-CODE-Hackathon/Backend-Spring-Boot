package com.garliccastle.demo.domain.autobiographyEntries.entity;

import com.garliccastle.demo.domain.user.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AutobiographyEntries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autobiographyEntriesId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String title;

    private String content;

    private LocalDate createdAt;
}
