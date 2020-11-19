package nc.sf2i.formation.exercice8batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nc.sf2i.formation.exercice8batch.model.Student;
import nc.sf2i.formation.exercice8batch.step.StudentListener;
import nc.sf2i.formation.exercice8batch.step.StudentProcessor;
import nc.sf2i.formation.exercice8batch.step.StudentReader;
import nc.sf2i.formation.exercice8batch.step.StudentWriter;

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
						.processor(new StudentProcessor())
						.writer(writer)
						.build();
		return step;
	}
}
