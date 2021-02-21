import org.junit.*;
import static org.junit.Assert.*;

public class HomeMadeTester {

    private String[] shortStringList = {"a", "b", "c", "d", "e"};
    private String[] stringList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q"};
    private ClosedHashSet closedTable;
    private OpenHashSet openTable;

    /**
     * when starting a test, the tables already contain the shortStringList
     */
    /*@Before
    public void doBefore() {
        this.closedTable = new ClosedHashSet(shortStringList);
        this.openTable = new OpenHashSet(shortStringList);
    }*/

    @Test
    public void closedHashDefaultConstructor() {
        ClosedHashSet hashTable = new ClosedHashSet();
        for (String s : shortStringList) {
            hashTable.add(s);
        }
        assertEquals(hashTable.size(), shortStringList.length);
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s));
        }
    }

    @Test
    public void closedHashDataConstructor() {
        ClosedHashSet hashTable = new ClosedHashSet();
        for (String s : shortStringList) {
            hashTable.add(s);
        }
        assertEquals(hashTable.size(), shortStringList.length);
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s));
        }
    }

    @Test
    public void openHashDefaultConstructor() {
        OpenHashSet hashTable = new OpenHashSet(shortStringList);
        assertEquals(hashTable.size(), shortStringList.length);
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s));
        }
    }

    @Test
    public void openHashDataConstructor() {
        OpenHashSet hashTable = new OpenHashSet(shortStringList);
        assertEquals(hashTable.size(), shortStringList.length);
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s));
        }
    }

    @Test
    public void closedHashAdding() {
        SimpleHashSet hashTable = new ClosedHashSet();
        String val = "hi";
        hashTable.add(val);
        assertTrue(hashTable.contains(val));
        assertEquals(hashTable.size(),1);
    }

    @Test
    public void openHashAdding() {
        SimpleHashSet hashTable = new OpenHashSet();
        String val = "hi";
        hashTable.add(val);
        assertTrue(hashTable.contains(val));
        assertEquals(1, hashTable.size());
    }

    /**
     * adding the same values again doesn't make a difference
     */
    @Test
    public void closedHashAddingSameValue() {
        SimpleHashSet hashTable = closedTable;
        for (String s : shortStringList) {
            hashTable.add(s);
        }
        assertEquals(hashTable.size(), shortStringList.length); //the size didn't change
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s)); // the values didn't change
        }
    }

    /**
     * adding the same values again doesn't make a difference
     */
    @Test
    public void openHashAddingSameValue() {
        SimpleHashSet hashTable = openTable;
        for (String s : shortStringList) {
            hashTable.add(s);
        }
        assertEquals(hashTable.size(), shortStringList.length); //the size didn't change
        for (String s : shortStringList) {
            assertTrue(hashTable.contains(s)); // the values didn't change
        }
    }

    @Test
    public void openHashDeleting() {
        SimpleHashSet hashTable = openTable;
        String val = shortStringList[0];
        boolean delete = hashTable.delete(val);
        assertEquals(hashTable.size(), shortStringList.length-1);
        assertTrue(!hashTable.contains(val));
        assertTrue(delete);
        // trying to delete an item that is not in table:
        delete = hashTable.delete(val);
        assertEquals(hashTable.size(), shortStringList.length-1);
        assertTrue(!hashTable.contains(val));
        assertFalse(delete);
    }

    @Test
    public void closedHashDeleting() {
        SimpleHashSet hashTable = closedTable;
        String val = shortStringList[0];
        boolean delete = hashTable.delete(val);
        assertEquals(hashTable.size(), shortStringList.length-1);
        assertTrue(!hashTable.contains(val));
        assertTrue(delete);
        // trying to delete an item that is not in table:
        delete = hashTable.delete(val);
        assertEquals(hashTable.size(), shortStringList.length-1);
        assertTrue(!hashTable.contains(val));
        assertFalse(delete);
    }

    @Test
    public void openHashContains() {
        SimpleHashSet hashTable = new OpenHashSet();
        hashTable.add("a");
        assertTrue(hashTable.contains("a"));
        assertFalse(hashTable.contains("b"));
    }

    @Test
    public void closedHashContains() {
        SimpleHashSet hashTable = new ClosedHashSet();
        hashTable.add("a");
        assertTrue(hashTable.contains("a"));
        assertFalse(hashTable.contains("b"));
    }

    @Test
    public void checkDifferentLoadFactors() {
        SimpleHashSet hashTable = new OpenHashSet(0.7f, 0.1f);
        hashTable.add("8");
        assertEquals(false, hashTable.add("8"));
    }


    @Test
    public void checkStringArrayConstructor() {
        this.closedTable = new ClosedHashSet(shortStringList);
        this.openTable = new OpenHashSet(shortStringList);
        System.out.println();
    }
}

