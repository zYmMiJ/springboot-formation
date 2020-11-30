package nc.sf2i.formation.exercice8spring.calcul;

public class RationalOperator implements Operator<Rational> {

    private static int pgcd(int n, int d) {
        if(n > d) {
            return pgcd(n-d, d);
        } else if (n < d) {
            return pgcd(n, d-n);
        } else {
            return n;
        }
    }

    @Override
    public Rational add(Rational e1, Rational e2) {
        int num = e1.getNumerator() * e2.getDenominator() + e1.getDenominator() * e2.getNumerator();
        int den = e1.getDenominator() * e2.getDenominator();
        int common = pgcd(num, den);
        return new Rational(num/common, den/common);
    }

    @Override
    public Rational sub(Rational e1, Rational e2) {
        int num = e1.getNumerator() * e2.getDenominator() - e1.getDenominator() * e2.getNumerator();
        int den = e1.getDenominator() * e2.getDenominator();
        int common = pgcd(num, den);
        return new Rational(num/common, den/common);
    }

    @Override
    public Rational mul(Rational e1, Rational e2) {
        int num = e1.getNumerator() * e2.getNumerator();
        int den = e1.getDenominator() * e2.getDenominator();
        int common = pgcd(num, den);
        return new Rational(num/common, den/common);
    }

    @Override
    public Rational div(Rational e1, Rational e2) {
        int num = e1.getNumerator() * e2.getDenominator();
        int den = e1.getDenominator() * e2.getNumerator();
        int common = pgcd(num, den);
        return new Rational(num/common, den/common);
    }
}
