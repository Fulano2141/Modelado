package Fhulano.AutomatasCelulares;

import javax.swing.*;

public class ejer1 {


    public static void main(String[] args) {

        Regla R = new Regla();
        int NumBinario[] = new int[8];
        int regla;
        String numRegla = "";
        do {
            do {
                numRegla = JOptionPane.showInputDialog("Inserta el numero de regla a mostrar:", "90");
            } while (numRegla.equals(""));
            regla = Integer.parseInt(numRegla);
        } while (regla < 0 || regla > 255);

        int pregunta = JOptionPane.showConfirmDialog(null, "¿Desea generar un vector inicial aleatorio?", "Inicializacion", JOptionPane.YES_NO_OPTION);
        if (pregunta == 0) {
            R.Random();
        }

        /*Se hace la conversion de Entero a Binario*/
        for (int i = 7; i >= 0; i--) {

            NumBinario[i] = regla % 2;
            System.out.println("El elemento [" + i + "] -> " + NumBinario[i]);
            regla /= 2;
        }

/*Se manda a la clase Regla el vector del numero binario de la regla escogida por el usuario para establecer la Regla a mostrar
No se consideran valores booleanos para poder simplificar y entender el proceso de obtencion de la regla*/

        R.getRegla(NumBinario[0], NumBinario[1], NumBinario[2], NumBinario[3], NumBinario[4], NumBinario[5], NumBinario[6], NumBinario[7]);
        R.Reglas();
        R.iteracion();
        R.mostrar();

    }

}
