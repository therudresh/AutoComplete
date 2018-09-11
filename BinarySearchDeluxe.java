import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

// Implements binary search for clients that may want to know the index of 
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, 
                                         Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new java.lang.NullPointerException();
        }

        if (a.length == 0) return -1;
        
        int x = 0;
        int len = a.length - 1;
        
        while (x + 1 < len) {
            int m = x + (len - x)/2;
            if (comparator.compare(key, a[m]) <= 0) {
                len = m;
            } 
    	    else {
    	        x = m;
    	    }
    	}
    	if (comparator.compare(key, a[x]) == 0) {
    	    return x;
    	}
    	if (comparator.compare(key, a[len]) == 0) {
    	    return len;
    	}
    	return -1;
    }

    // The index of the last key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, 
                                        Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new java.lang.NullPointerException();
        }

        if (a.length == 0) return -1;

        int x = 0;
        int len = a.length - 1;

        while (x + 1 < len) {
    		int m = x + (len - x)/2;
    		if (comparator.compare(key, a[m]) < 0) {
    			len = m;
    		} else {
    			x = m;
    		}
    	}
    	if (comparator.compare(key, a[x]) == 0) {
    		return x;
    	}
    	if (comparator.compare(key, a[len]) == 0) {
    		return len;
    	}
    	return -1;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        Arrays.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println(count);
    }
}
