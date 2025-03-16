package ao.uan.finalproject.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ao.uan.finalproject.controller.rest.SolucaoService;
import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.repository.EntradaSaidaRepository;
import ao.uan.finalproject.repository.SolucaoRepository;
import jakarta.persistence.PostPersist;

@Component
public class SolucaoEventHandler {

    @Autowired
    private SolucaoService solucaoService;

    // @Autowired
    // . private EntradaSaidaRepository entradaSaidaRepository;

    @PostPersist
    public void handleSolucaoSave(Solucao solucao) {
        System.out.println("After Save ...");

        // Exercicio exercicio = solucao.getExercicio();
        EntradaSaida entradaSaida = new EntradaSaida();// entradaSaidaRepository.findByExercicio(exercicio).get(0);
        entradaSaida.setEntrada("1");
        entradaSaida.setEntrada("1");

        System.out.println("After Save ...");
        try {
            File f = createFile(solucao.getCode());
            Process processoCompilar = executeCommend("mkdir example", f);
            processoCompilar.waitFor();

            Process processoExecutar = executeCommend("ifconfig", f);
            String saida = "";
            
            try {
                OutputStream outputStream = processoExecutar.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                writer.println(entradaSaida.getEntrada());
                writer.flush();
        
                BufferedReader reader = new BufferedReader(new InputStreamReader(processoExecutar.getInputStream()));
                String linha;
    
                while ((linha = reader.readLine()) != null) {
                    saida = linha;
                }
            } catch (IOException e) {
                saida = e.toString();
            }

            int exitCode = processoExecutar.waitFor();

            if(exitCode == 0) {
                System.out.println(saida + " = " + entradaSaida.getSaida());
                solucao.setOk(saida.equals(entradaSaida.getSaida()));
            }

            // solucaoService.saveAndUpdateSolucao(solucao);

        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public File createFile(String texto) throws FileNotFoundException {
        File rootFolder = new File("/Users/elmagno/Documents/Projectos/Uan/eola/solucao");
        File file = null;

        System.out.println("Folder");
        if(rootFolder.exists() && rootFolder.isDirectory()) {
            file = new File(rootFolder.getAbsolutePath()+"/Main.java");
            if(!file.exists()) {

                try {
                    System.out.println("Criando");
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("N~ao criou-se : " + e.toString());
                }

            }

            OutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(texto);
            writer.flush();
        }

        return file;
    }

    public Process executeCommend(String command, File f) {
        System.out.println("exec ::::: 111");
        Process p = null;
        try {
            // bash - para o caso do MacOS
            /* ProcessBuilder pb = new ProcessBuilder("bash", "/c", command);
            pb.directory(f.getParentFile());
            pb.redirectErrorStream(true);
            p = pb.start(); */
            p = Runtime.getRuntime().exec("ifconfig");
        } catch (IOException e) {
            System.out.println("n~ao executou ...");
        }
        return p;
    }

    public String entradaSaida(String entrada, Process processoExecutar) {            
        String saida = "";
        


        return saida;
    }
}
