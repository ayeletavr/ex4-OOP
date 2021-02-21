public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet {
    /**
     * A superclass for implementations of hash-sets implementing the SimpleSet interface.
     */

    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;
    protected static int INITIAL_CAPACITY = 32; // we divide it by two later.
    protected int capacity;
    protected float lowerLoadFactor;
    protected float upperLoadFactor;
    protected int size = 0;
    protected int capacityMinusOne; // we need this for clamping.

    // Constructors.

    /**
     * Constructs a new hash set with the default capacities
     * given in DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    protected SimpleHashSet() {
        capacity = INITIAL_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor - the upper load factor before rehashing.
     * @param lowerLoadFactor - the lower load factor before rehashing.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    // Methods.

    /**
     * @return the current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity
     * @param index - the index before clamping.
     * @return an index properly clamped.
     */
    protected int clamp(int index) {
        return index & (this.capacityMinusOne);
    };

    /**
     * @return the lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return this.lowerLoadFactor;
    }

    /**
     * @return the higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return this.upperLoadFactor;
    }

    /**
     * capacityMinusOne field helps us calculate the clamping index.
     * This method updates this field.
     */
    protected void setCapacityMinusOne(int newCapacity) {
        capacityMinusOne = newCapacity -1;
    }

    /**
     * @return true if current load factor crossed the upper limit, false otherwise.
     */
    protected boolean overLoading() {
        return (size() / (float) (capacityMinusOne+1) > getUpperLoadFactor());
    }

    /**
     * @return true if current load factor crossed the lower limit, false otherwise.
     */
    protected boolean underLoading() {
        return (size() / (float) (capacityMinusOne+1) < getLowerLoadFactor());
    }
}
