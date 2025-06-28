package com.garliccastle.demo.domain.prompts.repository;

import com.garliccastle.demo.domain.prompts.entity.Prompts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PromptsRepository extends JpaRepository<Prompts, Long> {
    @Query(value = "SELECT * FROM prompts WHERE type = 'EMOTION' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Prompts> findRandomEmotionPrompt();

    @Query(value = "SELECT * FROM prompts WHERE type = 'AUTOBIOGRAPHY' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Prompts> findRandomAUTOBIOGRAPHYPrompt();
}
