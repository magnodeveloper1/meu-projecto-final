package ao.uan.finalproject.handler;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import ao.uan.finalproject.domain.Solucao;;

@RepositoryEventHandler
public class SolucaoHandler {
    
    @HandleAfterCreate
    public void handleSolucaoSave(Solucao solucao) {
        System.out.println("Before Save");

        /****
         * 
        File f = new File("C:/Users/i001180/Documents/Own/wrk");
        Process p;
        try {
            ProcessBuilder compilar = new ProcessBuilder("cmd.exe", "/c", "javac Main.java");
            compilar.directory(f);
            compilar.redirectErrorStream(true);
            Process processoCompilar = compilar.start();
            processoCompilar.waitFor();

            // 2. Executar o Main.class gerado
            ProcessBuilder executar = new ProcessBuilder("cmd.exe", "/c", "Java Main");
            executar.directory(f);
            executar.redirectErrorStream(true);
            Process processoExecutar = executar.start();

            // 3. Escrever inputs para o programa
            OutputStream outputStream = processoExecutar.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println("1 2");   // Simula outro input
            writer.flush();

            // 4. Capturar a saída do programa
            BufferedReader reader = new BufferedReader(new InputStreamReader(processoExecutar.getInputStream()));
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }

            // 5. Aguarda a finalização do processo
            int exitCode = processoExecutar.waitFor();
            System.out.println("Execução finalizada com código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
         */
    }
}
