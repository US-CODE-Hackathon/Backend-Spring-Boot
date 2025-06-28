package com.garliccastle.demo.domain.conversations.repository;

import com.garliccastle.demo.domain.conversations.entity.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationsRepository extends JpaRepository<Conversations, Long> {
}
