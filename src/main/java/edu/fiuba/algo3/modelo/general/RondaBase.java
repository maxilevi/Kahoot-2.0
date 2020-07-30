package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.preguntas.Opcion;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;

import java.util.HashMap;
import java.util.List;

public abstract class RondaBase {
    protected Pregunta preguntaActual;
    protected List<Jugador> jugadores;
    protected HashMap<Jugador, List<Opcion>> respuestasDeJugadores;

    public RondaBase(Pregunta pregunta, List<Jugador> nuevosJugadores){
        preguntaActual = pregunta;
        jugadores = nuevosJugadores;
        respuestasDeJugadores = new HashMap<>();
    }

    public void mostrarPregunta() {
        System.out.println(preguntaActual.obtenerTexto());
    }

    public void pedirRespuesta(Jugador jugador) {
        //respuestasDeJugadores.put(jugador, input(Opcion opcion));
//        CORREGIR EL INPUT
    }

    public void mostrarRespuestaCorrecta(){
        for(Opcion opcion: preguntaActual.obtenerRespuestasCorrectas()) {
            System.out.println(opcion.obtenerTexto());
        }
    }

    public void mostrarPosiblesRespuestas() {
        for(Opcion opcion: preguntaActual.obtenerOpciones()) {
            System.out.println(opcion.obtenerTexto());
        }
//        VER COMO SE PUEDEN MOSTRAR TODAS JUNTAS
    }

    public abstract void mostrarModificadores(Jugador jugador);
    public abstract void comenzar();
}