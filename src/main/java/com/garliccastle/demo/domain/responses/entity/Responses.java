package com.garliccastle.demo.domain.responses.entity;

import com.garliccastle.demo.domain.conversations.entity.Conversations;
import com.garliccastle.demo.domain.prompts.entity.Prompts;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Responses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversations conversation;

    @OneToOne
    @JoinColumn(name = "prompt_id", unique = true)
    private Prompts prompt;

    private String content;

    private LocalDateTime createdAt;
}
