package Fhulano;

public class yuleWalker {
    double a1;
    double a2;
    double a3;

    public yuleWalker(double a1, double a2, double a3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public double getA1() {
        return this.a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return this.a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return this.a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    public double[][] gAmas(boolean imprimir) {
        double[][] aux;
        double[][] matA = { { getA1() - 1.0, getA3(), 0.0 }, { getA3() + getA1(), -1.0, 0.0 },
                { getA1(), getA2(), 1.0 }, };

        if (imprimir) {
            for (int i = 0; i < matA.length; i++) {
                for (int j = 0; j < matA[0].length; j++) {
                    System.out.print(Operaciones.redondearNum(matA[i][j]) + "\t");
                }
                System.out.println();
            }
        }
        matA = Operaciones.invert(matA);
        if (imprimir) {
            for (int i = 0; i < matA.length; i++) {
                for (int j = 0; j < matA[0].length; j++) {
                    System.out.print(Operaciones.redondearNum(matA[i][j]) + "\t");
                }
                System.out.println();
            }
        }
        double[][] an = { { -1.0 * getA1() }, { -1.0 * getA2() }, { -1.0 * getA3() }, };
        aux = Operaciones.multiplicacionNxM(matA, an);
        return aux;
    }

    @Override
    public String toString() {
        return "{" + " a1='" + Operaciones.redondearNum(getA1()) + "'" + ", a2='" + Operaciones.redondearNum(getA2())
                + "'" + ", a3='" + Operaciones.redondearNum(getA3()) + "'" + "}";
    }

}