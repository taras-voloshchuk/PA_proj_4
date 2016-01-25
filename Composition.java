import java.util.ArrayList;
import java.util.Iterator;

public class Composition<T> implements Executeable<T>, Iterable<Executeable<T>>{
    ArrayList <Executeable<T>> functions = new ArrayList<>();
    CompositionIterator<Executeable<T>> iterator = null;
    // Constructors
    public Composition(ArrayList<Executeable<T>> functions) {
        this.functions = functions;
    }

    public Composition(Executeable<T> f1, Executeable<T> f2) {
        this.functions.add(f1);
        this.functions.add(f2);
    }
    public Composition(Executeable<T> f) {
        this.functions.add(f);
    }

    public Composition(Executeable<T> f1, Executeable<T> f2, Executeable<T> f3) {
        this(f1, f2);
        this.functions.add(f3);
    }

    public Composition() { }

    // public methods

    // add one function to list of functions
    public void add(Executeable<T> f) {
        this.functions.add(f);
    }

    // execute composition of functions
    @Override
    public T execute(T x) {
        T res = x;
        for (Executeable<T> a : functions) {
            res = a.execute(res);
        }
        return res;
    }

    // return iterator
    @Override
    public CompositionIterator<Executeable<T>> iterator() {
        iterator = new CompositionIterator<>(functions);
        return iterator;
    }
}


@FunctionalInterface
interface Executeable<T> {
    T execute(T x);
}

class CompositionIterator<E> implements Iterator {
    ArrayList <E> a;
    int pos = 0;
    public CompositionIterator(ArrayList<E>a) {
        this.a = a;
    }
    @Override
    public boolean hasNext() {
        return pos != a.size();
    }

    @Override
    public E next() {
        return a.get(pos++);
    }

}
