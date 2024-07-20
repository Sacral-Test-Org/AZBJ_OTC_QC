package com.azbj.otcqc.repository;

import com.azbj.otcqc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ValidationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkUserAuthorization(String userId) {
        String sql = "SELECT COUNT(0) FROM user_uw_limits WHERE user_id = ? AND uw_code = 'OTC_QC'";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
        return count != null && count > 0;
    }

    public boolean checkSpgCaseStatus(String policyRef) {
        String sql = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END FROM wip_policy_bases x, wip_policy_versions y WHERE x.contract_id = y.contract_id AND x.policy_ref = ? AND y.product_id IN (SELECT char_value FROM azbj_system_constants WHERE sys_type = 'TELE_MER' AND sys_code = 'TELE_MER_APPR')";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{policyRef}, String.class);
        return "Y".equals(result);
    }

    public boolean checkOtcEligibility(String applicationNo) {
        String sql = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END FROM azbj_phub_status_tracker x WHERE x.application_no = ? AND (x.status = 'OTC_QC' OR (x.status = 'Tele_Video_Mer_Recd' AND (SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END FROM wip_policy_bases x, wip_policy_versions y WHERE x.contract_id = y.contract_id AND x.policy_ref = ? AND y.product_id IN (SELECT char_value FROM azbj_system_constants WHERE sys_type = 'TELE_MER' AND sys_code = 'TELE_MER_APPR')) = 'Y' AND x.end_time IS NULL)) AND NOT EXISTS (SELECT 1 FROM azbj_phub_status_tracker y WHERE y.application_no = x.application_no AND y.status LIKE 'FR-AR%' AND y.end_time IS NULL)";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{applicationNo, applicationNo}, String.class);
        return "Y".equals(result);
    }

    public boolean checkKycFlag(String applicationNo) {
        String sql = "SELECT kyc_flag FROM azbj_proposal_appln_det_ext WHERE appln_no = ? AND de_flag = 'D2' AND ROWNUM < 2";
        String result = jdbcTemplate.queryForObject(sql, new Object[]{applicationNo}, String.class);
        return "Y".equals(result);
    }

    public void saveStatus(String status) {
        String sql = "INSERT INTO document_status (status) VALUES (?)";
        jdbcTemplate.update(sql, status);
    }

    public String getStatus() {
        String sql = "SELECT status FROM document_status ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public Optional<ProposalDetailsModel> findById(String proposalId) {
        String sql = "SELECT * FROM proposal_details WHERE proposal_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{proposalId}, (rs, rowNum) -> new ProposalDetailsModel(rs.getString("proposal_id"), rs.getString("details"))));
    }

    public ProposalDetailsModel save(ProposalDetailsModel proposalDetails) {
        String sql = "INSERT INTO proposal_details (proposal_id, details) VALUES (?, ?)";
        jdbcTemplate.update(sql, proposalDetails.getProposalId(), proposalDetails.getDetails());
        return proposalDetails;
    }

    public boolean isDocumentVerified() {
        String sql = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END FROM document_verification WHERE verified = 'Y'";
        String result = jdbcTemplate.queryForObject(sql, String.class);
        return "Y".equals(result);
    }

    public List<UnderwriterCommentModel> findAllComments(String contractId) {
        String sql = "SELECT * FROM azbj_uw_comments WHERE contract_id = ?";
        return jdbcTemplate.query(sql, new Object[]{contractId}, (rs, rowNum) -> new UnderwriterCommentModel(rs.getString("user_id"), rs.getDate("comment_date"), rs.getString("comments")));
    }

    public List<UnderwriterCommentModel> findCommentsWithFlagN(String contractId) {
        String sql = "SELECT * FROM azbj_uw_comments WHERE contract_id = ? AND flag = 'N'";
        return jdbcTemplate.query(sql, new Object[]{contractId}, (rs, rowNum) -> new UnderwriterCommentModel(rs.getString("user_id"), rs.getDate("comment_date"), rs.getString("comments")));
    }

    public String findContractId(String applicationNumber, String transactionType) {
        String sql = "SELECT CONT_ID FROM azbj_batch_items WHERE APPLICATION_NO = ? AND TRANSACTION_TYPE = 'FRP'";
        return jdbcTemplate.queryForObject(sql, new Object[]{applicationNumber}, String.class);
    }

    public void insertComment(CommentDTO commentData) {
        String sql = "INSERT INTO azbj_uw_comments (event_no, contract_id, policy_no, move_code, policy_status, user_id, comment_date, comments, flag) VALUES (?, ?, ?, ?, ?, ?, SYSDATE, ?, 'N')";
        jdbcTemplate.update(sql, commentData.getEventNo(), commentData.getContractId(), commentData.getPolicyNo(), commentData.getMoveCode(), commentData.getPolicyStatus(), commentData.getUserId(), commentData.getComments());
    }

    public Optional<ValidationModel> findByIfscCode(String ifscCode) {
        String sql = "SELECT * FROM azbj_bank_ifsc_detail WHERE BANK_IFSC = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{ifscCode}, (rs, rowNum) -> new ValidationModel(rs.getString("BANK_IFSC"), rs.getString("BANK_NAME"), rs.getString("BANK_BRANCH"), rs.getString("BANK_MICR"))));
    }

    public List<Document> findRequiredDocuments() {
        String sql = "SELECT * FROM required_documents";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Document(rs.getString("document_type"), rs.getString("status")));
    }

    public void saveApplication(Application application) {
        String sql = "INSERT INTO applications (application_no, details) VALUES (?, ?)";
        jdbcTemplate.update(sql, application.getApplicationNo(), application.getDetails());
    }

    public BankDetailsModel findByIfscCode(String ifscCode) {
        String sql = "SELECT BANK_NAME, BANK_BRANCH, BANK_MICR FROM azbj_bank_ifsc_detail WHERE BANK_IFSC = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ifscCode}, (rs, rowNum) -> new BankDetailsModel(rs.getString("BANK_NAME"), rs.getString("BANK_BRANCH"), rs.getString("BANK_MICR")));
    }

    public Optional<PanDetailsModel> findPanDetails(String panCardNumber) {
        String sql = "SELECT * FROM cp_partners WHERE tax_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{panCardNumber}, (rs, rowNum) -> new PanDetailsModel(rs.getString("first_name"), rs.getString("middle_name"), rs.getString("surname"), rs.getDate("DATE_OF_BIRTH"))));
    }

    public List<SearchResultModel> searchApplications(SearchCriteriaDTO searchCriteriaDTO) {
        String sql = "SELECT apt.application_no appno, apt.proposal_status appstatus, abi.perm_receipt_no receiptno, abi.perm_receipt_date receiptdate, apad.ip_title || ' ' || apad.ip_first_name || ' ' || apad.ip_middle_name || ' ' || apad.ip_last_name laname, apad.ph_title || ' ' || apad.ph_first_name || ' ' || apad.ph_middle_name || ' ' || apad.ph_last_name phname, azsc.sys_desc partnername, apt.received_user recuser FROM azbj_batch_items abi, azbj_phub_tracker apt, azbj_proposal_appln_det apad, azbj_system_constants azsc WHERE apt.application_no = NVL (?, apt.application_no) AND abi.agent_code = NVL (TRIM (?), abi.agent_code) AND abi.perm_receipt_date BETWEEN NVL (?, abi.perm_receipt_date) AND NVL (?, abi.perm_receipt_date) AND To_Number(apt.application_no) = apad.appln_no AND apad.appln_no = To_Number(abi.application_no) AND apt.application_no = abi.application_no AND apt.agent_code = apad.agent_code AND apt.agent_code = abi.agent_code AND apad.agent_code = abi.agent_code AND abi.agent_code = azsc.char_value AND apt.agent_code = azsc.char_value AND apad.agent_code = azsc.char_value AND apt.perm_receipt_no = abi.perm_receipt_no AND apt.perm_receipt_no IS NOT NULL AND abi.perm_receipt_no IS NOT NULL AND azsc.sys_type = 'OTC' AND azsc.sys_code = 'OTC_WEB_PARTNERS' AND de_flag = 'D2' AND proposal_status IN ('PENDING_FOR_BBU', 'PROPOSAL_INVOKED', 'PROPOSAL_UPDATED') AND apt.proposal_no IS NULL";
        return jdbcTemplate.query(sql, new Object[]{searchCriteriaDTO.getApplicationNo(), searchCriteriaDTO.getPartnerType(), searchCriteriaDTO.getFromDate(), searchCriteriaDTO.getToDate()}, (rs, rowNum) -> new SearchResultModel(rs.getString("appno"), rs.getString("appstatus"), rs.getString("receiptno"), rs.getDate("receiptdate"), rs.getString("laname"), rs.getString("phname"), rs.getString("partnername"), rs.getString("recuser")));
    }

    public boolean checkPanStatus() {
        String sql = "SELECT CASE WHEN COUNT(*) > 0 THEN 'Y' ELSE 'N' END FROM pan_verification WHERE verified = 'Y'";
        String result = jdbcTemplate.queryForObject(sql, String.class);
        return "Y".equals(result);
    }

    public void savePanDetails(PanDetailsModel panDetails) {
        String sql = "INSERT INTO pan_details (first_name, middle_name, surname, date_of_birth) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, panDetails.getFirstName(), panDetails.getMiddleName(), panDetails.getSurname(), panDetails.getDateOfBirth());
    }
}
