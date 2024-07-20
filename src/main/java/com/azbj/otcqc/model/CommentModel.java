package com.azbj.otcqc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "azbj_uw_comments")
public class CommentModel {

    @Id
    @Column(name = "event_no")
    private Long eventNo;

    @Column(name = "contract_id")
    private String contractId;

    @Column(name = "policy_no")
    private String policyNo;

    @Column(name = "move_code")
    private String moveCode;

    @Column(name = "policy_status")
    private String policyStatus;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "comment_date")
    private Date commentDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "flag")
    private String flag;

    // Getters and Setters

    public Long getEventNo() {
        return eventNo;
    }

    public void setEventNo(Long eventNo) {
        this.eventNo = eventNo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getMoveCode() {
        return moveCode;
    }

    public void setMoveCode(String moveCode) {
        this.moveCode = moveCode;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}