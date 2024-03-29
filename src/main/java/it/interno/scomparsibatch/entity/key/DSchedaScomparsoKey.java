package it.interno.scomparsibatch.entity.key;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DSchedaScomparsoKey implements Serializable {
    String idSchedaScomparso;
    Timestamp tsInserimento;
    String idUfficioSegnalante;
    Timestamp tsComunicazione;
}
