package nc.sf2i.formation.exercice8batch.step;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class CsvFileVerification implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
        if (t instanceof FileNotFoundException) {
            return false;
        } else if (t instanceof FlatFileParseException) {
            FlatFileParseException ffpe = (FlatFileParseException) t;
            StringBuilder builder = new StringBuilder();
            builder.append(ffpe.getLocalizedMessage());
            builder.append(" at "+ffpe.getLineNumber());
            builder.append(" " + skipCount + " - " + ffpe.getInput());
            Logger.getGlobal().info(builder.toString());
            return true;
        }
        return false;
    }
}
