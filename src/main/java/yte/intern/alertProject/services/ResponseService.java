package yte.intern.alertProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.model.Response;
import yte.intern.alertProject.repository.AlertRepository;
import yte.intern.alertProject.repository.ResponseRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final AlertRepository alertRepository;

    public void addResponse(Long alertID, Response response) {
        responseRepository.save(response);
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            Alert alert = alertInDB.get();
            alert.getResponses().add(response);
            alertRepository.save(alert);
        }
    }

    public Set<Response> getAllResponses(Long alertID) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            Alert alert = alertInDB.get();
            return alert.getResponses();
        }
        return null;
    }
}
