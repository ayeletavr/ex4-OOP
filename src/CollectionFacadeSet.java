public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {

    protected java.util.Collection<java.lang.String> collection;

    // Constructors.

    /** creates a new facade wrapping the specified collection. /
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        this.collection = collection;
        for (String str : collection) {
            if (contains(str) || str == null) {
                continue;
            }
            add(str);
        }
    }


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return false if newValue already exist in the set.
     */
    public boolean add(String newValue) {
        if (!collection.contains(newValue) && collection.add(newValue)) {
            return true;
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return true if searchVal is found in the set.
     */
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted.
     */
    public boolean delete(String toDelete) {
        if (!this.contains(toDelete)) {
            return false;
        }
        this.collection.remove(toDelete);
        return true;
    }

    /**
     * @return The number of elements currently in the set.
     */
    public int size() {
        return this.collection.size();
    }
}
