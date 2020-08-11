package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.modelo.preguntas.Opcion;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import javafx.scene.control.Alert;

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

    public Pregunta obtenerPreguntaActual() {
        return preguntaActual;
    }

    public void guardarRespuestas(Jugador jugador, List<Opcion> respuestas) {
        respuestasDeJugadores.put(jugador, respuestas);
    }

    public abstract void usarModificador(Jugador jugador, IModificador modificador);

    public abstract List<IModificador> obtenerModificadores(Jugador jugador);

    public abstract void actualizarPuntaje() throws NoQuedanUsosExcepcion;

    public boolean todosContestaron(){
        if(respuestasDeJugadores.size() == 1)
            return false;
        return jugadores.size() % (respuestasDeJugadores.size()) == 0;
    }

}
