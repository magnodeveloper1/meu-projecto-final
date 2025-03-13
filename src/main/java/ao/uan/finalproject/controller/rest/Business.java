package ao.uan.finalproject.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Business {
    
    @PostMapping("/api/validar-code")
    public String validateCode(){
        return "false";
    }
}
