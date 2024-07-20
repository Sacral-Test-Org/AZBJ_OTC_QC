package com.azbj.otcqc.repository;

import com.azbj.otcqc.model.UnderwriterCommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnderwriterCommentRepository extends JpaRepository<UnderwriterCommentModel, Long> {

    @Query("SELECT NVL(MAX(event_no), 0) + 1 FROM azbj_uw_comments WHERE contract_id = :contractId")
    int getNextEventNumber(@Param("contractId") String contractId);

    @Query("SELECT comments FROM azbj_uw_comments a WHERE contract_id = :contractId AND event_no = (SELECT MAX(event_no) FROM azbj_uw_comments b WHERE a.contract_id = b.contract_id)")
    UnderwriterCommentModel findLatestCommentByContractId(@Param("contractId") String contractId);

    default void saveComment(UnderwriterCommentModel commentModel) {
        save(commentModel);
    }
}
