package Fhulano.AutomatasCelulares;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Regla extends JFrame {

    int A[][]=new int[1000][1000];
    int a,b,c,d,e,f,g,h;
    boolean rand = false;

    public Regla() {

        setTitle("Automatas Celulares");
        setBackground(Color.BLACK);
        setSize(1000,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void Random() {
        rand = true;
    }

    public void Reglas(){

        for(int i=0;i<1000;i++){
            A[0][i]=0;
        }

        if (rand) {
            Random r = new Random();
            for(int j=0; j<=500; j++) {
                boolean truefalse = r.nextBoolean();
                A[0][j] = (truefalse)?1:0; /*Se genera un vector inicial aleatorio*/
            }
        }else {
            A[0][100]=1; /*Se establece un vector inicial con un pixel encendido a la mitad del vector*/
        }

    }

    /*EL metodo iteracion se encarga de ir evaluando las celulas en toda la matriz; los valores enteros j, k y l son la representacion en la tupla de la celda del 'centro' y sus vecinos */
    public void iteracion() {

        for(int y=0;y<500;y++){
            for(int x=0;x<250;x++){
                if(x<248){
                    int j=0,k=0,l=0;
                    j=x+1;
                    k=x+2;
                    l=y+1;
                    A[l][j]=getCasilla(A[y][x],A[y][j],A[y][k]);
                }
                else if(x>=248){
                    int l=0;
                    l=y+1;
                    A[l][249]=getCasilla(A[y][248],A[y][249],A[y][0]);
                    A[l][0]=getCasilla(A[y][249],A[y][0],A[y][1]);
                }
            }
        }

    }

    /*El metodo getCasilla es el encargado de ir siguiendo la regla y regresar el nuevo valor que la celula tendra en la siguiente etapa del tiempo (k+1)*/
    public int getCasilla(int A,int B ,int C) {
        int r=0;
        if(A==0 && B==0 && C==0){r=a;
        }else if(A==0 && B==0 && C==1){r=b;
        }else if(A==0 && B==1 && C==0){r=c;
        }else if(A==0 && B==1 && C==1){r=d;
        }else if(A==1 && B==0 && C==0){r=e;
        }else if(A==1 && B==0 && C==1){r=f;
        }else if(A==1 && B==1 && C==0){r=g;
        }else if(A==1 && B==1 && C==1){r=h;
        }
        return r;
    }

    public void getRegla(int H,int G,int F,int E,int D,int C,int B,int A){

        a=A; b=B; c=C; d=D; e=E; f=F; g=G; h=H;
        /*Se reciben los valores del vector binario para crear la regla*/
    }

    public void imprimir () {

        for(int y=0;y<11;y++){
            for(int x=0;x<11;x++){
                System.out.println("Elemento A["+y+"]["+x+"] :"+A[y][x]);
            }
        }

    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        for(int y=0;y<500;y++){
            try{
                for(int x=0;x<250;x++) {
                    if(A[y][x]==1){
                        int l=0,k=0;
                        l=x*5;  //multiplicado por tamaño del cuadro
                        k=y*5;
                        g.setColor(Color.WHITE);
                        g.fillRect(l,k, 5, 5);
                    }
                    else {
                        int a=0,b=0;
                        a=x*5;
                        b=y*5;
                        g.setColor(Color.BLACK);
                        g.fillRect(a, b, 5, 5);
                    }
                }
                Thread.sleep(80);
            } catch(InterruptedException e){
                System.out.println("Excepcion: " + e.getMessage());
            }
        }
        /*Se termina el metodo paint*/
    }

    public void mostrar() {
        setVisible(true);
    }

}