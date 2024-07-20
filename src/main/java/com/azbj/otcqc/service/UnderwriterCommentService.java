package com.azbj.otcqc.service;

import com.azbj.otcqc.dto.UnderwriterCommentDTO;
import com.azbj.otcqc.model.UnderwriterCommentModel;
import com.azbj.otcqc.repository.UnderwriterCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnderwriterCommentService {

    @Autowired
    private UnderwriterCommentRepository underwriterCommentRepository;

    public void addComment(UnderwriterCommentDTO commentData) {
        int nextEventNumber = underwriterCommentRepository.getNextEventNumber(commentData.getContractId());
        UnderwriterCommentModel commentModel = new UnderwriterCommentModel();
        commentModel.setEventNo(nextEventNumber);
        commentModel.setContractId(commentData.getContractId());
        commentModel.setPolicyNo(commentData.getPolicyNo());
        commentModel.setUserId(commentData.getUserId());
        commentModel.setCommentDate(new java.util.Date());
        commentModel.setComments(commentData.getComments());
        commentModel.setFlag("Y");
        underwriterCommentRepository.saveComment(commentModel);
    }

    public UnderwriterCommentModel getLatestComment(String contractId) {
        return underwriterCommentRepository.findLatestCommentByContractId(contractId);
    }

    public void processComment(UnderwriterCommentDTO commentData) {
        if (commentData.getComments() == null || commentData.getComments().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        addComment(commentData);
    }
}
