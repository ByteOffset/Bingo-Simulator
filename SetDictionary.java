import java.util.*;

/**
 *
 * @author Preston Peterson
 * @version 11/01/2015
 */

public class SetDictionary<K extends Comparable<? super K>> implements SetInterface<K>, Iterable<K>
{
    private TreeMap<K, Boolean> items;

    public SetDictionary()
    {
        this.items = new TreeMap<>();
    } // end default constructor

    public boolean add(K newEntry)
    {
        return this.items.put(newEntry, true) == null;
    } // end add

    public boolean remove(K anEntry)
    {
        return this.items.remove(anEntry) != null;
    } // end remove

    public void clear()
    {
        this.items.clear();
    } // end clear

    public boolean contains(K anEntry)
    {
        return this.items.get(anEntry) != null;
    } // end contains

    public int getCurrentSize()
    {
        return this.items.size();
    } // end getCurrentSize

    public boolean isEmpty()
    {
        return this.items.size() == 0;
    } // end isEmpty

    public Iterator<K> getIterator() //defined in SetInterface. implement getIterator. have iterator() call it
    {
        //use keySet from TreeMap to create iterator
        Set<K> set = items.keySet();
        return set.iterator();
    } // end getIterator

    public Iterator<K> iterator() //defined in iterable interface
    {
        //call getIterator()
        return getIterator();
    } // end iterator

    public K[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")

        K[] result = (K[]) new Comparable[getCurrentSize()]; // unchecked cast

        Iterator<K> iter = iterator();
        int i = 0;
        while (iter.hasNext()) {
            result[i] = iter.next();
            i++;
        }

        //MUST BE IMPLEMENTED WITH ITERATOR
        return result;
    } // end toArray

    public SetInterface<K> union(SetInterface<K> otherSet)
    {
        SetInterface<K> result = new SetDictionary<>();
        Iterator<K> iterThis = iterator();
        Iterator<K> iterOther = otherSet.getIterator();
        while (iterThis.hasNext()) {
            result.add(iterThis.next());
        }
        while (iterOther.hasNext()) {
            result.add(iterOther.next());
        }

        //MUST BE IMPLEMENTED WITH ITERATORS

        return result;
    } // end union

    public SetInterface<K> intersection(SetInterface<K> otherSet)
    {
        SetInterface<K> result = new SetDictionary<>();
        Iterator<K> iterThis = iterator();
        Iterator<K> iterOther = otherSet.getIterator();
        K iterThisValue = iterThis.next();
        K iterOtherValue = iterOther.next();
        int difference;
        try {
            while (true) {
                difference = iterThisValue.compareTo(iterOtherValue);
                //call compare to only once and save the values. based on the values progress both iterators or only one
                if (difference == 0) {
                    result.add(iterThisValue);
                    iterThisValue = iterThis.next();
                    iterOtherValue = iterOther.next();
                }
                else if (difference > 0)
                    iterOtherValue = iterOther.next();
                else
                    iterThisValue = iterThis.next();
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("Finished creating intersection.");
        }
        //MUST BE IMPLEMENTED WITH ITERATORS

        // UTILIZE TRY_CATCH BLOCK
//        try
//        {
//            while (true)
//            {
//              progress iterator without checking for hasNext.
//              when an exception is generated it will be caught and the loop will terminate
//
//
//
//            } // end while
//        } catch (NoSuchElementException nsee)
//        {
//            System.out.println("Finished creating intersection.");
//        }

        return result;
    } // end intersection

    public static void main(String args[])
    {
        System.out.println("CREATING set1");
        SetInterface<Integer> set1 = new SetDictionary<>();
        set1.add(1);
        set1.add(3);
        set1.add(2);
        set1.add(0);
        set1.add(-1);

        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->contains for -1 yields " + set1.contains(-1));
        System.out.println("-->contains for -2 yields " + set1.contains(-2));
        System.out.println("-->contains for 3 yields " + set1.contains(3));
        System.out.println("-->contains for 4 yields " + set1.contains(4));

        set1.add(1);
        System.out.println("-->Added 1 again to the set1, should be the same");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        Iterator<Integer> kIter = set1.getIterator();
        System.out.println("-->Iterating over the set1 ");

        while (kIter.hasNext())
        {
            System.out.println("\t" + kIter.next());
        } // end while

        System.out.println("-->Removing -1 20 3 from set1");
        set1.remove(-1);
        set1.remove(20);
        set1.remove(3);

        System.out.println("-->Should just have 0 1 and 2 now");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("CREATING set2");
        SetInterface<Integer> set2 = new SetDictionary<>();
        set2.add(1);
        set2.add(3);
        set2.add(2);
        set2.add(-1);
        set2.add(5);
        set2.add(8);

        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING UNION OF set1 and set2");
        SetInterface<Integer> testUnion = set1.union(set2);
        System.out.print("-->The union of set1 and set2 has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set1 and set2");
        SetInterface<Integer> testIntersection = set1.intersection(set2);
        System.out.print("-->The intersection of set1 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set1");
        testIntersection = set2.intersection(set1);
        System.out.print("-->The intersection of set2 and set1 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set2");
        testIntersection = set2.intersection(set2);
        System.out.print("-->The intersection of set2 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF testUnion and set2");
        testIntersection = testUnion.intersection(set2);
        System.out.print("-->The intersection of testUnion and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->testUnion should be unchanged");
        System.out.println("-->testUnion has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();
    } // end main
}
