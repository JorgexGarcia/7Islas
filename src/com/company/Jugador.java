package com.company;

import java.util.ArrayList;

/**
 * Clase jugador que esta compuesta por un nombre, una mano de cartas que añade en cada ronda y una puntuación
 */
public class Jugador {
    private String nombre;
    private ArrayList<String> mano;
    private int puntuacion;

    /*Constructor*/
    public Jugador(String n){
        setNombre(n);
        setMano(new ArrayList<>());
        setPuntuacion(0);
    }

    /*Métodos Set*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setMano(ArrayList<String> mano) {
        this.mano = mano;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /*Métodos Get*/
    public String getNombre() {
        return nombre;
    }
    public ArrayList<String> getMano() {
        return mano;
    }
    public String getCartaMano(int posicion){
        return mano.get(posicion);
    }
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Método para calcular la puntuación de los jugadores
     */
    public void calcularPuntuacion(){
        do{
            puntosDoblon();
            cantidadCartas();
        }while(mano.size()>0);
    }

    /**
     * Método para calcular la cantidad de doblones
     */
    private void puntosDoblon() {
        while(mano.remove("Doblón")){
            puntuacion++;
        }
    }

    /**
     * Método para saber la cantidad de set distintos de cartas.
     * Cada vez que encuentra un tipo de carta, elimina 1 de la mano y cuenta 1 para el set
     */
    private void cantidadCartas() {
        int cantidad = 0;
        if(mano.remove("Caliz"))
            cantidad++;
        if(mano.remove("Rubí"))
            cantidad++;
        if(mano.remove("Diamante"))
            cantidad++;
        if(mano.remove("Collar"))
            cantidad++;
        if(mano.remove("Mapa del Tesoro"))
            cantidad++;
        if(mano.remove("Corazón"))
            cantidad++;
        if(mano.remove("Revolver"))
            cantidad++;
        if(mano.remove("Espada"))
            cantidad++;
        if(mano.remove("Barril de Ron"))
            cantidad++;
        puntosCartas(cantidad);
    }

    /**
     * Dependiendo de la cantidad de cartas en el set, suma los puntos correspondientes a la puntuación
     * @param cantidad Cantidad de cartas en el set
     */
    private void puntosCartas(int cantidad) {
        switch (cantidad){
            case 1:
                puntuacion += 1;
                break;
            case 2:
                puntuacion += 3;
                break;
            case 3:
                puntuacion += 7;
                break;
            case 4:
                puntuacion += 13;
                break;
            case 5:
                puntuacion += 21;
                break;
            case 6:
                puntuacion += 30;
                break;
            case 7:
                puntuacion += 40;
                break;
            case 8:
                puntuacion += 50;
                break;
            case 9:
                puntuacion += 60;
                break;
        }
    }

    /**
     * Método que devuelve un texto para poder mostrar al usuario
     * @param numero El número de la carta
     * @param posicion La posición de la carta en el array
     * @return Devuelve la carta según su posicioón y el número correspondiente
     */
    public String mostrarCartaMano(int numero, int posicion){
        return numero + ". " + getCartaMano(posicion);
    }
}
