package nc.opt.exercice8batch;

import nc.opt.exercice8batch.model.Student;
import nc.opt.exercice8batch.step.StudentListener;
import nc.opt.exercice8batch.step.StudentProcessor;
import nc.opt.exercice8batch.step.StudentReader;
import nc.opt.exercice8batch.step.StudentWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    protected JobBuilderFactory jobBF;
    @Autowired
    protected StepBuilderFactory stepBF;

    @Bean
    public Job getJob() {
        Job job = jobBF.get("exercice8")
                .listener(new StudentListener())
                .flow(getStep())
                .end()
                .build();
        return job;
    }
    @Bean
    public Step getStep() {
        Step step = stepBF.get("step1")
                .<Student, Student>chunk(2)
                .reader(new StudentReader("student-data.csv"))
                .processor(new StudentProcessor())
                .writer(new StudentWriter())
                .build();
        return step;
    }
}
