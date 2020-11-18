package nc.opt.exercice7batch.job;

import nc.opt.exercice7batch.model.ExamResult;
import nc.opt.exercice7batch.model.LocalDateAdapter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.util.logging.Logger;

public class ExamResultFieldsSetMapper implements FieldSetMapper<ExamResult> {

    @Override
    public ExamResult mapFieldSet(FieldSet fieldSet) throws BindException {
        ExamResult examResult = new ExamResult();
        examResult.setStudentName(fieldSet.readString(0));
        LocalDateAdapter localDateAdapter = new LocalDateAdapter();
        LocalDate birthDay = null;

        try {
            birthDay = localDateAdapter.unmarshal(fieldSet.readString(1));
        } catch (Exception e) {
            Logger.getGlobal().severe(e.getMessage());
        }
        examResult.setBirthDay(birthDay);
        examResult.setScore(fieldSet.readDouble(2));
        return examResult;
    }
}
