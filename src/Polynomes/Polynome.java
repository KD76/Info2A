package Polynomes;

class Polynome {

    private double[] coeffs;
    private Polynome primitive;
    private Polynome derivee;

    public Polynome(double[] c) {
        coeffs = new double[c.length];
        System.arraycopy(c, 0, coeffs, 0, c.length);
    }

    public String toString() {
        String data = "";
        for (int i = coeffs.length - 1; i > 0; i--) {
            data += " (" + coeffs[i] + ")";
            data += " x^" + i + " +";
        }
        data += " (" + coeffs[0] + ")";
        return data;
    }

    public double valPoly(double x) {
        double val = 0;
        for (int i = 0; i < coeffs.length; i++) {
            val += coeffs[i] * Math.pow(x, i);
        }
        return val;
    }

    public double horner(double x) {

        double val = 0;

        for (int i = coeffs.length - 1; i > 0; i--) {
            val += coeffs[i];
            val *= x;
        }

        val += coeffs[0];

        return val;
    }

    public Polynome derivePoly() {
        if (derivee == null) {
            double[] derivCoeffs = new double[coeffs.length - 1]; // Suppression d'un degré.
            for (int i = coeffs.length - 1; i >= 1; i--) {
                derivCoeffs[i - 1] = coeffs[i] * i;
            }
            derivee = new Polynome(derivCoeffs);
        }
        return derivee;
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
