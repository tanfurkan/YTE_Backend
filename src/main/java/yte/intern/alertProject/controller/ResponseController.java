package yte.intern.alertProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.alertProject.model.Response;
import yte.intern.alertProject.services.ResponseService;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @PostMapping("/response")
    public Response addAlert(@RequestParam final Long alertID,@RequestBody final Response response){
        return responseService.addResponse(alertID,response);
    }

    @GetMapping("/response")
    public Response getResponse(@RequestParam final Long id){
        return responseService.getResponse(id);
    }

    @GetMapping("/allresponses")
    public List<Response> getAllResponses(){
        return responseService.getAllResponses();
    }

    @GetMapping("/responses")
    public Set<Response> getResponses(@RequestParam final Long alertID){
        return responseService.getAllResponses(alertID);
    }
}
