package it.interno.scomparsibatch.repository;

import it.interno.scomparsibatch.entity.DSchedaScomparso;
import it.interno.scomparsibatch.entity.key.DSchedaScomparsoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;


public interface NativeQueryRepository extends JpaRepository<DSchedaScomparso, DSchedaScomparsoKey> {

    @Query(value = "SELECT NR_ORE FROM DBSCOMP.CPARAMETRI_PROCEDURE_BATCH WHERE PROCEDURA = ?1", nativeQuery = true)
    Integer getOreProcedura(String nomeProcedura);

    @Modifying
    @Query(value = "INSERT INTO DBSCOMP.CLOG_PROCEDURE_BATCH " +
            "(PROCEDURA, TSESECUZIONE, NR_REC_ELAB, ESITO_ELAB) " +
            "VALUES (?1, ?2, ?3, ?4)", nativeQuery = true, countQuery = "select 1")
    void insertBatchLog(String procedura, Timestamp tsEsecuzione, Integer numeroRecordElaborati, Character esito);

}
