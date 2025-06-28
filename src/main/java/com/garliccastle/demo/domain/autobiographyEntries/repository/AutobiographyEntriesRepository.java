package com.garliccastle.demo.domain.autobiographyEntries.repository;

import com.garliccastle.demo.domain.autobiographyEntries.entity.AutobiographyEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutobiographyEntriesRepository extends JpaRepository<AutobiographyEntries, Long> {
}
