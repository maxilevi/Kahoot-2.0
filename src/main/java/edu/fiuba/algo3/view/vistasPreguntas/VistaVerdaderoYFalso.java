package edu.fiuba.algo3.view.vistasPreguntas;

import edu.fiuba.algo3.modelo.general.Jugador;
import edu.fiuba.algo3.modelo.general.Kahoot;
import edu.fiuba.algo3.modelo.preguntas.Opcion;
import edu.fiuba.algo3.view.eventos.TerminarPreguntaVerdaderoYFalso;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaVerdaderoYFalso extends VistaTipoDePregunta {

    public VistaVerdaderoYFalso(Kahoot modelo, Jugador jugador, Stage stage){
        super(modelo,jugador, stage);
        this.getStylesheets().add(getClass().getResource("/css/escenaGeneral.css").toExternalForm());
        this.obtenerBotonesParaResponder(modelo, jugador, stage);
    }




    private void obtenerBotonesParaResponder(Kahoot modelo, Jugador jugador, Stage stage){
        int i = -400;
        for(Opcion opcion : modelo.obtenerPreguntaActual().obtenerOpciones()){
            Button boton = new Button(opcion.obtenerTexto());
            boton.setOnAction(new TerminarPreguntaVerdaderoYFalso(opcion,modelo,jugador,stage));
            getChildren().add(boton);
            setMargin(boton, new Insets(-200, 0, 0, i));
            i += 800;
        }
    }

    @Override
    public void forzarContestar() {
        new TerminarPreguntaVerdaderoYFalso(null, modelo, jugador, stage).handle(null);
    }
}
