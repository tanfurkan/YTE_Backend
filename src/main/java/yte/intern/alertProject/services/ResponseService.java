package yte.intern.alertProject.services;

import org.springframework.stereotype.Service;
import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.model.Response;
import yte.intern.alertProject.repository.AlertRepository;
import yte.intern.alertProject.repository.ResponseRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final AlertRepository alertRepository;

    public ResponseService(final ResponseRepository responseRepository, AlertRepository alertRepository) {
        this.responseRepository = responseRepository;
        this.alertRepository = alertRepository;
    }

    public Response getResponse(Long id) {
        Optional<Response> responseInDB = responseRepository.findById(id);
        if(responseInDB.isPresent()){
            return responseInDB.get();
        }
        else{
            return null;
        }
    }

    public Response addResponse(Long alertID, Response response) {
        responseRepository.save(response);
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            Alert alert1 = alertInDB.get();
            alert1.getResponses().add(response);
            alertRepository.save(alert1);
        }
        return null;
    }

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
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
