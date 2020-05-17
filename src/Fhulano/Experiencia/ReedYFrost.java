package Fhulano.Experiencia;

import Fhulano.Simulacion.bernoulli;

public class ReedYFrost {
    int S, E, I;
    double k;
    double[][] data;

    public ReedYFrost(int s, int e, int i, double k) {
        S = s;
        E = e;
        I = i;
        this.k = k;
        data = datos();
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }


    public double[][] datos() {
        bernoulli be = new bernoulli(getP(), 10);

        double[][] aux = new double[20][4];
        aux[0][0] = 1;
        aux[0][1] = Math.round(getS() - getE());
        aux[0][2] = Math.round(getE());
        aux[0][3] = Math.round(getI());
        // Poblacion total = S + Inmunes;
        // Inmunes = Inmunes t-1 + Enfermos;
        // Enfermos = S * (1 - (q^ Enfermos)
        int con = 0;
        for (int i = 1; i < aux.length; i++) {
            aux[i][0] = i + 1;
//            con++;
//            if (con == 15) {
//                double sum = 0.0;
//                for (int j = 0; j < i; j++) {
//                    sum += be.siguienteDato() * (int) Math.round(aux[j][1] * (1 - Math.pow(getQ(), aux[j][2])));
//                }
//                aux[i][2] = sum;
//                con = 0;
//                System.out.println();
//            } else {
            aux[i][2] = aux[i - 1][1] * (1 - Math.pow(getQ(), aux[i - 1][2]));
//            }
//                    + Math.log(1.0 / (getQ()));
            aux[i][3] = aux[i][2] + aux[i - 1][3];
            aux[i][1] = getS() - aux[i][3];

            if (aux[i][1] < 0.0) {
                break;
            }
        }

        return aux;
    }

    private double getQ() {
        return 1.0 - getP();
    }

    private double getP() {
        return k / (getS() - 1.0);
    }
}
