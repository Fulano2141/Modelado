package Fhulano.Simulacion;

import java.util.ArrayList;

@SuppressWarnings({"MismatchedReadAndWriteOfArray", "MismatchedQueryAndUpdateOfCollection"})
public class geometrica {
    double med;
    double var;
    double cantidad;
//    int n = 40;

    public geometrica(double med, double var, double cantidad) {
        this.med = med;
        this.var = var;
        this.cantidad = cantidad;
    }

    public double getMed() {
        return med;
    }

    public void setMed(double med) {
        this.med = med;
    }

    public double getVar() {
        return var;
    }

    public void setVar(double var) {
        this.var = var;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<Double> listProb() {
//        double[][] tab = new double[50][2];
        ArrayList<Double> tab = new ArrayList<>();
        double p = 1 / getMed();
        double val ;
        double q = 1 - p;
        double acum = 0.0;
//        p = 0.6;
        System.out.println(" p = " + p);
        for (int i = 0; acum <= 0.9999; i++) {
            val = p * Math.pow(q, i);
            acum += val;
//            tab[i][0] = val;
//            tab[i][1] = acum;
            tab.add(val);
        }
        System.out.println(acum);
        return tab;
    }

    public void algo() {

//        double num = 1.0 - Math.pow(Math.E, -1.0 * equisD);
//        double num = 1.0 - Math.pow(Math.E, -3.0 * equisD);

        ArrayList<Integer> lista = new ArrayList<>();
        double[][] matrix = new double[10][10];
        for (int j = 0; j < 2500; j++) {
            int positionSeleccionable = 0;
            double numRandom01 = Math.random();
            double resultOfSeleccionable = Math.max(numRandom01, matrix[0][1]) - Math.min(numRandom01, matrix[0][1]);
            for (int i = 0; i < matrix.length; i++) {
                if ((Math.max(numRandom01, matrix[i][1]) - Math.min(numRandom01, matrix[i][1])) < resultOfSeleccionable) {
                    positionSeleccionable = i;
                    resultOfSeleccionable = matrix[i][1];
                }
/*                 if(formato1.format(matrix[i][1]).equals(formato1.format(numRandom01))){
                    lista.add(i);
                }*/
            }
            lista.add(positionSeleccionable);
        }
//        System.out.println(mostrarvector(lista));
    }

    public static void main(String[] args) {
        geometrica geo = new geometrica(1.3, 0.1, 10);
        ArrayList<Double> a = geo.listProb();

        geo.setMed(geo.getMed());
        geo.setCantidad(geo.getCantidad());
        geo.setVar(geo.getVar());

        geo.algo();

        System.out.println(a);
        System.out.println(a.size());
    }
}
