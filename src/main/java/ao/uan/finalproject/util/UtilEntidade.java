package ao.uan.finalproject.util;

import ao.uan.finalproject.domain.Exercicio;

public class UtilEntidade {

    public static Exercicio mapearExercicio(Exercicio exercicio, String[] values) {
        for(String value : values) {

            String v[] = value.split("=");

            if(value.contains("descricao")) {
                exercicio.setDescricao(v[1]);
            } // Adicionar outras regras
        }

        return exercicio;
    }
    
}
