package nc.opt.exercice8batch.step;

import nc.opt.exercice8batch.model.Student;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import java.util.logging.Logger;

/**
 * Il s'occupe de troiter le fichier d'entrée ligne par ligne
 * pour une ligne, il sait comment la découper
 * pour chaque champ, il sait où l'insérer
 */
public class StudentReader extends FlatFileItemReader {

    public StudentReader(String inputPath) {
        super();
        super.setEncoding("UTF-8");
        super.setResource(new ClassPathResource(inputPath));
        Logger.getGlobal().info("consommation du" + inputPath);
        super.setName("studentReader");
        DefaultLineMapper<Student> dlm = new DefaultLineMapper<Student>();
        DelimitedLineTokenizer dlt = new DelimitedLineTokenizer(",");
        dlt.setNames("id", "firstname", "lastname", "birthDate", "score");
        dlm.setLineTokenizer(dlt);
        BeanWrapperFieldSetMapper<Student> bwfsm = new BeanWrapperFieldSetMapper<Student>();
        bwfsm.setTargetType(Student.class);
        dlm.setFieldSetMapper(bwfsm);

        super.setLineMapper(dlm);
    }
}
