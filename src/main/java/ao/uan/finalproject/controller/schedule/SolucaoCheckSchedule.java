package ao.uan.finalproject.controller.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ao.uan.finalproject.repository.SolucaoRepository;
import ao.uan.finalproject.repository.TesteSolucaoRepository;

@Component
public class SolucaoCheckSchedule {

    private static final Logger log = LoggerFactory.getLogger(SolucaoCheckSchedule.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SolucaoRepository solucaoRepository;

    @Autowired
    private SolucaoCheckProcess checkProcess;

    @Scheduled(cron = "* * * * * *")
    public void checkSolucaoNaoTratadas() {
        // log.info("The time is now {}", dateFormat.format(new Date()));
        checkProcess.checkarSolucaoNaoTratada();
    }
}
