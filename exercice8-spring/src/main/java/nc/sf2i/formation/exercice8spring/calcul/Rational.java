package nc.sf2i.formation.exercice8spring.calcul;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rational {
    protected Integer numerator;
    protected Integer denominator;

    public Rational(Integer numerator, Integer denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
}
