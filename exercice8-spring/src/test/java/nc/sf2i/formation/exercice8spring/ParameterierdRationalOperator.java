package nc.sf2i.formation.exercice8spring;

import nc.sf2i.formation.exercice8spring.calcul.Rational;
import nc.sf2i.formation.exercice8spring.calcul.RationalOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ParameterierdRationalOperator {

    private Rational fromString2Rational(String numVal, String denVal) {
        return new Rational(Integer.valueOf(numVal.trim()), Integer.valueOf(denVal.trim()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/dataset1.csv", delimiter = ';', numLinesToSkip = 1)
    public void testAdd(String input, String expected) {
        String[] values = input.split(",");
        Rational a = fromString2Rational(values[0], values[1]);
        Rational b = fromString2Rational(values[2], values[3]);
        values = expected.split(",");
        Rational exp = fromString2Rational(values[0], values[1]);
        RationalOperator operator = new RationalOperator();
        Rational actual = operator.add(a, b);
        Assertions.assertTrue(actual.getNumerator() == exp.getNumerator());
        Assertions.assertTrue(actual.getDenominator() == exp.getDenominator());
    }
}
