package Collections;

/**
 * Created by Kévin on 04/02/2016.
 */
public class Tree {

    public TreeElement root;

    public Tree() {

    }

    public int find(int value) {
        TreeElement current = root;
        int count = 0;
        while (current != null) {
            if (current.value == value) {
                return count;
            } else {
                if (value < current.value) {
                    current = current.left;
                } else if (value > current.value) {
                    current = current.right;
                }
                count++;
            }
        }
        return -1;
    }

    public void insert(int value) {
        if (root == null) {
            root = new TreeElement(value);
        } else {
            TreeElement current = root;
            TreeElement oldCurrent = root;
            while(current != null) {
                if (current.value == value) {
                    return;
                } else {
                    oldCurrent = current;
                    if (value < current.value) {
                        current = current.left;
                    } else if (value > current.value) {
                        current = current.right;
                    }
                }
            }
            if (value <= oldCurrent.value) {
                oldCurrent.left = new TreeElement(value);
            } else {
                oldCurrent.right = new TreeElement(value);
            }
        }
    }
}
