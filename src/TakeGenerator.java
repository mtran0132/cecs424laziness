import java.util.Iterator;

public class TakeGenerator <T > implements Iterable <T > {
    private Iterable <T > mSource ; // the sequence to take values from
    private int mAmount ; // The number of values to take
    public TakeGenerator ( int n , Iterable <T> source ) {
        mSource = source ;
        mAmount = n ;
    }
    public Iterator <T > iterator () {
        return new TakeIterator ();
    }
    private class TakeIterator implements Iterator <T > {
        private int mRemaining ;
        // To allow multiple TakeIterators to walk over the same source , we request a unique
        // source iterator for each TakeIterator.
        private Iterator<T > mIterator ;
        public TakeIterator() {
            mRemaining = mAmount ;
            mIterator = mSource.iterator();
        }
        public boolean hasNext () {
            return mRemaining > 0 && mIterator.hasNext ();
        }
        public T next () {
            -- mRemaining ;
            return mIterator.next();
        }
    }
}


/*
for ( Integer i : new TakeGenerator < Integer >(3 , new RangeGenerator (1 , 100000 , 5))) {
        System . out . println ( i );
        }*/
