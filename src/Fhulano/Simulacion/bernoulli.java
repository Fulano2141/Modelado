package Fhulano.Simulacion;

public class bernoulli {
    double p;
    int n;
    int[] numbers;
    double[][] tProb;

    public bernoulli(double p, int n) {
        this.p = p;
        this.n = n;
        tProb = new double[2][3];
        numbers = new int[n];
        updateProb();
        updateNumbers();
    }

    public void updateNumbers() {
        int[] aux = new int[n];
        double[][] matrix = tProb;
        for (int j = 0; j < getN(); j++) {
            int positionSeleccionable = 0;
            double numRandom01 = Math.random();
            double resultOfSeleccionable = Math.max(numRandom01, matrix[0][1]) - Math.min(numRandom01, matrix[0][1]);
            for (int i = 0; i < matrix.length; i++) {
                if ((Math.max(numRandom01, matrix[i][1]) - Math.min(numRandom01, matrix[i][1])) < resultOfSeleccionable) {
                    positionSeleccionable = i;
                    resultOfSeleccionable = matrix[i][1];
                }
            }
            //lista.add(positionSeleccionable);
            aux[j] = positionSeleccionable;
        }
        setNumbers(aux);
    }


    public void updateProb() {
        double[][] matrix = tProb;
        double fx = 0.0;
        for (int i = 0; i < 2; i++) {
            double px = (Math.pow(getP(), i) * Math.pow(1 - getP(), 1 - i));
            fx += px;
            matrix[i][0] = i;
            matrix[i][1] = px;
            matrix[i][2] = fx;
        }
        settProb(matrix);
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public double[][] gettProb() {
        return tProb;
    }

    public void settProb(double[][] tProb) {
        this.tProb = tProb;
    }

    public int siguienteDato() {
        return getNumbers()[(int) (Math.random() * getNumbers().length)];
    }
}
