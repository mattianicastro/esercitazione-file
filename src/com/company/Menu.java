package com.company;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    int scelta;
    Scanner scanner;
    ArrayList<Libro> libri;
    final String fileName = "libri.txt";

    public Menu() throws IOException {
        scanner = new Scanner(System.in);
        // Creazione di un Array dinamico con oggetti di tipo Libro
        this.libri = new ArrayList<>();
        ricaricaLista();
    }

    private void ricaricaLista() throws IOException {
        // Inizializzazione reader
        FileReader file = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(file);

        // Lettura del file riga per riga, la variabile eof ci serve a
        // tenere conto di quando il file è finito, la imposteremo a true
        // quando buffer.readLine() ci restituirà null
        boolean eof = false;
        libri.clear();
        while (!eof) {
            String c = buffer.readLine();
            if (c == null) {
                eof = true;
            } else {
                // Uso il costruttore che accetta solo il parametro informazioni
                // per creare un libro direttamente
                Libro l = new Libro(c);
                // aggiungo il libro all'array dinamico
                libri.add(l);
            }
        }

        buffer.close();
        file.close();

    }

    private void determinaPiuCostoso() {
        //Determinazione del libro più costoso

        // Creo una variabile piuCostoso per tenere traccia del libro più costoso finora
        // La inizializzo a una Libro con costo 0.
        Libro piuCostoso = new Libro("", 0);

        // Questo è un "for speciale" che ci permette di iterare facilmente su un ArrayList
        for (Libro l : libri) {
            if (l.getCosto() > piuCostoso.getCosto()) {
                piuCostoso = l;
            }
        }

        // il metodo toString della variabile piuCostoso viene chiamato in automatico
        System.out.println("Il libro più costoso è : \n" + piuCostoso);
    }

    private void aggiungiLibro() throws IOException {
        // Creo un FileWriter impostanto append=true, così il contenuto
        // del file non verrà sovrascritto ma verrà aggiunto in fondo al file
        FileWriter file = new FileWriter(fileName, true);
        PrintWriter writer = new PrintWriter(file);
        // Uso il costruttore che accetta uno Scanner, che è costruito per
        // chiedere i dati da tastiera
        Libro l = new Libro(scanner);
        // Aggiunta del libro all'Array
        libri.add(l);
        // Scrittura su file
        writer.println(l.toString());
        writer.close();
        file.close();
    }

    private void elencaLibri() {
        for (Libro l : libri) {
            System.out.println(l.toString());
        }
    }

    private void elencaPerCosto() throws IOException {
        scanner.nextLine();
        System.out.println("Inserire costo");
        float costo = scanner.nextFloat();
        FileWriter file = new FileWriter("altrilibri.txt", false);
        PrintWriter writer = new PrintWriter(file);
    
        for(Libro l : libri) {
            if (l.getCosto() == costo) {
                writer.println(l);
            }
        }

        writer.close();
        file.close();

    }
    public void run() throws IOException {
        while (true) {
            System.out.println("0) Esci\n1) Ricarica file\n2) Trova il libro più costoso\n3) Aggiungi libro\n4) Elenca libri\n5) Elenca i libri per costo");
            scelta = scanner.nextInt();
            switch (scelta) {
                case 0:
                    System.out.println("Bye");
                    return;
                case 1:
                    ricaricaLista();
                    break;
                case 2:
                    determinaPiuCostoso();
                    break;
                case 3:
                    aggiungiLibro();
                    break;
                case 4:
                    elencaLibri();
                    break;
                case 5:
                    elencaPerCosto();
                    break;
            }
        }
    }

}
