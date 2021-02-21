import java.util.Iterator;

public class OpenHashSet extends SimpleHashSet {

    /** Array of linked lists. */
    protected LinkedListsWrapper[] openHashTable;


    // Constructors.

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        setCapacityMinusOne(INITIAL_CAPACITY);
        openHashTable = new LinkedListsWrapper[INITIAL_CAPACITY];
    }

    /**
     * A default constructor.
     * Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        openHashTable = new LinkedListsWrapper[INITIAL_CAPACITY];
        setCapacityMinusOne(INITIAL_CAPACITY);
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        setCapacityMinusOne(INITIAL_CAPACITY);
        openHashTable = new LinkedListsWrapper[INITIAL_CAPACITY];
        if ((data.length / (float) capacity()) > getLowerLoadFactor()) { // the loadFactor are in the valid range,
            // and there is np need to rehash.
            for (String str : data) {
                if (str != null) {
                    hash(str);
                }
            }
        }
        for (String str : data) { // there might be over/under loading.
            if (str != null) {
                add(str);
            }
        }
    }

    // Methods.

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return false if newValue is already exists in the set.
     */
    public boolean add(java.lang.String newValue) {
        if (this.contains(newValue)) {
            return false;
        }
        hash(newValue);
        resizeAndRehash();
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal) {
        return (openHashTable[clamp(searchVal.hashCode())] != null
               && openHashTable[clamp(searchVal.hashCode())].containsInLinkedList(searchVal));
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted.
     */
    public boolean delete(java.lang.String toDelete) {
        if (toDelete == null ||openHashTable[clamp(toDelete.hashCode())] == null ||
                !openHashTable[clamp(toDelete.hashCode())].deleteFromLinkedList(toDelete)) {
            return false;
        }
        size -= 1;
        resizeAndRehash();
        return true;
    }

    /**
     * @return The number of elements currently in the set.
     */
    public int size() {
        return size;
    }

    /**
     * @return The current capacity (number of cells) in the table.
     */
    public int capacity() {
        return openHashTable.length;
    }


    /**
     * gets a string and hashes it to the hash Set.
     * @param str - A string to hash.
     */
    protected void hash(String str) {
        int correctHashCode = clamp(str.hashCode());
        if (openHashTable[correctHashCode] == null) {
            openHashTable[correctHashCode] = new LinkedListsWrapper(); {
            }
        }
        openHashTable[correctHashCode].addToLinkedList(str);
        size ++;
    }

    /**
     * when the method is called, it resize and rehashes the hashTable.
     */
    protected void resizeAndRehash() {
        if (capacity() == 1) {
            return; // so that the table won't diappear.
        }
        //resizing.
        LinkedListsWrapper[] oldHashTable = openHashTable;
        if (overLoading()) {
            setCapacityMinusOne(capacity() * 2);
            openHashTable = new LinkedListsWrapper[capacity() * 2];
        }
        else if (underLoading()) {
            setCapacityMinusOne(capacity() / 2);
            openHashTable = new LinkedListsWrapper[capacity() / 2];
        }
        else {
            return;
        }
        //rehashing.
        size = 0;
        for (LinkedListsWrapper linkedList : oldHashTable) {
            if (linkedList == null) {
                continue;
            }
            Iterator<String> linkedListIterator = linkedList.getIterator();
            while (linkedListIterator.hasNext()) {
                hash(linkedListIterator.next());
            }
        }

    }
}
