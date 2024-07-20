package com.azbj.otcqc.dto;

import java.util.Date;

public class CommentDTO {
    private int event_no;
    private String contract_id;
    private String policy_no;
    private String move_code;
    private String policy_status;
    private String user_id;
    private Date comment_date;
    private String comments;
    private String flag;

    // Getters and Setters
    public int getEvent_no() {
        return event_no;
    }

    public void setEvent_no(int event_no) {
        this.event_no = event_no;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getPolicy_no() {
        return policy_no;
    }

    public void setPolicy_no(String policy_no) {
        this.policy_no = policy_no;
    }

    public String getMove_code() {
        return move_code;
    }

    public void setMove_code(String move_code) {
        this.move_code = move_code;
    }

    public String getPolicy_status() {
        return policy_status;
    }

    public void setPolicy_status(String policy_status) {
        this.policy_status = policy_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
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