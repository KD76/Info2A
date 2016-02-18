package Collections;

public class TestClass3 {
    
    public static void main (String [] args) {

        ListBis L1 = new ListBis();
        Tree T1 = new Tree();

        int [] values = {100, 50, 150, 23, 45, 1, 73, 1000, 500, 2, 32, 34, 131, 3, 
        	17, 43, 42, 53, 873, 53, 2, 34, 35, 142, 862, 341, 51, 1, 321, 41};

        for (int i=0; i<values.length; ++i) {
        	L1.insert (values[i]);
        	T1.insert (values[i]);
        }
       
        int countlist=0, counttree=0;
        for (int i=0; i<values.length; ++i) {
            int count;
            System.out.println ("Searching for "+values[i]);
            count=L1.find(values[i]);
            if (count>=0) {
                System.out.println ("List: found after "+count+" iterations.");
                countlist+=count;
            }
            count=T1.find(values[i]);
            if (count>=0) {
                System.out.println ("Tree: found after "+count+" iterations.");
                counttree+=count;
            }
        }
        System.out.println ("======\nNumber of values in the structures: "+values.length);
        System.out.println ("Average number of iterations for list: "
        	+((float)countlist/(float)values.length));
        System.out.println ("Theoretical value: "+values.length+"/2="+values.length/2);
        System.out.println ("Average number of iterations for tree: "
        	+((float)counttree/(float)values.length));
        System.out.println ("Theoretical value (for balanced tree!) = "
        	+" log2("+values.length+")="+Math.log(values.length));
    }
}