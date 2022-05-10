package com.company;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Clase Tablero que contiene un array de Jugadores, un array de Islas, un array de cartas que compone un mazo
 * y constantes para la formación del mazo y el tablero.
 */
public class Tablero {
    private Jugador[] jugadores;
    private String[] islas;
    private final int CANTIDADISLAS = 6;
    private final int CANTDOBLONES = 27;
    private final int CANTCALICES = 3;
    private final int CANTRUBIES = 3;
    private final int CANTDIAMANTES = 3;
    private final int CANTCOLLARES = 4;
    private final int CANTMAPATESORO = 4;
    private final int CANTCORAZONES = 4;
    private final int CANTREVOLVERES = 6;
    private final int CANTESPADAS = 6;
    private final int CANTBARRILESDERON = 6;
    private ArrayList<String> mazo;

    /*Constructor*/
    public Tablero (int cantidadJugadores){
        jugadores = new Jugador[cantidadJugadores];
        islas = new String[CANTIDADISLAS];
        mazo = new ArrayList<>();
        setMazo();
        llenarIslas();
    }

    /*Métodos Set*/
    public void setJugador(int posicion, String nombre ) {
        jugadores[posicion] = new Jugador(nombre);
    }

    /*Métodos Get*/
    public Jugador[] getJugadores() {
        return jugadores;
    }
    public Jugador getJugador(int posiscion) {
        return jugadores[posiscion];
    }
    public String getIsla(int posicion) {
        return islas[posicion];
    }
    public ArrayList<String> getMazo() {
        return mazo;
    }

    /**
     * Método que crea un mazo, llama a un método para introducir cada tipo de carta
     */
    private void setMazo() {
        setCarta("Doblón", CANTDOBLONES);
        setCarta("Caliz", CANTCALICES);
        setCarta("Rubí", CANTRUBIES);
        setCarta("Diamante", CANTDIAMANTES);
        setCarta("Collar", CANTCOLLARES);
        setCarta("Mapa del Tesoro", CANTMAPATESORO);
        setCarta("Corazón", CANTCORAZONES);
        setCarta("Revolver", CANTREVOLVERES);
        setCarta("Espada", CANTESPADAS);
        setCarta("Barril de Ron", CANTBARRILESDERON);
    }

    /**
     * Método introduce una cantidad de cartas en una posición aleatoria del mazo.
     * @param valor Tipo de la carta
     * @param cantidad Cantidad de cartas de ese tipo
     */
    private void setCarta(String valor, int cantidad){
        for (int i = 0; i < cantidad; i++) {
            mazo.add((int)(Math.random() * mazo.size()), valor);
        }
    }

    /**
     * Método que llena las islas del tablero
     */
    private void llenarIslas() {
        for (int i = 0; i < islas.length; i++) {
            cartaIsla(i);
        }
    }

    /**
     * Método que saca una carta del mazo y la coloca en una isla determinada
     * @param posicion La isla que tiene que colocar la carta
     */
    public void cartaIsla(int posicion){
        islas[posicion] =  mazo.remove((int)(Math.random() * mazo.size()));
    }

    /**
     * Método que saca 3 doblones del mazo por cada jugador y los coloca en la mano de cada uno
     */
    public void iniciarManoJugador() {
        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < 3; j++) {
                mazo.remove("Doblón");
                jugadores[i].getMano().add("Doblón");
            }
        }
    }
}
