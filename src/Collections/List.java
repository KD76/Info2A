package Collections;

/**
 * Created by Kévin on 04/02/2016.
 */
public class List {

    private ListElement root;

    public List() {

    }

    public void insert(int v) {
        if (root == null) {
            root = new ListElement(v);
        } else {
            ListElement current = root;
            while (current != null && current.next != null) {
                current = current.next;
            }
            current.next = new ListElement(v);
        }
    }

    public String toString() {
        String string = "[";
        ListElement current = root;
        do {
            string += (current.value)+((current.next == null)?"":", ");
            current = current.next;
        } while (current != null);
        string += "]";

        return string;
    }

    public void display() {
        System.out.println(this);
    }

    public void insertFirst(int v) {
        if (root == null) {
            root = new ListElement(v);
        } else {
            ListElement list = new ListElement(v);
            list.next = root;
            root = list;
        }
    }

    public int find(int val) {
        int count = 0;
        ListElement current = root;
        while (current != null) {
            if (current.value == val) {
                return count;
            } else {
                count++;
                current = current.next;
            }
        }
        return -1;
    }

    public void deleteFirst() {
        if (root != null) {
            root = root.next;
        }
    }

    public void deleteValue(int value) {
        ListElement current = root;
        if (current.value == value) {
            root = root.next;
            return;
        }
        while(current.next != null) {
            if (current.next.value == value) {
                current.next = current.next.next;
                return;
            }
        }
    }

    public List commonValues(List list) {
        List common = new List();

        ListElement current = root;
        do {
            int index = list.find(current.value);

            if (index > -1) {
                common.insert(current.value);
            }
            current = current.next;
        } while (current != null);

        return common;
    }

}
