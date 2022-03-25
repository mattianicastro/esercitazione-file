package com.company;

import java.util.Scanner;

public class Libro {
    private String titolo;
    private float costo;

    public Libro(String titolo, int costo) {
        this.titolo = titolo;
        this.costo = costo;
    }

    public Libro(String informazioni) {
        String[] i = informazioni.split("//");
        this.titolo = i[0];
        this.costo = Float.parseFloat(i[1]);
    }

    public Libro(Scanner s) {
        System.out.println("Titolo del libro?");
        s.nextLine();
        this.titolo = s.nextLine();
        System.out.println("Costo del libro?");
        this.costo = s.nextFloat();
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return titolo + "//" + costo;
    }

}
