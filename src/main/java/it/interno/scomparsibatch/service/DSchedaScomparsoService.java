package it.interno.scomparsibatch.service;

import it.interno.scomparsibatch.entity.DSchedaScomparso;

import java.util.List;

public interface DSchedaScomparsoService {

    List<DSchedaScomparso> getAllSchedeSegnalazione(Integer ore);
    void cancellazioneSegnalazioni(String nomeProcedura);

}
