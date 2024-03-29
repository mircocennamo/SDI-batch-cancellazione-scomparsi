package it.interno.scomparsibatch.repository;

import it.interno.scomparsibatch.entity.DSchedaScomparso;
import it.interno.scomparsibatch.entity.key.DSchedaScomparsoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DSchedaScomparsoRepository extends JpaRepository<DSchedaScomparso, DSchedaScomparsoKey> {

    @Query(value = "SELECT * " +
            "FROM DBSCOMP.DSCHEDASCOMPARSO d " +
            "WHERE d.TSCOMUNICAZIONE < SYSTIMESTAMP - (?1/24) " +
            "AND d.TIPOLOGIADENUNCIA = 'SEGNALAZIONE' " +
            "AND d.TSCANCELLAZIONE IS NULL " +
            "AND d.IDRIFERIMENTOSCHEDASCOMPARSO IS NULL " +
            "AND d.IDRIFERIMENTOUFFICIOSEGNALANTE IS NULL " +
            "AND d.TSRIFERIMENTOCOMUNICAZIONE IS NULL ", nativeQuery = true)
    List<DSchedaScomparso> getAllSchedeSegnalazione(Integer ore);
}
