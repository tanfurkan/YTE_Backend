package yte.intern.alertProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.alertProject.model.Alert;
import yte.intern.alertProject.services.AlertService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AlertController {

    private final AlertService alertService;


    @PostMapping("/alert")
    public boolean addAlert(@RequestBody final Alert alert){
        Alert savedAlert = alertService.addAlert(alert);
        if(savedAlert != null){
            alertService.runAlert(alert);
            return true;
        }
        else{
            return false;
        }
    }

    @GetMapping("/alert")
    public Alert getAlert(@RequestParam final Long id){
        return alertService.getAlert(id);
    }

    @GetMapping("/alerts")
    public List<Alert> getAllAlerts(){
        return alertService.getAllAlerts();
    }

    @PutMapping("/alert")
    public Boolean updateAlert(@RequestParam final Long id, @RequestBody final Alert alert){
        return alertService.updateAlert(id,alert);
    }

    @DeleteMapping("/alert")
    public boolean deleteAlert(@RequestParam final Long id){
        return alertService.deleteAlert(id);
    }


}
