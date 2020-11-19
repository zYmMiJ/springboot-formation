package nc.opt.exercice7batch;

import nc.opt.exercice7batch.job.ExamResultFieldsSetMapper;
import nc.opt.exercice7batch.job.ExamResultItemProcessor;
import nc.opt.exercice7batch.job.ExamResultJobListener;
import nc.opt.exercice7batch.model.ExamResult;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@EnableBatchProcessing
@Configuration
public class AppConfiguration {
	@Autowired
	protected JobBuilderFactory jobBF;
	@Autowired
	protected StepBuilderFactory stepBF;

    @Bean
	public Job getJob() throws Exception {
		return jobBF.get("exercice7")
			.listener(new ExamResultJobListener())
			.start(getStep())
			.build();
	}

	@Bean
	public Step getStep() throws Exception {
		return stepBF.get("step1")
			.<ExamResult, ExamResult>chunk(2)
			.reader(reader())
			.processor(new ExamResultItemProcessor())
			.writer(writer())
			.build();
	}

	@Bean
	public FlatFileItemReader<ExamResult> reader() {
        FlatFileItemReaderBuilder<ExamResult> builder = new FlatFileItemReaderBuilder<ExamResult>();
        return builder.name("flatFileItemReader")
			.resource(new ClassPathResource("examResult.txt"))
			.fieldSetMapper(new ExamResultFieldsSetMapper())
			.lineTokenizer(new DelimitedLineTokenizer("|"))
			.build();
    }

	@Bean
    public StaxEventItemWriter<ExamResult> writer() {
        StaxEventItemWriterBuilder<ExamResult> staxEventItemWriterBuilder = new StaxEventItemWriterBuilder<ExamResult>();
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(ExamResult.class);
		return staxEventItemWriterBuilder.name("staxEventItemWriter")
			.resource(new FileSystemResource("xml/examResult.xml"))
			.marshaller(marshaller)
			.encoding("UTF-8")
			.build();
    }
}
