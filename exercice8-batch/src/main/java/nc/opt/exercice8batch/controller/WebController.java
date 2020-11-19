package nc.opt.exercice8batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@EnableAutoConfiguration
public class WebController {

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected Job job;

    @RequestMapping(path = "/runjob", method = RequestMethod.GET)
    public String runJob() throws Exception {
        Logger.getGlobal().info("request http pour le declenchement du batch");
        JobParametersBuilder builder = new JobParametersBuilder();
        JobParameters jobParameters = builder.addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution execution = jobLauncher.run(job, jobParameters);
        return "Done " + execution.getStatus();
    }

}
