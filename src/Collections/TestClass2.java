package Collections;

public class TestClass2 {
    
    public static void main (String [] args) {
        
        List L1 = new List();
		List L2 = new List();

        L1.insert (313);
        L1.insert (42);
        L1.insert (1973);
        
        L1.display ();
        
        int it=L1.find(42);

        if (it<0)
			System.out.println ("42 not found.");
		else
			System.out.println ("42 found after "+it+" steps.");        	

		L2.insert (42);
		L2.insert (37);
		L2.insert (0);

		L1.commonValues(L2).display();

		System.out.println("===");
		L1.display();

		L1.deleteFirst();	

		System.out.println("===");
		L1.display();

		L1.deleteValue(42);

		System.out.println("===");
		L1.display();
    }    
}
