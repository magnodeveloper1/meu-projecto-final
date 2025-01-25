package ao.uan.finalproject.dto.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import ao.uan.finalproject.domain.Utilizador;

@Projection(name = "noPassword", types = { Utilizador.class })
public interface noPassword {
    public Long getId();

    public String getPrimeiroNome();

    public String getUltimoNome();

    public String getEmail();

    public String getCodeName();

    public Date getDataNascimento();
}