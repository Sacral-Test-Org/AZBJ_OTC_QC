package com.azbj.otcqc.repository;

import com.azbj.otcqc.model.SubmitApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitApplicationRepository extends JpaRepository<SubmitApplicationModel, Long> {
    SubmitApplicationModel save(SubmitApplicationModel submitApplicationModel);
}