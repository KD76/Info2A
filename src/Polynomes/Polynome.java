package Polynomes;

class Polynome {

    /**
     * C'est le tableau des coefficients de chaque degré du polynome. Il est ordonné selon les puissances croissantes (Ex : coeffs[0] correspond au terme constant,
     * coeffs[1] au terme devant x, etc.)
     */
    private double[] coeffs;

    /**
     * Ce n'était pas demandé dans le TP, mais je trouvais ça intéressant à rajouter:
     * C'est un emplacement pour stocker la primitive du polynôme. Plutôt que de la calculer à chaque fois qu'on en a besoin,
     * on la calcule une fois et on a stocke pour les prochaines occasions.
     */
    Polynome primitive;

    /**
     * Même principe que pour la primitive.
     */
    private Polynome derivee;

    /**
     * Constructeur principal du polynômes. Prend en paramètre un tableau de double, le copie et l'attribue au tableau coeffs du polynôme.
     * @param c Tableau des coefficients
     */
    public Polynome(double[] c) {
        coeffs = new double[c.length]; // On crée le tableau des coefficients du polynôme, de même taille que le tableau que l'on passe en paramètre.
        System.arraycopy(c, 0, coeffs, 0, c.length); // Méthode pour copier un tableau. C'est pas à connaître. Par contre, ce qu'il faut savoir, c'est l'autre méthode, à savoir:
        /**
         * for(int i=0; i<coeffs.length; i++) {
         *     coeffs[i] = c[i]
         * }
         */
    }

    /**
     * Méthode permettant de convertir l'objet Polynôme en chaîne de caractères.
     * Cette méthode est une "méthode magique", c'est à dire que lorsque l'on souhaite afficher l'objet poly en tapant
     * System.out.println(poly), Java va directement appeler cette méthode pour afficher le polynôme.
     * ATTENTION ! Cette méthode N'AFFICHE PAS LE POLYNOME ! Elle le transforme en chaîne de caractères, et c'est cette chaîne de caractères que l'on peut afficher !
     * @return Le polynôme formatté pour être affiché
     */
    public String toString() {
        String data = ""; // On crée une chaîne vide.
        for (int i = coeffs.length - 1; i > 0; i--) { // Pour tous les coefficients du tableau en partant du coeff le plus haut, jusqu'au coeff d'ordre 1
            data += " (" + coeffs[i] + ")"; // On ajoute à la chaîne le coefficient
            data += " x^" + i + " +"; // Puis, on ajoute le terme en x avec sa puissance (qui vaut i, par définition)
        }
        data += " (" + coeffs[0] + ")"; // Enfin on rajoute le terme constant
        return data; //On renvoie la châine de caractère.
    }

    /**
     * Permet de calculer la valeur du polynôme en x.
     * @param x Eh bien... c'est x.
     * @return Valeur du polynôme en x
     */
    public double valPoly(double x) {
        double val = 0; // On crée un double égal à 0.
        for (int i = 0; i < coeffs.length; i++) { // Pour chaque coefficient du tableau du polynôme
            val += coeffs[i] * Math.pow(x, i); // On ajoute au double de départ la valeur de x à la puissance i fois le coefficient coeffs[i]
        }
        return val; // On retourne la valeur.
    }

    /**
     * BONUS - Permet de calculer la valeur du polynôme via le schéma de Horner
     * @param x Bah... c'est toujours x.
     * @return Valeur du polynôme en X
     */
    public double horner(double x) {

        double val = 0; // Idem que méthode précédente

        for (int i = coeffs.length - 1; i > 0; i--) { // Idem méthode précédente
            val += coeffs[i]; // On ajoute au double de départ la valeur du coefficient coeffs[i]
            val *= x; // On multiplie val par x pour suivre le schéma de horner
        }

        val += coeffs[0]; // A la toute fin, on rajoute le dernier terme, à savoir le coefficient.

        return val;
    }

    /**
     * Dérive le polynôme.
     * @return Le polynôme dérivé.
     */
    public Polynome derivePoly() {
        if (derivee == null) { // Si la dérivée n'a pas encore été calculée (soit le polynôme derivee nul)
            double[] derivCoeffs = new double[coeffs.length - 1]; // On crée un tableau de coeffs ayant pour degré celui du polynôme moins un.
            for (int i = coeffs.length - 1; i >= 1; i--) { // Pour tous les coeffs du polynome de départ en partant des plus hauts, et en oubliant le dernier qui est le terme constant
                derivCoeffs[i - 1] = coeffs[i] * i; // On attribue au coefficient i-1 du polynôme dérivé la valeur du coefficient en i multiplié par la puissance du coeff, à savoir i.
            }
            derivee = new Polynome(derivCoeffs); // On construit le polynôme dérivée et on le stocke si on en a besoin pour plus tard.
        }
        return derivee; // On renvoit le polynôme dérivée.
    }

    public double polyNewton(double start, int precision) {
        Polynome derivPoly = derivePoly();
        double tenPrecision = Math.pow(10, -1 * precision);


        double solution = start;
        double oldVal;
        do {
            oldVal = solution;
            solution = oldVal - valPoly(oldVal) / derivPoly.valPoly(oldVal);
        } while (Math.abs(solution - oldVal) > tenPrecision);

        return solution;
    }

    public double integrerRectangle(double a, double b, int N) {
        double delta = (b - a) / (double) N;
        double val = 0;

        for (int i = 0; i < N; i++) {
            val += horner((0.5 + i) * delta + a);
        }

        val *= delta;

        return val;
    }

    public Polynome primitivePoly() {
        if (this.primitive == null) {
            double[] primitive = new double[coeffs.length + 1];

            for (int i = 0; i < coeffs.length; i++) {
                primitive[i + 1] = coeffs[i] / (i + 1);
            }
            primitive[0] = 0;

            this.primitive = new Polynome(primitive);
        }
        return primitive;
    }

    public double integrerPoly(double a, double b) {
        Polynome prim = primitivePoly();

        return prim.horner(b) - prim.horner(a);
    }

    public static void main(String[] args) {
        double[] tab = {-6, -8, 1, 4, 1};
        Polynome pol = new Polynome(tab);
        System.out.println(pol);
        double val1 = pol.polyNewton(-4, 7);
        double val2 = pol.polyNewton(-2, 15);
        double val3 = pol.polyNewton(0, 15);
        double val4 = pol.polyNewton(2, 15);
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(val3);
        System.out.println(val4);

        System.out.println("f(5) = " + pol.horner(5));

        System.out.println("Intégration de f sur [0,1] avec N = 20 : " + pol.integrerRectangle(0, 1, 600));
        System.out.println("Une primitive du polynome: " + pol.primitivePoly());
        System.out.println("Valeur exacte de l'intégrale: " + pol.integrerPoly(0, 1));

    }

}
