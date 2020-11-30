package nc.sf2i.formation.exercice8spring.calcul;

public interface Operator<E> {

    public E add(E e1, E e2);
    public E sub(E e1, E e2);
    public E mul(E e1, E e2);
    public E div(E e1, E e2);

}
