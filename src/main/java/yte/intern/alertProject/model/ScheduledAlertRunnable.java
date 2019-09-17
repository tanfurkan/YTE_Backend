package yte.intern.alertProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import yte.intern.alertProject.repository.AlertRepository;
import yte.intern.alertProject.repository.ResponseRepository;
import yte.intern.alertProject.services.ResponseService;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Setter
@Component
@AllArgsConstructor
@Scope("prototype")
public class ScheduledAlertRunnable implements Runnable{

    private String url;
    private String method;
    private Long alertID;

    private final ResponseService responseService;

    public void run() {
        long start = System.currentTimeMillis();
        boolean isResponseOK = false;
        try {
        AsyncRestTemplate asycTemp = new AsyncRestTemplate();
        String url = this.url;
        String method = this.method;
        HttpMethod httpMethod;
        switch (method.toUpperCase()){
            case "POST":
                httpMethod = HttpMethod.POST;
                break;
            case "PUT":
                httpMethod = HttpMethod.PUT;
                break;
            case "DELETE":
                httpMethod = HttpMethod.DELETE;
                break;
            case "GET":
            default:
                httpMethod = HttpMethod.GET;
        }
        Class<String> responseType = String.class;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);
        ListenableFuture<ResponseEntity<String>> future = asycTemp.exchange(url, httpMethod, requestEntity, responseType);
        start = System.currentTimeMillis();

            ResponseEntity<String> entity = future.get();
            if(entity.getStatusCodeValue()/100 == 2){
                isResponseOK = true;
            }
        } catch (InterruptedException e) {
            e.getMessage();
        } catch (ExecutionException e) {
            e.getMessage();
        } catch (Error e) {
            e.getMessage();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println( new Date() + " Thread:"+  Thread.currentThread().getName() +" Url: "+ url + " Method:" +method+ " AlertID:"+ alertID + " Response:"+ isResponseOK + " Time:"+timeElapsed);

        Response httpResponse = new Response(isResponseOK,timeElapsed,start);
        responseService.addResponse(alertID,httpResponse);
    }

}
