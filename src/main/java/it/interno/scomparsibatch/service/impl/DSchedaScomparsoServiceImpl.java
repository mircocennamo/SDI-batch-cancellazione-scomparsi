package it.interno.scomparsibatch.service.impl;

import it.interno.scomparsibatch.entity.DSchedaScomparso;
import it.interno.scomparsibatch.repository.DSchedaScomparsoRepository;
import it.interno.scomparsibatch.repository.NativeQueryRepository;
import it.interno.scomparsibatch.service.DSchedaScomparsoService;
import it.interno.scomparsibatch.utils.GenericUtils;
import it.interno.scomparsibatch.utils.MsUrls;
import it.interno.scomparsibatch.utils.RestCalls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DSchedaScomparsoServiceImpl implements DSchedaScomparsoService {

    private Logger loggerCancellazioneSegnalazione = Logger.getLogger("PRCSEGN");

    @Autowired
    private DSchedaScomparsoRepository dSchedaScomparsoRepository;
    @Autowired
    private NativeQueryRepository nativeQueryRepository;

    @Override
    public List<DSchedaScomparso> getAllSchedeSegnalazione(Integer ore) {
        return dSchedaScomparsoRepository.getAllSchedeSegnalazione(ore);
    }

    @Override
    @Transactional
    public void cancellazioneSegnalazioni(String nomeProcedura) {

        List<String> idSchedeDaChiudere = new ArrayList<>();
        char esito;
        try {
            loggerCancellazioneSegnalazione.log(Level.INFO, "Job Per chiusura segnalazioni dopo 72H IN ESECUZIONE.");

            // Recupero l'intervallo di ore su cui esegire la procedura
            Integer ore = nativeQueryRepository.getOreProcedura(nomeProcedura);

            idSchedeDaChiudere = this.getAllSchedeSegnalazione(ore).stream().map(DSchedaScomparso::getIdSchedaScomparso).collect(Collectors.toList());
            String url = MsUrls.SCOMPARSI_URL + "/cancellazione/segnalazione/batch";

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                    .queryParam("CodiceUfficio", "RMACPI")
                    .queryParam("CodiceUtente", "REVRINPI")
                    .queryParam("motivazioneEliminazioneScheda", "BATCHCANCELLAZIONE");

            RestCalls.doPost(builder.toUriString(), idSchedeDaChiudere);

            List<String> idSchedeNonChiuse = this.getAllSchedeSegnalazione(ore).stream().map(DSchedaScomparso::getIdSchedaScomparso).collect(Collectors.toList());

            if (!idSchedeNonChiuse.isEmpty()) {

                esito = 'P';
                loggerCancellazioneSegnalazione.log(Level.WARNING, "Esecuzione job parziale");

                for (String s : idSchedeNonChiuse) {
                    loggerCancellazioneSegnalazione.log(Level.WARNING, "La scheda con id " + s.trim() + " non è stata eseguita");
                }
            }else{
                esito = 'S';
                loggerCancellazioneSegnalazione.log(Level.INFO, "Job eseguito con successo");
            }

        } catch (Exception ex) {
            esito = 'F';
            ex.printStackTrace();
            loggerCancellazioneSegnalazione.log(Level.SEVERE, "Esecuzione job fallita con errore : " + ex.getMessage());

        }

        nativeQueryRepository.insertBatchLog(nomeProcedura, GenericUtils.getCurrentTimestamp(), idSchedeDaChiudere.size(), esito);
    }
}
