package ao.uan.finalproject.handler;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import ao.uan.finalproject.domain.Utilizador;

@RepositoryEventHandler
public class UtilizadorHandler {
    
    @HandleBeforeCreate
    public void handleUtilizadorSave(Utilizador utilizador) {
        System.out.println("Before Save");
    }
}
