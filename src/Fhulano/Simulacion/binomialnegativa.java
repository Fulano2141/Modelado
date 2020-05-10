package Fhulano.Simulacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class binomialnegativa {
    double med, var, n;

    public binomialnegativa(double med, double var, double n) {
        this.med = med;
        this.var = var;
        this.n = n;
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

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public void algo() {
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

    public Map<Double, Double> mapProb() {
        Map<Double, Double> tab = new HashMap<Double, Double>();
        double p = getMed() / getVar();
        double k = Math.pow(getMed(), 2);
        k = k / (getVar() - getMed());
        double val = 0.0;
        double q = 1 - p;
        for (int i = 0; i <= 10; i++) {
//            double b = Math.pow(p, j) * Math.pow(q, n - j);
            val = combina((int) (k - i - 1), i) * Math.pow(p, k) * Math.pow(q, i);
            tab.put((i + 0.0), val);
        }
        return tab;
    }

    public ArrayList<Double> listProb() {
//        double[][] tab = new double[50][2];
        ArrayList<Double> tab = new ArrayList<Double>();
        double p = getMed() / getVar();
        double k = Math.pow(getMed(), 2);
        k = k / (getVar() - getMed());
        double val = 0.0;
        double q = 1 - p;
        double acum = 0.0;
        k = Math.floor(k);
        System.out.println(" p = " + p + " k = " + k);
        for (int i = 1; acum <= 0.90; i++) {

//            val = combina((int) (k - i - 1), i) * Math.pow(p, k) * Math.pow(q, i);
            val = combina((int) (k + i - 1.0), i) * Math.pow(p, k) * Math.pow(1.0 - p, i);
            acum += val;

//            tab[i][0] = val;
//            tab[i][1] = acum;
            tab.add(val);

        }
        System.out.println(acum);
        return tab;
    }

    private static int combina(int n, int r) {
        if (r == 0) {
            return 1;
        } else if (n == r) {
            return 1;
        } else if (r == 1) {
            return n;
        } else
            return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private static int factorial(int number) {
        int fact = 1;
        for (int i = 1; i <= number; i++) {
            fact = fact * i;
        }
//        if (number == 0)
//            return 1;
//        return number * factorial(number - 1);
        if (fact == 0) {
            fact = 1;
        }
        return fact;
    }

    public static void main(String[] args) {
        binomialnegativa bin = new binomialnegativa(2.2, 3.3, 10);
//        Map<Double, Double> nombreMap = new HashMap<Double, Double>();
//        nombreMap = geo.mapProbAcum();
//        Iterator it = nombreMap.entrySet().iterator();
//
//        while (it.hasNext()) {
//            Map.Entry e = (Map.Entry)it.next();
//            System.out.println(e.getKey() + " " + e.getValue());
//        }
        ArrayList<Double> a = bin.listProb();
        System.out.println(a);
        System.out.println(a.size());
    }
}
