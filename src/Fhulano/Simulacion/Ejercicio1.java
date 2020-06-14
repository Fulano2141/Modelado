package Fhulano.Simulacion;

import java.lang.invoke.SwitchPoint;

public class Ejercicio1 {
    public static void main(String[] args) {

        ejer1();
        ejer2();
    }

    private static void ejer2() {
        double med1 = 2959;// 2006
        double med = 3000;// 2004
        int n = 8;
        double desv = Math.sqrt(39.1);

        double T = (med - med1)/(desv/Math.sqrt(n));
        // 1 - alpha = 0.95
        double t = 2.365;
        double talmed = 2.998;
        System.out.println("\n\nT experimental = "+T);
        System.out.println("____________ Cola Derecha __________________");
        if(T > t){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
        System.out.println("____________ Cola Izquierda __________________");
        if(T < -t){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
        System.out.println("____________ Dos Cola __________________");
        if(Math.abs(T) > talmed){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
    }

    private static void ejer1() {
        double med = 53 ;// 2006
        double med1 = 40;// 2004
        int n = 12;
        double desv = 16.11;

        double T = (med - med1)/(desv/Math.sqrt(n));
        // 1 - alpha = 0.95
        double t = 1.796;
        double talmed = 2.201;
        System.out.println("\nT experimental = "+T);
        System.out.println("____________ Cola Derecha __________________");
        if(T > t){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
        System.out.println("____________ Cola Izquierda __________________");
        if(T < -t){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
        System.out.println("____________ Dos Cola __________________");
        if(Math.abs(T) > talmed){
            System.out.println("Las medias son iguales");
        }else {
            System.out.println("Las medias son distintas");
        }
    }
}
