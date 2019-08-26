package yte.intern.alertProject.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.model.AlertScheduler;
import yte.intern.alertProject.model.ScheduledAlertRunnable;
import yte.intern.alertProject.repository.AlertRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final ResponseService responseService;
    private final AlertScheduler alertScheduler;

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

        if(alertScheduler.getPoolSize() < 2){
                System.out.println("PoolSize:"+alertScheduler.getPoolSize());
                System.out.println("POOL SIZE <2 ");
                alertScheduler.setPoolSize(50);
                System.out.println("PoolSize:"+alertScheduler.getPoolSize());

        }

        ScheduledAlertRunnable alertRun = new ScheduledAlertRunnable(alert.getUrl(),alert.getHttpMethod(),alert.getId(),responseService);
        System.out.println(alert.getId());
        alertScheduler.scheduleWithFixedDelay(alert.getId(),alertRun,alert.getControlPeriod()*1000);
    }
}
