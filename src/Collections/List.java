package Collections;

/**
 * Created by Kévin on 04/02/2016.
 */
public class List {

    /**
     * Racine de la liste. En clair, l'élément "premier" de la liste.
     */
    private ListElement root;

    /**
     * Effectivement, on a rien à construire. Le premier élément inséré sera la racine, puis les suivants viendront s'ajouter.
     * Sûrement le constructeur le plus utile de tous les temps.
     */
    public List() {

    }

    /**
     * Insère un entier en FIN de liste.
     * @param v Entier à insérer.
     */
    public void insert(int v) {
        if (root == null) { // Si la racine n'est pas encore définie...
            root = new ListElement(v); // ... On encapsule l'entier dans un "élément de liste" et on définit cet élément comme racine de la liste.
                                       // Si vous ne comprenez pas cette notion d'élément de liste, allez voir le fichier ListElement pour plus d'informations.
        } else { // Sinon...
            ListElement current = root; // On entame une "récursion" dans la liste. En clair, on explore tous les éléments de la liste jusqu'à ce que l'élément actuel n'ait plus de "voisin".
                                        // Auquel cas, on définira l'entier à insérer comme son voisin.
            while (current != null && current.next != null) { // Tant que l'élément courant n'est pas nul et que son voisin n'est pas nul... (NB: Le current != null est juste une condition pour éviter les NullPointerException.)
                current = current.next; // C'est qu'il y a encore des éléments devant nous. Nous ne sommes pas à la fin de la liste. On continue...
            }

            // Le while vient de se terminer. Cela signifie que l'élément actuel ("current") n'a pas de voisin.

            current.next = new ListElement(v); // Eh bien, s'il n'a pas de voisin, on va lui en donner un :) - On a fini l'insertion !
        }
    }

    /**
     * Petite méthode sympathique pour afficher la liste comme il se doit.
     * @return Une belle liste formattée.
     */
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

    /**
     * Allez, on a la flemme, on fait une petite méthode oklm pour afficher la liste.
     */
    public void display() {
        System.out.println(this);
    }

    /**
     * Au lieu d'insérer l'élément à la fin, on l'ajoute au début. Bim.
     * @param v Élément à insérer
     */
    public void insertFirst(int v) {
        if (root == null) { // Si on a pas d'élément racine...
            root = new ListElement(v); //... Bah c'est rapide, l'élément devient la racine.
        } else { // Sinon... (En vérité c'est pas plus dur lol)
            ListElement list = new ListElement(v); // On encapsule l'élément dans un "élément de liste".
            list.next = root; // On dit que cet élément a plus élément suivant, l'actuel premier élément de la liste.
            root = list; // Enfin, on définit le nouveau premier élément de la liste comme étant l'élément fraîchement rajouté.
        }
    }

    /**
     * Trouver une valeur dans la liste (Attention, On est des fous, on renvoie même la position de l'élément !)
     * @param val Valeur recherchée
     * @return Position de l'élément recherché. Si l'élément n'existe pas dans la liste, renvoie -1
     */
    public int find(int val) {
        int count = 0; // Au commencement, il n'y avait aucun élément compté...
        ListElement current = root; // Et soudainement, la racine arriva.
        while (current != null) { // Son ordre se répandit et fut considéré comme parole d'évangile. Une boucle se créa, et avec elle l'ordre si bien nommé apparu: "Tant que l'élément actuel n'est pas null, cherchez l'élément"
                                  // En clair, si l'élément actuel en cours d'étude est null, ça signfie que l'on est arrivé en fin de tableau. Mais je m'égare dans cette belle histoire.
            if (current.value == val) { // La boucle se mit à chercher. Et si elle trouvait...
                return count; // Elle n'avait qu'à renvoyer cette sainte position.
            } else { // Sinon...
                count++; // Elle continuait son chemin, incrémentant son compteur pour savoir qu'elle avançait d'une position dans le tableau.
                current = current.next; // Enfin, elle alla voir l'élément suivant.
            }
        }
        return -1; // Et si finalement, la valeur tant recherchée n'était que simple légende, elle n'avait qu'à renvoyer -1.
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
