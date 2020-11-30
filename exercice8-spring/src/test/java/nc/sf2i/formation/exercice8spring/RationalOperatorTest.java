package nc.sf2i.formation.exercice8spring;

import nc.sf2i.formation.exercice8spring.calcul.Rational;
import nc.sf2i.formation.exercice8spring.calcul.RationalOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;


// Effectuer les test de RationalOperator
@ExtendWith(SpringExtension.class)
public class RationalOperatorTest {

    private Rational a;
    private Rational b;
    private RationalOperator operator;

    @BeforeEach
    public void setup() {
        a = new Rational(1, 3);
        b = new Rational(1, 4);
        operator = new RationalOperator();
    }

    @Test
    public void testAdd() {
        // 1/3 + 1/4 = 7/12
        Rational actual = operator.add(a, b);
        Assertions.assertTrue(actual.getNumerator() == 7);
        Assertions.assertTrue(actual.getDenominator() == 12);
    }

    @Test
    public void testSub() {
        // 1/3 - 1/4 = 1/12
        Rational actual = operator.sub(a, b);
        Assertions.assertTrue(actual.getNumerator() == 1);
        Assertions.assertTrue(actual.getDenominator() == 12);
    }

    @Test
    public void testMul() {
        // 1/3 * 1/4 = 1/12
        Rational actual = operator.mul(a, b);
        Assertions.assertTrue(actual.getNumerator() == 1);
        Assertions.assertTrue(actual.getDenominator() == 12);
    }

    @Test
    public void testDiv() {
        // 1/3 / 1/4 = 4/3
        Rational actual = operator.div(a, b);
        Assertions.assertTrue(actual.getNumerator() == 4);
        Assertions.assertTrue(actual.getDenominator() == 3);
    }
}
