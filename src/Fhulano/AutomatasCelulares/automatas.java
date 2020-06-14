package Fhulano.AutomatasCelulares;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class automatas extends JPanel implements ActionListener {
    public static ElementsMatrix elementsOfMatrix[][] = new ElementsMatrix[5][22];
    public static int rowPosition = 2, columnPosition = 1;
    public static java.util.List<MistakesElements> mistakeElements = new ArrayList<MistakesElements>();

    Timer tm = new Timer(500, this);


    public static void main(String a[]) {
        automatas colorMatrix = new automatas();
        generateMatrix();
//        System.out.println(showMatrix(elementsOfMatrix));
        JFrame jFrame = new JFrame();
        jFrame.setSize(1400, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(colorMatrix);
        jFrame.setVisible(true);

//        generateMatrix();
//        new ColorMatrix();
    }

    public static void generateMatrix() {
        int numberOfCars = (int) (Math.random() * 2) + 1;
        System.out.println("Son " + numberOfCars + " autos");
        int numRowRandom = (int) (Math.random() * 4) + 1;
        int numColumnRandom = (int) (Math.random() * 21) + 1;
        elementsOfMatrix[2][4] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[1][4] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[2][5] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[1][5] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[2][8] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[2][7] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[3][8] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[1][11] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[1][10] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[2][10] = new ElementsMatrix(2, Color.red);
        elementsOfMatrix[2][11] = new ElementsMatrix(2, Color.red);
        System.out.println("Genera [" + numRowRandom + "," + numColumnRandom + "]");
        int cont = 1;
        ElementsMatrix elementsMatrix2 = new ElementsMatrix(3, Color.BLUE);
        elementsOfMatrix[2][1] = elementsMatrix2;
//        mistakeElements.add(new MistakesElements(2,1));
        while (cont <= numberOfCars) {
            if (numColumnRandom != 0 && numColumnRandom != elementsOfMatrix[0].length - 1 && numRowRandom != 0 && numRowRandom != elementsOfMatrix.length - 1) {
                for (int i = 0; i < elementsOfMatrix.length; i++) {
                    for (int j = 0; j < elementsOfMatrix[0].length; j++) {
                        if (i == 0 || i == elementsOfMatrix.length - 1 || j == elementsOfMatrix[0].length - 1 || j == 0) {
                            ElementsMatrix elementsMatrix = new ElementsMatrix(1, Color.GRAY);
                            elementsOfMatrix[i][j] = elementsMatrix;
//                        elementsOfMatrix[i][j].setColor(Color.GRAY);
//                        elementsOfMatrix[i][j].setNumber(1);
                        } else {
                            if (i == numRowRandom && j == numColumnRandom) {
//                        color[i][j]=Color.red;
                                ElementsMatrix elementsMatrix = new ElementsMatrix(2, Color.RED);
                                elementsOfMatrix[i][j] = elementsMatrix;
                                numRowRandom = (int) (Math.random() * 3) + 1;
                                numColumnRandom = (int) (Math.random() * 21) + 1;
//                        elementsOfMatrix[i][j].setColor(Color.red);
//                        elementsOfMatrix[i][j].setNumber(2);
                                System.out.println("Encuentra en [" + i + "," + j + "]");
//                                System.out.println("Genera ["+numRowRandom+","+numColumnRandom+"]");
                                cont++;
                            } else {
                                if (elementsOfMatrix[i][j] != null) {
                                    if (elementsOfMatrix[i][j].getNumber() != 2 && elementsOfMatrix[i][j].getNumber() != 3 && elementsOfMatrix[i][j].getNumber() != 4) {
                                        ElementsMatrix elementsMatrix = new ElementsMatrix(0, Color.WHITE);
                                        elementsOfMatrix[i][j] = elementsMatrix;
                                    }
                                } else {
                                    ElementsMatrix elementsMatrix = new ElementsMatrix(0, Color.WHITE);
                                    elementsOfMatrix[i][j] = elementsMatrix;

                                }
//                            elementsOfMatrix[i][j].setColor(Color.red);
//                            elementsOfMatrix[i][j].setNumber(0);
//                            color[i][j]=Color.white;

                            }

                        }
                    }
                }

            } else {
                numRowRandom = (int) (Math.random() * 4) + 1;
                numColumnRandom = (int) (Math.random() * 21) + 1;
            }


        }
        System.out.println("Termina de buscar");

        new automatas();

    }

    public static void generateMove() {
        int lastrowPosition = rowPosition, lastColumnPosition = columnPosition;
        int restrigedPositionRow = 0, restrigedPositionColumn = 0;
        boolean enterRestringed = false;
        if (elementsOfMatrix[rowPosition][columnPosition + 1].getNumber() == 1) {
            columnPosition = 1;
            rowPosition = 2;
            cleanMatrix();
        } else {
            if (elementsOfMatrix[rowPosition][columnPosition + 1].getNumber() == 0) {
                columnPosition++;
//                System.out.println(showMatrix(elementsOfMatrix));
//                System.out.println("SIIIII ENTROOO EL VALOR DE LA COLUMNA ES " + elementsOfMatrix[rowPosition][columnPosition].getNumber());
            } else {
                if (isAlreadyHappened(rowPosition, columnPosition)) {
                    enterRestringed = true;
                    restrigedPositionRow = rowPosition;
                    restrigedPositionColumn = columnPosition;
                }
                if (elementsOfMatrix[rowPosition - 1][columnPosition].getNumber() == 0 && elementsOfMatrix[rowPosition - 2][columnPosition].getNumber() == 1 && elementsOfMatrix[rowPosition - 1][columnPosition + 1].getNumber() == 2 && elementsOfMatrix[rowPosition + 1][columnPosition].getNumber() == 0) {
                    rowPosition++;
                } else {
                    if (elementsOfMatrix[rowPosition - 1][columnPosition].getNumber() == 0) {
                        rowPosition--;
                    } else {
                        if (elementsOfMatrix[rowPosition + 1][columnPosition].getNumber() == 0) {
                            rowPosition++;
                        } else {
                            if (elementsOfMatrix[rowPosition + 1][columnPosition].getNumber() != 0 && elementsOfMatrix[rowPosition][columnPosition + 1].getNumber() != 0) {
//                            ElementsMatrix elementsMatrix=new ElementsMatrix(4,Color.MAGENTA);
//                            elementsOfMatrix[rowPosition][columnPosition]=new ElementsMatrix(4,Color.MAGENTA);
                                enterRestringed = true;
                                restrigedPositionRow = rowPosition;
                                restrigedPositionColumn = columnPosition;
//                            elementsOfMatrix[rowPosition][columnPosition].setNumber(4);
//                            elementsOfMatrix[rowPosition][columnPosition].setColor(Color.MAGENTA);
                                columnPosition--;
//                            System.out.println(showMatrix(elementsOfMatrix));
                            } else {
                                if (elementsOfMatrix[rowPosition - 1][columnPosition].getNumber() != 0 && elementsOfMatrix[rowPosition][columnPosition + 1].getNumber() != 0) {
                                    elementsOfMatrix[rowPosition][columnPosition] = new ElementsMatrix(4, Color.MAGENTA);
                                    //                                elementsOfMatrix[rowPosition][columnPosition].setNumber(4);
//                                elementsOfMatrix[rowPosition][columnPosition].setColor(Color.MAGENTA);
                                    enterRestringed = true;
                                    restrigedPositionRow = rowPosition;
                                    restrigedPositionColumn = columnPosition;
                                    columnPosition--;
//                                System.out.println(showMatrix(elementsOfMatrix));

                                }
                            }
                        }
                    }

                }
            }
            elementsOfMatrix[rowPosition][columnPosition] = new ElementsMatrix(3, Color.BLUE);
            mistakeElements.add(new MistakesElements(lastrowPosition, lastColumnPosition));
            elementsOfMatrix[lastrowPosition][lastColumnPosition] = new ElementsMatrix(0, Color.WHITE);
            if (enterRestringed) {
                elementsOfMatrix[restrigedPositionRow][restrigedPositionColumn] = new ElementsMatrix(4, Color.MAGENTA);
            }

//            System.out.println(showMatrix(elementsOfMatrix));


        }

        new automatas();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        generateMove();
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int space = 0;
        int spaceVertical = 0;
        for (int i = 0; i < elementsOfMatrix.length; i++) {
            for (int j = 0; j < elementsOfMatrix[0].length; j++) {
                if (elementsOfMatrix[i][j].getNumber() == 0) {
                    g.setColor(elementsOfMatrix[i][j].getColor());
                    g.drawRect(100 + space, 100 + spaceVertical, 50, 50);
                }
                if (elementsOfMatrix[i][j].getNumber() == 1) {
                    g.setColor(elementsOfMatrix[i][j].getColor());
                    g.fillRect(100 + space, 100 + spaceVertical, 50, 50);

                }
                if (elementsOfMatrix[i][j].getNumber() == 2) {
                    g.setColor(elementsOfMatrix[i][j].getColor());
                    g.fillRect(100 + space, 100 + spaceVertical, 50, 50);
                }
                if (elementsOfMatrix[i][j].getNumber() == 3) {
                    g.setColor(elementsOfMatrix[i][j].getColor());
                    g.fillRect(100 + space, 100 + spaceVertical, 50, 50);
                }
                if (elementsOfMatrix[i][j].getNumber() == 4) {
//                    System.out.println("Si dibuja el purpura ");
                    g.setColor(elementsOfMatrix[i][j].getColor());
                    g.fillRect(100 + space, 100 + spaceVertical, 50, 50);
                }
                space += 55;
            }
            spaceVertical += 55;
            space = 0;
        }
        tm.start();
    }

    public static String showMatrix(ElementsMatrix[][] elementInMatrix) {
        StringBuilder cad = new StringBuilder();
        for (ElementsMatrix[] inMatrix : elementInMatrix) {

            for (int j = 0; j < elementInMatrix[0].length; j++) {
                cad.append(inMatrix[j].getNumber()).append("   ");
            }
            cad.append("\n");
        }
        return cad.toString();
    }

    public static boolean isAlreadyHappened(int i, int j) {
        boolean isHappened = false;
        for (int c = 0; c < mistakeElements.size(); c++) {
            if (i == mistakeElements.get(c).getI() && j == mistakeElements.get(c).getJ()) {
                isHappened = true;
            }
        }
        return isHappened;
    }

    public static void cleanMatrix() {
        for (int i = 0; i < elementsOfMatrix.length; i++) {
            for (int j = 0; j < elementsOfMatrix[0].length; j++) {
                if (elementsOfMatrix[i][j].getNumber() == 4) {
                    elementsOfMatrix[i][j] = new ElementsMatrix(0, Color.white);
                }

            }
        }

    }
}