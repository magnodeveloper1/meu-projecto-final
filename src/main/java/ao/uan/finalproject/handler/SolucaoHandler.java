package ao.uan.finalproject.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.repository.EntradaSaidaRepository;
import ao.uan.finalproject.repository.SolucaoRepository;;

@RepositoryEventHandler
public class SolucaoHandler {

    @Autowired
    private SolucaoRepository solucaoRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @HandleAfterCreate
    public void handleSolucaoSave(Solucao solucao) {
        System.out.println("After Save ...");

        Exercicio exercicio = solucao.getExercicio();
        EntradaSaida entradaSaida = entradaSaidaRepository.findByExercicio(exercicio);

        File f = new File("C:/Users/i001180/Documents/Own/wrk");
        
        try {
            Process processoCompilar = executeCommend("javac Main.java", f);
            processoCompilar.waitFor();

            Process processoExecutar = executeCommend("java Main", f);

            OutputStream outputStream = processoExecutar.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println("1 2");
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(processoExecutar.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

            int exitCode = processoExecutar.waitFor();
            solucao.setOk(exitCode == 0);

            solucaoRepository.save(solucao);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Process executeCommend(String command, File f) {
        Process p = null;
        try {
            // bash - para o caso do MacOS
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", command);
            pb.directory(f);
            pb.redirectErrorStream(true);
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
