package yte.intern.alertProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.alertProject.model.Response;
import yte.intern.alertProject.services.ResponseService;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping("/responses")
    public Set<Response> getResponses(@RequestParam final Long alertID){
        return responseService.getAllResponses(alertID);
    }
}
