package yte.intern.alertProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.model.AlertScheduler;
import yte.intern.alertProject.model.ScheduledAlertRunnable;
import yte.intern.alertProject.repository.AlertRepository;
import yte.intern.alertProject.repository.ResponseRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final ResponseRepository responseRepository;
    private final ResponseService responseService;
    private final AlertScheduler alertScheduler;

    public Alert addAlert(final Alert alert){
        Optional<Alert> alertInDB = alertRepository.findByName(alert.getName());
        if(alertInDB.isPresent()){
            return null;
        }
        else{
            Alert savedAlert = alertRepository.save(alert);
            ScheduledAlertRunnable alertRun = new ScheduledAlertRunnable(savedAlert.getUrl(),savedAlert.getHttpMethod(),savedAlert.getId(),responseService);
            System.out.println(alert.getId());
            alertScheduler.scheduleWithFixedDelay(alertRun,alert.getControlPeriod()*1000);

            return savedAlert;
        }
    }

    public Alert getAlert(final Long alertID) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            return alertInDB.get();
        }
        else{
            return null;
        }
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert updateAlert(Long alertID, Alert alert) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            Alert updatedAlert = alertInDB.get();
            updatedAlert.setName(alert.getName());
            updatedAlert.setUrl(alert.getUrl());
            updatedAlert.setHttpMethod(alert.getHttpMethod());
            updatedAlert.setControlPeriod(alert.getControlPeriod());
            return alertRepository.save(updatedAlert);
        }
        else{
            return null;
        }
    }

    public boolean deleteAlert(Long alertID) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);
        if(alertInDB.isPresent()){
            alertRepository.delete(alertInDB.get());
            return true;
        }
        else{
            return false;
        }
    }
}
