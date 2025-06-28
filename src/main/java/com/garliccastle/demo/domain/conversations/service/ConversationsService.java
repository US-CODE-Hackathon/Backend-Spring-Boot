package com.garliccastle.demo.domain.conversations.service;

import com.garliccastle.demo.domain.conversations.entity.Conversations;
import com.garliccastle.demo.domain.conversations.repository.ConversationsRepository;
import com.garliccastle.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationsService {
    private final ConversationsRepository conversationsRepository;
    private final UserRepository userRepository;

    public Conversations create(){
        Conversations conversations = Conversations.builder()
                .user(userRepository.findById(1L).orElseThrow())
                .build();
        return conversationsRepository.save(conversations);
    }
}
