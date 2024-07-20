package com.azbj.otcqc.controller;

import com.azbj.otcqc.dto.UnderwriterCommentDTO;
import com.azbj.otcqc.model.UnderwriterComment;
import com.azbj.otcqc.service.UnderwriterCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class UnderwriterCommentController {

    @Autowired
    private UnderwriterCommentService underwriterCommentService;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestBody UnderwriterCommentDTO commentData) {
        underwriterCommentService.addComment(commentData);
        return ResponseEntity.ok("Comment added successfully");
    }

    @GetMapping("/latest/{contractId}")
    public UnderwriterComment getLatestComment(@PathVariable String contractId) {
        return underwriterCommentService.getLatestComment(contractId);
    }

    @PostMapping("/submit")
    public void submitComment(@RequestBody UnderwriterCommentDTO commentData) {
        underwriterCommentService.processComment(commentData);
    }
}
