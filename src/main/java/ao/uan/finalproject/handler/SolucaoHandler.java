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
        EntradaSaida entradaSaida = (EntradaSaida) entradaSaidaRepository.findByExercicio(exercicio).toArray()[0];
        
        try {

            File f = createFile(solucao.getCode());
            Process processoCompilar = executeCommend("javac Main.java", f);
            processoCompilar.waitFor();

            Process processoExecutar = executeCommend("ifconfig", f);
            String saida = entradaSaida(entradaSaida.getEntrada(), processoExecutar);
            int exitCode = processoExecutar.waitFor();

            if(exitCode == 0)
                solucao.setOk(saida.equals(entradaSaida.getSaida()));

            solucaoRepository.save(solucao);

        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public File createFile(String texto) throws FileNotFoundException {
        File rootFolder = new File("/solucao");
        File file = null;

        if(rootFolder.exists() && rootFolder.isDirectory()) {
            file = new File(rootFolder.getAbsolutePath()+"/Main.java");
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {}
            }

            OutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(texto);
            writer.flush();
        }

        return file;
    }

    public Process executeCommend(String command, File f) {
        Process p = null;
        try {
            // bash - para o caso do MacOS
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", command);
            pb.directory(f.getParentFile());
            pb.redirectErrorStream(true);
            pb.redirectError(new File(f.getParentFile() + "/log")); //Log errors in specified log file.
            p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public String entradaSaida(String entrada, Process processoExecutar) {            
        String saida = "";
        
        try {
            OutputStream outputStream = processoExecutar.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(entrada);
            writer.flush();
    
            BufferedReader reader = new BufferedReader(new InputStreamReader(processoExecutar.getInputStream()));
            String linha;

            while ((linha = reader.readLine()) != null) {
                saida += ("\n" + linha);
            }
        } catch (IOException e) {
            saida = e.toString();
        }

        return saida;
    }
}
