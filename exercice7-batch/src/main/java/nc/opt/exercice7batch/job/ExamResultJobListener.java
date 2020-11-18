package nc.opt.exercice7batch.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class ExamResultJobListener implements JobExecutionListener {

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = LocalDateTime.now();
        Logger.getGlobal().info(startTime.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stopTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, stopTime);
        int nanoSecondes = duration.getNano();
        Logger.getGlobal().info("duration = "+nanoSecondes);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
            Logger.getGlobal().info("success "+ jobExecution.getJobId());
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            Logger.getGlobal().info("failure "+ jobExecution.getJobId());
            List<Throwable> throwables = jobExecution.getAllFailureExceptions();
            for (Throwable th : throwables) {
                Logger.getGlobal().severe(th.getLocalizedMessage());
            }
        }
    }
}
