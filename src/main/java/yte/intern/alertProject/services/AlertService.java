package yte.intern.alertProject.services;

import org.springframework.stereotype.Service;

import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.model.AlertScheduler;
import yte.intern.alertProject.model.ScheduledAlertRunnable;
import yte.intern.alertProject.repository.AlertRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final ResponseService responseService;
    private final AlertScheduler alertScheduler;

    public AlertService(AlertRepository alertRepository, ResponseService responseService, AlertScheduler alertScheduler) {
        this.alertRepository = alertRepository;
        this.responseService = responseService;
        this.alertScheduler = alertScheduler;
        this.runAllAlertInDB();
    }

    public Alert addAlert(final Alert alert){
        Optional<Alert> alertInDB = alertRepository.findByName(alert.getName());

        if(alertInDB.isPresent()){
            return null;
        }
        else{
            alert.setCreatedAt(System.currentTimeMillis());
            return alertRepository.save(alert);
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

    public boolean updateAlert(Long alertID, Alert alert) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);

        if(alertInDB.isPresent()){
            alertScheduler.removeTask(alertID); // STOP THE RUNNING TASK
            Alert updatedAlert = alertInDB.get();
            updatedAlert.setName(alert.getName());
            updatedAlert.setUrl(alert.getUrl());
            updatedAlert.setHttpMethod(alert.getHttpMethod());
            updatedAlert.setControlPeriod(alert.getControlPeriod());
            alertRepository.save(updatedAlert);
            runAlert(updatedAlert); // RUN THE TASK AGAIN
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteAlert(Long alertID) {
        Optional<Alert> alertInDB = alertRepository.findById(alertID);

        if(alertInDB.isPresent()){
            alertScheduler.removeTask(alertID);
            alertRepository.delete(alertInDB.get());
            return true;
        }
        else{
            return false;
        }
    }

    public void runAlert(Alert alert){
        ScheduledAlertRunnable alertRun = new ScheduledAlertRunnable(alert.getUrl(),alert.getHttpMethod(),alert.getId(),responseService);
        alertScheduler.scheduleWithFixedDelay(alert.getId(),alertRun,alert.getControlPeriod()*1000);
    }
    
    public void runAllAlertInDB(){
        alertScheduler.setPoolSize(50);
        alertScheduler.setThreadGroupName("");
        List<Alert> alertList = getAllAlerts();
        for (Alert alert : alertList) {
            runAlert(alert);
        }
    }
}
