package nc.opt.exercice7batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
//@EnableBatchProcessing
public class Exercice7BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Exercice7BatchApplication.class, args);
	}

//	@Autowired
//	protected JobBuilderFactory jobBF;
//	@Autowired
//	protected StepBuilderFactory stepBF;

//	@Bean
//	public Job getJob() throws Exception {
//		return jobBF.get("exercice7")
//					.start(getStep())
//					.build();
//	}
//
//	@Bean
//	public Step getStep() throws Exception {
//		return stepBF.get("step1")
//					  .tasklet(getTask())
//				      .build();
//	}
//
//	@Bean
//	public Tasklet getTask() throws Exception {
//		return new Tasklet() {
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				Logger.getGlobal().info("exercice7 step1");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.exit(SpringApplication.run(Exercice7BatchApplication.class, args));
//	}


}
