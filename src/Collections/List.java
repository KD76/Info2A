package Collections;

/**
 * Created by Kévin on 04/02/2016.
 */
public class List {

    private int[] tab;
    private int insertPos = 0;

    public List(int capacity) {
        tab = new int[capacity];
    }

    public List() {
        tab = new int[0];
    }

    public void insert(int val) {
        if (tab.length <= insertPos) {
            int[] newTab = new int[insertPos+1];
            System.arraycopy(tab, 0, newTab, 0, tab.length);
            tab = newTab;
        }
        tab[insertPos] = val;
        insertPos++;
    }

    public String toString() {
        String val = "";
        val += "[";
        for (int i=0; i<tab.length; i++) {
            val += ""+tab[i]+((i==tab.length-1)?"":",");
        }
        val += "]";

        return val;
    }

    public void display() {
        System.out.println(this);
    }

    public int find(int element) {
        for(int i=0; i<tab.length; i++) {
            if (tab[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public void deleteFirst() {
        if (insertPos > 0) {
            insertPos--;
            System.arraycopy(tab, 1, tab, 0, tab.length-1);
            tab[tab.length-1] = 0;
        }
    }

    public boolean deleteValue(int value) {
        int pos = find(value);
        if (pos < 0) {
            return false;
        }
        for (int i=pos;i<insertPos; i++) {
            if (i+1 < insertPos) {
                tab[i] = tab[i+1];
            } else {
                tab[i] = 0;
            }
        }
        insertPos--;
        return true;
    }

    public void commonValues(List otherList) {
        List commonList = new List(0);
        for (int i=0; i<tab.length; i++) {
            int index = otherList.find(tab[i]);
            if (index >= 0) {
                commonList.insert(tab[i]);
            }
        }
        commonList.display();
        //return commonList;
    }

}
