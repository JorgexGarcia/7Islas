package com.company;

import java.util.Scanner;

public class Main {

    /**
     * El main controla el juego, el crea un tablero y lleva el control del jugador activo.
     * Termina la partida cuando el mazo se quede sin cartas. Despúes llama a los métodos para calcular
     * los puntos y muestra los resultados
     */
    public static void main(String[] args) {
        Tablero tablero = crearPartida();
        int jugadorActual = elegirOrden(tablero);

        do{
            partida(jugadorActual-1,tablero);
            jugadorActual++;
            if (jugadorActual > tablero.getJugadores().length)
                jugadorActual = 1;

        }while(tablero.getMazo().size() > 0);

        calculaPuntuaciones(tablero);
        int posicion = compruebaPuntuacion(tablero);
        mostrarResultado(tablero, posicion);
    }

    /**
     * Método que muestra el ganador final
     * @param tablero El tablero de juego
     * @param posicion La posición del ganador
     */
    private static void mostrarResultado(Tablero tablero, int posicion) {
        System.out.println("\nTermino la partida");
        System.out.println("El ganador es " + tablero.getJugador(posicion).getNombre() + " !!!");
        System.out.println("Con una puntuacion de " + tablero.getJugador(posicion).getPuntuacion());
    }

    /**
     * Método para comprobar que jugador tiene la máxima puntuación
     * @param tablero Tablero del juego
     * @return Devuelve la posición del jugador
     */
    private static int compruebaPuntuacion(Tablero tablero) {
        int puntuacion = 0 ;
        int posicion  = 0;
        for (int i = 0; i < tablero.getJugadores().length; i++) {
            if (tablero.getJugador(i).getPuntuacion() > puntuacion){
                puntuacion = tablero.getJugador(i).getPuntuacion();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Método para calcular la puntuación de los jugadores del tablero
     * @param tablero Tablero de juego
     */
    private static void calculaPuntuaciones(Tablero tablero){
        for (int i = 0; i < tablero.getJugadores().length; i++) {
            System.out.println("\nJugador " + (i+1) + " : " + tablero.getJugador(i).getNombre() + "\n");
            mostrarManoJugador(tablero.getJugador(i));
            tablero.getJugador(i).calcularPuntuacion();
        }
    }

    /**
     * Método para crear un tablero, primero tiene que saber la cantidad de jugadores y sus nombres
     * @return El tablero de juego
     */
    public static Tablero crearPartida(){

        System.out.println("Bienvenido al juego 7 Islas");
        System.out.println("Cuantos jugadores queréis jugar? Mínimo 2 jugadores y máximo 4 jugadores");
        int cantidad = entradaInt(2,4,"Juego para 2 - 4 jugadores");
        Tablero tablero = new Tablero(cantidad);
        tablero = entradaNombres(tablero, cantidad);
        return tablero;
    }

    /**
     * Método para pedir los nombres de todos los jugadores,
     * también llama al método para inicializar sus manos con 3 doblones
     * @param tablero Tablero de juego
     * @param cantidad Cantidad de jugadores
     * @return Tablero de juego
     */
    private static Tablero entradaNombres(Tablero tablero, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Dime el nombre del jugador " + (i + 1));
            String nombre = entradaString("Error en la entrada de datos, vuelva a intentarlo");
            tablero.setJugador(i, nombre);
        }
        tablero.iniciarManoJugador();
        return tablero;
    }

    /**
     * Método para recoger un String por pantalla, se protege para evitar excepciones
     * @param texto Texto que muestra por pantalla en caso de no ser un valor válido
     * @return El string recogido por la pantalla
     */
    private static String entradaString(String texto) {
        Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        String strin = "";
        do{
            try{
                strin = scanner.nextLine();
            }catch (Exception e){
                fallo = true;
                System.out.println(texto);
            }
        }while (fallo);
        return strin;
    }

    /**
     * Método para elegir el orden de inicio de los jugadores
     * @param tablero Tablero de juego
     * @return Posición del jugador activo
     */
    public static int elegirOrden(Tablero tablero){
        int posicion = 1;
        if (tablero.getJugadores().length > 1){
            System.out.println("Vamos a lanzar un dado para elegir el orden");
            posicion = (int)(Math.random() * tablero.getJugadores().length+1);
        }
        System.out.println();
        System.out.println("Empieza el jugador " + posicion + "!");
        return posicion;
    }

    /**
     * Método de la partida, muestra un menú y según la opción del jugador actua
     * @param posicion Posición del jugador activo
     * @param tablero Tablero de juego
     */
    public static void partida(int posicion, Tablero tablero){
        infoPartida(tablero);
        int opcion;
        do {
            System.out.println();
            System.out.println("Turno de " + tablero.getJugador(posicion).getNombre());
            System.out.println();
            System.out.println("Elige una de estas opciones:");
            System.out.println("\t1. Ver tu información a modo resumen.");
            System.out.println("\t2. Iniciar tirada");
            opcion = entradaInt(1, 2, "Opción no valida, solo tienes dos opciones");
            switch (opcion) {
                case 1:

                    verResumen(tablero.getJugador(posicion));
                    break;
                case 2:
                    iniciarTirada(tablero.getJugador(posicion), tablero);
                    break;
            }
        }while (opcion < 2);
    }

    /**
     * Método para recoger un int por pantalla, se protege para evitar excepciones
     * @param min Valor mínimo admitido
     * @param max Valor máximo admitido
     * @param texto Texto para mostrar en caso de no ser un valor válido
     * @return Devuelve el int
     */
    private static int entradaInt(int min, int max, String texto) {
        Scanner scaner = new Scanner(System.in);
        int opcion;
        do {
            try {
                opcion = scaner.nextInt();
            } catch (Exception e) {
                scaner.nextLine();
                opcion = 0;
            }
            if (opcion < min || opcion > max) {
                System.out.println(texto);
            }
        } while ((opcion < min || opcion > max));
        return opcion;
    }

    /**
     * Método para mostrar el estado del Tablero
     * @param tablero Tablero de juego
     */
    private static void infoPartida(Tablero tablero) {
        System.out.println();
        System.out.println("Quedan " + tablero.getMazo().size()+" cartas en el mazo del juego");
        System.out.println("En la isla 1 esta: " + tablero.getIsla(0));
        System.out.println("En la isla 2 esta: " + tablero.getIsla(1));
        System.out.println("En la isla 3 esta: " + tablero.getIsla(2));
        System.out.println("En la isla 4 esta: " + tablero.getIsla(3));
        System.out.println("En la isla 5 esta: " + tablero.getIsla(4));
        System.out.println("En la isla 6 esta: " + tablero.getIsla(5));
    }

    /**
     * Método para ver las cartas del jugador
     * @param jugador Jugador activo
     */
    public static void verResumen(Jugador jugador){
        System.out.println();
        System.out.println("Jugador " + jugador.getNombre());
        System.out.println("Tu mano esta compuesta por: ");
        mostrarManoJugador(jugador);
    }

    /**
     * Método para iniciar una jugada, muestra un menú con las posibles opciones al jugador
     * @param jugador Jugador activo
     * @param tablero Tablero de juego
     */
    public static void iniciarTirada(Jugador jugador, Tablero tablero){
        int tirada = lanzarDado();
        System.out.println("Elige una de estas opciones:");
        System.out.println("\t1. Llevarte la carta de la isla " + tirada);
        System.out.println("\t\tQue es la carta: " + tablero.getIsla(tirada-1));
        System.out.println("\t2. Contratar barco pirata");
        int opcion=entradaInt(1,2,"Opción no valida, solo tienes dos opciones ");
        boolean fallo = false;
        do{
            switch (opcion) {
                case 1:
                    jugador.getMano().add(tablero.getIsla(tirada-1));
                    tablero.cartaIsla(tirada-1);
                    break;
                case 2:
                    fallo = (!contratarBarco(tirada, jugador, tablero));
                    break;
            }
        }while (fallo);
    }

    /**
     * Método que simula el lanzamiento de un dado
     * @return Devuelve el valor del lanzamiento
     */
    private static int lanzarDado() {
        System.out.println("Vamos a lanzar el dado");
        int tirada = (int)((Math.random()*6)+1);
        System.out.println("Ha salido un " + tirada);
        return tirada;
    }

    /**
     * Método que realiza la acción de contratar un barco, comprueba que la isla seleccionada es válida.
     * Si es correcto, mete a la mano del jugador la carta de la isla y coloca una carta en ella.
     * @param tirada Posición de la isla
     * @param jugador Jugador Activo
     * @param tablero Tablero de juego
     * @return Si ha sido posible realizar la acción. En caso de no ser una isla válida, devuelve un false
     */
    public static boolean contratarBarco(int tirada, Jugador jugador, Tablero tablero){
        System.out.println("Escribe el número de la isla de la que deseas su tesoro");
        int eleccion = entradaInt(1,6,"Solo hay 6 islas, intentalo de nuevo");
        int cantidad =  eleccion - tirada;
        if (cantidad < 0)
            cantidad = cantidad * -1;
        if (!comprobarIsla(cantidad, jugador))
            return false;
        descartarCartaJugador(cantidad, jugador);
        System.out.println("Recibes la carta "+tablero.getIsla(eleccion-1));
        jugador.getMano().add(tablero.getIsla(eleccion-1));
        tablero.cartaIsla(eleccion-1);
        return true;
    }

    /**
     * Método para comprobar si la isla esta fuera del rango superior o el jugador ya esta situado en esa isla
     * @param cantidad Valor resultante con la diferencia de la isla que desea el jugador y su tirada
     * @param jugador Jugador Activo
     * @return Si fue posible realizar la acción, devuelve false, si la isla no es correcta
     */
    private static boolean comprobarIsla(int cantidad, Jugador jugador) {
        if (cantidad == 0) {
            System.out.println("Ya estas en esa isla");
            return false;
        } else {
            if (cantidad > jugador.getMano().size()) {
                System.out.println("No tienes tantas cartas");
                return false;
            }
        }
        return true;
    }

    /**
     * Método que permite al jugador descartarse las cartas de su mano para poder moverse a otra isla
     * @param cantidad Cantidad de cartas a descartar
     * @param jugador Jugador Activo
     */
    private static void descartarCartaJugador(int cantidad, Jugador jugador) {
        int posicion;
        System.out.println("Tienes que descartar " + cantidad + " cartas");
        for (int i = 0; i < cantidad; i++) {

            System.out.println("Dime que carta quieres indicando su número");
            mostrarManoJugador(jugador);
            posicion=entradaInt(1, jugador.getMano().size(),"No tienes esa carta");
            jugador.getMano().remove(posicion-1);
        }
    }

    /**
     * Método que visualiza las cartas de la mano del jugador
     * @param jugador Jugador activo
     */
    private static void mostrarManoJugador(Jugador jugador) {
        for (int j = 0; j < jugador.getMano().size(); j++) {
            System.out.println(jugador.mostrarCartaMano(j+1, j));
        }
    }
}
