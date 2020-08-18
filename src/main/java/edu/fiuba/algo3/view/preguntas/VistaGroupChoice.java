package edu.fiuba.algo3.view.preguntas;

import edu.fiuba.algo3.controller.ControladorSeleccionGrupos;
import edu.fiuba.algo3.modelo.general.Jugador;
import edu.fiuba.algo3.modelo.general.Kahoot;
import edu.fiuba.algo3.modelo.preguntas.Opcion;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.RespuestaDeJugador;
import edu.fiuba.algo3.view.VistaPregunta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VistaGroupChoice extends VistaTipoDePregunta {
    private ObservableList<Integer> listaGrupos;
    private VBox vBoxOpciones;
    private Label nombreJugador;
    private Label preguntaTexto;
    private Button botonAceptar;
    private ArrayList<RespuestaDeJugador> respuestas;
    private Kahoot modelo;
    private Jugador jugador;
    private Stage stage;

    public VistaGroupChoice(Kahoot modelo, Jugador jugador, Stage stage) {
        super();
        this.modelo = modelo;
        this.jugador = jugador;
        this.stage = stage;
        this.getChildren().clear();
        inicializarTextos(modelo,jugador);
        setStackPane();
        this.setColorFondo();
        botonAceptar.setOnAction(this::mandarRespuestas);
        this.getStylesheets().add(getClass().getResource("/css/escenaPregunta.css").toExternalForm());
    }

    private void setStackPane() {
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(nombreJugador);
        this.setMargin(nombreJugador, new Insets(-600, 0, 0, 0));

        this.getChildren().add(preguntaTexto);
        this.setMargin(preguntaTexto, new Insets(-500, 0, 0, 0));

        this.getChildren().add(vBoxOpciones);

        this.getChildren().add(botonAceptar);
        this.setMargin(botonAceptar, new Insets(0, 0, 0, 0));

    }

    private void inicializarTextos(Kahoot modelo,Jugador jugador) {
        Pregunta pregunta = modelo.obtenerPreguntaActual();
        nombreJugador.setText(jugador.obtenerNombre());
        nombreJugador.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        preguntaTexto.setText(pregunta.obtenerTexto());
        preguntaTexto.setFont(new Font(20));

        botonAceptar = new Button("Aceptar");
        botonAceptar.setAlignment(Pos.CENTER);


        listaGrupos.add(1);
        listaGrupos.add(2);

        respuestas = new ArrayList<>();
        vBoxOpciones = new VBox();
        for (Opcion opcion : pregunta.obtenerOpcionesMezcladas()) {
            RespuestaDeJugador nuevaRespuestaDeJugador = new RespuestaDeJugador(opcion);
            respuestas.add(new RespuestaDeJugador(opcion));
            ChoiceBox<Integer> nuevaChoiceBox = new ChoiceBox<>(listaGrupos);
            Label nuevaOpcionTexto = new Label(opcion.obtenerTexto());

            nuevaOpcionTexto.setFont(Font.font(18));
            nuevaChoiceBox.setPrefHeight(nuevaOpcionTexto.getPrefHeight());

            HBox nuevaCajaOpcion = new HBox(nuevaOpcionTexto, nuevaChoiceBox);
            nuevaCajaOpcion.setAlignment(Pos.CENTER);
            nuevaChoiceBox.setValue(1);

            nuevaChoiceBox.setOnAction(new ControladorSeleccionGrupos(nuevaRespuestaDeJugador, nuevaChoiceBox));

            HBox cajaOpcionYChoiceBox = new HBox(nuevaOpcionTexto,nuevaChoiceBox);
            vBoxOpciones.getChildren().add(cajaOpcionYChoiceBox);
            vBoxOpciones.setFillWidth(true);
        }
    }

    private void setColorFondo() {
        Color color = Color.web("#eecd86",1.0);
        this.setBackground(new Background((new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY))));
    }

    public void mandarRespuestas(ActionEvent event) {
        modelo.jugadorResponder(jugador, respuestas);
        VistaPregunta vistaAux = new VistaPregunta();
        vistaAux.CambiarPreguntaAOtroJugador(modelo, jugador, stage);
        this.getChildren().clear();
        this.getChildren().addAll(vistaAux);
    }

    @Override
    public void forzarContestar() {
        mandarRespuestas(null);
    }
}
