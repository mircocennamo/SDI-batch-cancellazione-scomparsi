package it.interno.scomparsibatch.service.impl;

import it.interno.scomparsibatch.service.BatchService;
import it.interno.scomparsibatch.service.DSchedaScomparsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private DSchedaScomparsoService dSchedaScomparsoService;

    private static final String CANCELLAZIONE_SEGNALAZIONE_PROCEDURA = "PRCSEGN";

    @Override
    @Scheduled(cron = "0 0 2 * * ?")
    public void cancellazioneSegnalazioni() {
        dSchedaScomparsoService.cancellazioneSegnalazioni(CANCELLAZIONE_SEGNALAZIONE_PROCEDURA);
    }
}
