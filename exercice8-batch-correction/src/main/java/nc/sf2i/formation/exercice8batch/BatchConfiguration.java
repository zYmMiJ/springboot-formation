package nc.sf2i.formation.exercice8batch;

import nc.sf2i.formation.exercice8batch.step.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nc.sf2i.formation.exercice8batch.model.Student;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	protected JobBuilderFactory jobBF;
	@Autowired
	protected StepBuilderFactory stepBF;
	@Autowired
	protected StudentListener listener;
	@Autowired
	protected StudentWriter writer;
	@Bean
	public Job getJob() {
		Job job = jobBF.get("exercice8")
						.listener(listener)
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
						.faultTolerant()
						.skipPolicy(new CsvFileVerification())
						.skipLimit(10)
						.processor(new StudentProcessor())
						.writer(writer)
						.build();
		return step;
	}
}
