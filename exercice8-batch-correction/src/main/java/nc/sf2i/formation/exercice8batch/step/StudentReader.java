package nc.sf2i.formation.exercice8batch.step;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.DataBinder;

import io.micrometer.core.instrument.util.StringUtils;
import nc.sf2i.formation.exercice8batch.model.Student;

/**
 * Il s'occuper de traiter le fichier d'entrée ligne par ligne pour une ligne,
 * il sait comment la découper pour chaque champ, il sait où l'insérer
 */
public class StudentReader extends FlatFileItemReader<Student> {
	// https://stackoverflow.com/questions/45825627/using-spring-batch-to-parse-date-from-file-into-localdatetime
	static class BeanWrapperFieldSetMapperCustom extends BeanWrapperFieldSetMapper<Student> {
		@Override
		protected void initBinder(DataBinder binder) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
				@Override
				public void setAsText(String text) throws IllegalArgumentException {
					if (StringUtils.isNotEmpty(text)) {
						setValue(LocalDate.parse(text, formatter));
					} else {
						setValue(null);
					}
				}

				@Override
				public String getAsText() throws IllegalArgumentException {
					Object date = getValue();
					if (date != null) {
						return formatter.format((LocalDate) date);
					} else {
						return "";
					}
				}
			});
		}
	}

	public StudentReader(String inputPath) {
		super();
		super.setEncoding("UTF-8");
		super.setResource(new ClassPathResource(inputPath));
		Logger.getGlobal().info("consommation du " + inputPath);
		super.setName("studentReader");
		DefaultLineMapper<Student> dlm = new DefaultLineMapper<Student>();
		DelimitedLineTokenizer dlt = new DelimitedLineTokenizer(",");
		dlt.setNames("id", "firstname", "lastname", "birthDate", "score"); // nom des attributs de Student
		dlm.setLineTokenizer(dlt);
		BeanWrapperFieldSetMapper<Student> bwfsm = new BeanWrapperFieldSetMapperCustom();
		bwfsm.setTargetType(Student.class);
		dlm.setFieldSetMapper(bwfsm);
		super.setLineMapper(dlm);
	}
}
