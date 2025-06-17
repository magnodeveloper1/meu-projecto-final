package ao.uan.finalproject.controller.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ao.uan.finalproject.domain.EntradaSaida;
import ao.uan.finalproject.domain.Exercicio;
import ao.uan.finalproject.domain.Solucao;
import ao.uan.finalproject.domain.TestesSolucao;
import ao.uan.finalproject.repository.EntradaSaidaRepository;
import ao.uan.finalproject.repository.SolucaoRepository;
import ao.uan.finalproject.repository.TesteSolucaoRepository;

@Component
public class SolucaoCheckProcess {

    @Autowired
    private SolucaoRepository solucaoRepository;

    @Autowired
    private EntradaSaidaRepository entradaSaidaRepository;

    @Autowired
    private TesteSolucaoRepository testeSolucaoRepository;

    public void salvarSolucaoTestado(String entrada, String saida, String resultadoEsperado, Solucao solucao) {
        TestesSolucao t = new TestesSolucao();
        t.setEntrada(entrada);
        t.setSaida(saida);
        t.setResultadoEsperado(resultadoEsperado);
        t.setSolucao(solucao);
        testeSolucaoRepository.save(t);
    }

    public void checkarSolucaoNaoTratada() {
        Optional<Solucao> solucaoNaoTratado = solucaoRepository.solucaoNaoTratada();

        if (solucaoNaoTratado.isPresent()) {
            Solucao solucao = solucaoNaoTratado.get();

            if (solucao.getLinguagem().equalsIgnoreCase("Java"))
                handleSolucaoSaveJava(solucao);
            else if (solucao.getLinguagem().equalsIgnoreCase("Python"))
                handleSolucaoSavePython(solucao);
        }
    }

    public void handleSolucaoSavePython(Solucao solucao) {

        Exercicio exercicio = solucao.getExercicio();
        solucao.setResposta("Processando ...");
        solucaoRepository.save(solucao);

        List<EntradaSaida> entradaSaidaList = entradaSaidaRepository.findByExercicio(exercicio); // exercicio.getEntradaSaida();
        String resposta = "";
        boolean pass = false;
        solucao.setOk(pass);

        for (int i = 0; i < entradaSaidaList.size(); i++) {

            EntradaSaida entradaSaida = entradaSaidaList.get(i);
            String[] linhaDeEntrada = entradaSaida.getEntrada().split(" ");

            try {
                File f = createFile(solucao.getCode(), "main.py");
                Process processoExecutar = executeCommend("python main.py", f);
                String saida = "";

                try {
                    OutputStream outputStream = processoExecutar.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    
                    for(String input : linhaDeEntrada) 
                        writer.println(input);
                    
                    writer.flush();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(processoExecutar.getInputStream()));
                    String linha;

                    while ((linha = reader.readLine()) != null) {
                        saida = linha;
                    }

                    reader.close();

                } catch (IOException e) {
                    resposta = "Runtime Error";
                    break;
                }

                int exitCode = processoExecutar.waitFor();

                if (exitCode == 0) {

                    boolean answer = saida.equals(entradaSaida.getSaida());
                    salvarSolucaoTestado(entradaSaida.getEntrada(), saida, entradaSaida.getSaida(), solucao);
                    pass = answer;

                    if (!answer) {
                        resposta = "Resposta Errada no teste " + (i + 1);
                        break;
                    } else {
                        resposta = "OK";
                    }

                } else {
                    resposta = "Exception Sintax Error";
                    break;
                }

            } catch (InterruptedException | FileNotFoundException e) {
                resposta = "Sintax Error";
                break;
            }

            File f2 = new File("C:\\Users\\i001180\\Documents\\3. Projectos\\meu-projecto-final\\main.py");
            File f1 = new File("C:\\Users\\i001180\\Documents\\3. Projectos\\meu-projecto-final\\main.py");
            f1.delete();
            f2.delete();
        }

        solucao.setOk(pass);
        solucao.setResposta(resposta);
        solucaoRepository.save(solucao);

    }

    public void handleSolucaoSaveJava(Solucao solucao) {

        Exercicio exercicio = solucao.getExercicio();
        solucao.setResposta("Processando ...");
        solucaoRepository.save(solucao);

        List<EntradaSaida> entradaSaidaList = entradaSaidaRepository.findByExercicio(exercicio); // exercicio.getEntradaSaida();
        String resposta = "";
        boolean pass = false;
        solucao.setOk(pass);

        for (int i = 0; i < entradaSaidaList.size(); i++) {

            EntradaSaida entradaSaida = entradaSaidaList.get(i);

            try {

                File f = createFile(solucao.getCode(), "Main.java");
                Process processoCompilar = executeCommend("javac Main.java", f);
                processoCompilar.waitFor();

                Process processoExecutar = executeCommend("java Main", f);
                String saida = "";

                try {
                    OutputStream outputStream = processoExecutar.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    writer.println(entradaSaida.getEntrada());
                    writer.flush();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(processoExecutar.getInputStream()));
                    String linha;

                    while ((linha = reader.readLine()) != null) {
                        saida = linha;
                    }

                    reader.close();
                    outputStream.close();
                    writer.close();

                } catch (IOException e) {
                    resposta = "Runtime Error";
                    break;
                }

                int exitCode = processoExecutar.waitFor();

                if (exitCode == 0) {

                    boolean answer = saida.equals(entradaSaida.getSaida());
                    salvarSolucaoTestado(entradaSaida.getEntrada(), saida, entradaSaida.getSaida(), solucao);
                    pass = answer;

                    if (!answer) {
                        resposta = "Resposta Errada no teste " + (i + 1);
                        break;
                    } else {
                        resposta = "OK";
                    }

                } else {
                    resposta = "Exception Sintax Error";
                    break;
                }

            } catch (InterruptedException | FileNotFoundException e) {
                resposta = "Sintax Error";
                break;
            }

            File f2 = new File("C:\\Users\\i001180\\Documents\\3. Projectos\\meu-projecto-final\\Main.java");
            File f1 = new File("C:\\Users\\i001180\\Documents\\3. Projectos\\meu-projecto-final\\Main.class");
            f1.delete();
            f2.delete();
        }

        solucao.setOk(pass);
        solucao.setResposta(resposta);
        solucaoRepository.save(solucao);
    }

    public File createFile(String texto, String nomeFile) throws FileNotFoundException {
        File rootFolder = new File("C:\\Users\\i001180\\Documents\\3. Projectos\\meu-projecto-final\\");
        File file = null;

        if (rootFolder.exists() && rootFolder.isDirectory()) {
            file = new File(rootFolder.getAbsolutePath() + "/" + nomeFile);
            if (!file.exists()) {

                try {
                    file.createNewFile();
                } catch (IOException e) {
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

        Process p = null;
        try {
            // bash - para o caso do MacOS
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", command); // ("python", "main.py", "1 1");
            pb.directory(f.getParentFile());
            pb.redirectErrorStream(true);
            p = pb.start();
        } catch (IOException e) {
            System.out.println("n~ao executou ...");
        }
        return p;
    }
}
