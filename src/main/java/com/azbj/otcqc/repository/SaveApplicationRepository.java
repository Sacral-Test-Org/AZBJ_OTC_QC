package com.azbj.otcqc.repository;

import com.azbj.otcqc.models.SaveApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveApplicationRepository extends JpaRepository<SaveApplicationModel, Long> {
    SaveApplicationModel save(SaveApplicationModel saveApplicationModel);
}