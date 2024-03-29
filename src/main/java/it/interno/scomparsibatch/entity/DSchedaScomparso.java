package it.interno.scomparsibatch.entity;

import it.interno.scomparsibatch.entity.key.DSchedaScomparsoKey;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "DSCHEDASCOMPARSO", schema = "DBSCOMP")
@IdClass(DSchedaScomparsoKey.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
public class DSchedaScomparso {

    @Id
    @Column(name = "IDSCHEDASCOMPARSO", nullable = false)
    String idSchedaScomparso;

    @Id
    @Column(name = "TSINSERIMENTO", nullable = false)
    Timestamp tsInserimento;

    @Id
    @Column(name = "IDUFFICIOSEGNALANTE")
    String idUfficioSegnalante;

    @Id
    @Column(name = "TSCOMUNICAZIONE")
    Timestamp tsComunicazione;

}
