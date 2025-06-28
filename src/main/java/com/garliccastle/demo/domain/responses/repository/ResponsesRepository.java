package com.garliccastle.demo.domain.responses.repository;

import com.garliccastle.demo.domain.responses.entity.Responses;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsesRepository extends JpaRepository<Responses, Long> {
    List<Responses> findAllByConversation_ConversationId(Long conversationId);}
