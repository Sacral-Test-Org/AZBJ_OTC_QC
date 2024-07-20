package com.azbj.otcqc.repository;

import com.azbj.otcqc.model.DocumentModel;
import com.azbj.otcqc.model.PersonalDetails;
import com.azbj.otcqc.model.DocumentUploadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {

    @Query("SELECT d FROM DocumentModel d")
    List<DocumentModel> findAllDocuments();

    @Query(value = "SELECT azbj_pk0_acc.get_contract_id(:proposalNumber) FROM dual", nativeQuery = true)
    String getContractId(@Param("proposalNumber") String proposalNumber);

    @Query(value = "SELECT TAX_ID, DATE_OF_BIRTH, SEX, FIRST_NAME, MIDDLE_NAME, SURNAME " +
            "FROM cp_partners a, wip_interested_parties b " +
            "WHERE CONTRACT_ID = :contractId " +
            "AND a.PART_ID = b.PARTNER_ID " +
            "AND b.IP_NO = 2", nativeQuery = true)
    PersonalDetails getPersonalDetails(@Param("contractId") String contractId);

    @Override
    <S extends DocumentUploadModel> S save(S entity);

    @Query(value = "SELECT image_path FROM images WHERE proposal_number = :proposalNumber AND image_name = :imageName", nativeQuery = true)
    String findImagePath(@Param("proposalNumber") String proposalNumber, @Param("imageName") String imageName);
}
