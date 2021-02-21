import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListsWrapper {
    /** In order to implement the open-hashing set using linked lists,
     * this wrapper class helps us to do so.
     */

    LinkedList<String> linkedList = new LinkedList<String>();

    /**
     * delete from linked list.
     * @param toDelete - string to remove.
     * @return true if deleted, false otherwise.
     */
    public boolean deleteFromLinkedList(String toDelete) {
        return linkedList.remove(toDelete);
    }

    /**
     * Add to linked-list
     * @param toAdd - the requested string to add.
     */
    public void addToLinkedList(String toAdd) {
        linkedList.add(toAdd);
    }

    /**
     * This method checks if the requested string exist in the linked list.
     * @param searchval the requested string to check.
     * @return true if contains, false otherwise.
     */
    public boolean containsInLinkedList(String searchval) {
        return linkedList.contains(searchval);
    }

    /**
     * @return size of linked list.
     */
    public int size() {
        return linkedList.size();
    }

    /**
     * @return an array of strings that the linked list contains.
     */
    public String[] getStringsInLinkedList() {
        String[] res = new String[this.size()];
        for (int i=0; i <= linkedList.size(); i++) {
            res[i] = linkedList.element();
        }
//        int i = 0;
//        for (String str : linkedList) {
//            res[i] = str;
//            i++;
//        }
        return res;
    }

    /**
     * @return iterator of the strings in specific list.
     */
    public Iterator<String> getIterator() {
        return linkedList.iterator();
    }
}
