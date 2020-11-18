package nc.opt.exercice7batch.job;

import nc.opt.exercice7batch.model.ExamResult;
import org.springframework.batch.item.ItemProcessor;

public class ExamResultItemProcessor implements ItemProcessor<ExamResult, ExamResult> {

    @Override
    public ExamResult process(ExamResult item) throws Exception {
        if (item.getScore() < 60) {
            return null;
        }
        return item;
    }
}
