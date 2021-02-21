public class ClosedHashSet extends SimpleHashSet {

    /** String Array of hash table. */
    public Object[] closedHashTable;

    /**Flag for delete method. */
    public final static Object FLAG = null;

    public final double CLOSED_CLAMPING_CONST = 0.5;

    // Constructors.

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        closedHashTable = new String[INITIAL_CAPACITY];
        setCapacityMinusOne(INITIAL_CAPACITY);
    }

    /**
     * A default constructor.
     * Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        closedHashTable = new String[INITIAL_CAPACITY];
        setCapacityMinusOne(INITIAL_CAPACITY);
    }

    /** Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data - data of string to hash.
     */
    public ClosedHashSet(java.lang.String[] data) {
        closedHashTable = new String[INITIAL_CAPACITY];
        setCapacityMinusOne(INITIAL_CAPACITY);
        if (data.length/(float) capacity() > lowerLoadFactor) { // there is no need to rehash and resize.
            for (String str : data) {
                if (str != null) {
                    closedHashTable[clampforClosed(str)] = str;
                    size++;
                }
            }
        }
        for (String str : data) { // there might be need to rehash and resize.
            if (str != null) {
                add(str);
            }
        }
    }


    /**
     * @return the current capacity (number of cells) of the table.
     */
    public int capacity() {
        return this.closedHashTable.length;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return false if newValue already exist in the set.
     */
    public boolean add(String newValue) {
        int newValHashCode = clampforClosed(newValue);
        if (newValue == null || contains(newValue )|| newValHashCode == -1) {
            return false;
        }
        closedHashTable[newValHashCode] = newValue;
        size++;
        resizeAndRehash();
        return true;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return true if searchVal is found in the set.
     */
    public boolean contains(String searchVal) {
        if (searchVal == null) {
            return false;
        }
        return getStringIndex(searchVal) != -1;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return true if toDelete is found and deleted.
     */
    public boolean delete(String toDelete) {
        int toDeleteHashCode = getStringIndex(toDelete);
        if (toDelete == null || toDeleteHashCode == -1) {
            return false;
        }
        closedHashTable[toDeleteHashCode] = (String) FLAG;
        size -= 1;
        if (underLoading())
        {
            resizeAndRehash();
        }
        return true;
    }

    /**
     * @return the number of elements currently in the set.
     */
    public int size() {
        return size;
    }

    /**
     * when the method is called, it resize and rehashes the hashTable.
     */
    protected void resizeAndRehash() {
        if (closedHashTable.length == 1) {
            return;
        }
        // resizing.
        Object[] oldHashTable = closedHashTable;
        if (overLoading()) {
            setCapacityMinusOne(closedHashTable.length * 2);
            closedHashTable = new Object[closedHashTable.length * 2];
        }
        else if (underLoading()) {
            setCapacityMinusOne(closedHashTable.length / 2);
            closedHashTable = new Object[closedHashTable.length / 2];
        }
        else {
            return;
        }
        // rehashing.
        for (Object str : oldHashTable) {
            if (str != null && clampforClosed(str.toString()) != -1) {
                closedHashTable[clampforClosed(str.toString())] = str.toString();
            }
        }

    }

    /**
     * This method fixes the clamping method for closed hashSets.
     * @param str the str to find the correct index in the set.
     * @return index if found, -1 otherwise.
     */
    protected int clampforClosed(String str) {
        if (str == null || size() == capacity()) {
            return -1;
        }
        int counter = 0;
        int findHashCode = clamp((int) ( str.hashCode() + CLOSED_CLAMPING_CONST * (counter * counter + counter)));
        while (counter < capacity()) {
            if (closedHashTable[findHashCode] == null || closedHashTable[findHashCode] == FLAG) {
                return findHashCode;
            }
            counter++;
            findHashCode = clamp((int) (str.hashCode() + CLOSED_CLAMPING_CONST * (counter * counter + counter)));
        }
        return -1;
    }

    /**
     * Checks if the specified string in set.
     * @param searchVal - the requested string.
     * @return the index if found, -1 otherwise.
     */
    protected int getStringIndex(String searchVal) {
        if (searchVal == null) {
            return -1;
        }
        for (int i=0; i < capacity(); i++) {
            if (closedHashTable[i] == null) {
                continue;
            }
            if (closedHashTable[i].equals(searchVal)) {
                return i;
            }
        }
        return -1;
    }

}
