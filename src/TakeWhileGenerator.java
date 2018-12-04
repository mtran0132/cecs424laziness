import java.util.Iterator;
import java.util.function.Predicate;

public class TakeWhileGenerator <T> implements Iterable <T> {
    private Iterable <T> mSource ; // the sequence to skip values from
    private Predicate<T> mPred;     // Predicate
    private T currentValue;

    public TakeWhileGenerator (Predicate<T> pred, Iterable <T> source) {
        mPred = pred;
        mSource = source;
    }
    public Iterator <T > iterator() {
        return new takeWhileIterator();
    }
    private class takeWhileIterator implements Iterator <T > {

        // To allow multiple TakewhileIterators to walk over the same source , we request a unique
        // source iterator for each TakeIterator.
        private Iterator<T> iterator ;
        private Predicate predicate;

        public takeWhileIterator() {
            predicate = mPred;
            iterator = mSource.iterator();
        }

        public boolean hasNext () {
            if(iterator.hasNext()){
                currentValue = iterator.next();
                if(predicate.test(currentValue)){
                    return  true;
                }
            }
            return false;
        }

        public T next () {
            return currentValue;
        }
    }
}

