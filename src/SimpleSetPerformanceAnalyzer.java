import java.util.*;

public class SimpleSetPerformanceAnalyzer {

    private CollectionFacadeSet[] collections;
    private OpenHashSet openHashTest = new OpenHashSet();
    private ClosedHashSet closedHashTest = new ClosedHashSet();
    private CollectionFacadeSet treeSetTest = new CollectionFacadeSet (new TreeSet<String>());
    private CollectionFacadeSet hashSetTest = new CollectionFacadeSet(new HashSet<String>());
    private CollectionFacadeSet linkedListTest = new CollectionFacadeSet(new LinkedList<String>());
    private static String[] data1 = Ex4Utils.file2array("data1.txt");
    private static String[] data2 = Ex4Utils.file2array("data2.txt");
    private Map<SimpleSet, String> setMap = new HashMap<>();
    private String[] TEST1 = {"hi", "-13170890158"};
    private String[] TEST2 = {"23", "hi"};

    public SimpleSetPerformanceAnalyzer() {
        setMap.put(openHashTest, "OpenHashSet");
        setMap.put(closedHashTest, "ClosedHashSet");
        setMap.put(treeSetTest, "TreeSetTest");
        setMap.put(hashSetTest, "HashSetTest");
        setMap.put(linkedListTest, "LinkedListTest");
    }

    public void runAnalysis(String[] data, String dataName, String[] testStr) {
        for (SimpleSet set: setMap.keySet()) {
            System.out.println(setMap.get(set) + " added " + dataName + " and it took " + measureAddData(set, data) + " ms");
        }
        System.out.println();
        for (SimpleSet set : setMap.keySet()) {
            for (String str : testStr) {
                System.out.println(setMap.get(set) + " checked if contains " + str +", "+ dataName + "and it took" + measureContains(set, data, str) + " ns");
            }
        }

        System.out.println();
    }

    public static void main(String[] strList) {
        SimpleSetPerformanceAnalyzer analyzer = new SimpleSetPerformanceAnalyzer();
        analyzer.runAnalysis(data1, "data1", analyzer.TEST1);
        analyzer = new SimpleSetPerformanceAnalyzer();
        analyzer.runAnalysis(data2, "data2", analyzer.TEST2);
    }


    /**
     * for ex. 1 and ex. 2 - Adding all the words in data1.txt and data2.txt one by one,
     * to each of the data structures (with default initialization) in separate.
     * This time should be measured in milliseconds (1ms = 10^-3 s).
     */
    public long measureAddData(SimpleSet set, String[] data) {
        long timeBefore = System.nanoTime();
        for (String str : data) {
            set.add(str);
        }
        long difference = System.nanoTime() - timeBefore;
        return difference/1000000;
    }

    /**
     * for ex. 3 - 6.
     */
    public long measureContains(SimpleSet set, String[] data, String searchVal) {
        long WARM_UP = 70000;
        if (!set.getClass().getName().equals("LinkedListTest")) {
            for (int i = 0; i <= WARM_UP; i++) {
                set.contains(searchVal);
            }
        }
        long sum = 0;
        if (set.getClass().getName().equals("LinkedListTest")) {
            for (int i=0; i <= 7000; i++) {
                long timeBefore = System.nanoTime();
                set.contains(searchVal);
                sum += System.nanoTime() - timeBefore;
            }
            return sum/7000;
        }
        for (int i=0; i <= WARM_UP; i++) {
            long timeBefore = System.nanoTime();
            set.contains(searchVal);
            sum += System.nanoTime() - timeBefore;
        }
        return sum/WARM_UP;
    }

}
