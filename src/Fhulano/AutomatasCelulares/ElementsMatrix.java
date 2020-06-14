package Fhulano.AutomatasCelulares;

import java.awt.*;

public class ElementsMatrix {
    int i;
    Color col;

    public ElementsMatrix(int i, Color col) {
        this.i = i;
        this.col = col;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }

    public int getNumber() {
        return getI();
    }

    public Color getColor() {
        return getCol();
    }
}
