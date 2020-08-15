package edu.fiuba.algo3.modelo.TestUnitarios;

import edu.fiuba.algo3.lectorDeArchivos.LectorDeArchivosJson;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.TipoMultipleChoice;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class TestsUnitariosLectorDeArchivosJson {
    LectorDeArchivosJson lectorDeArchivosJson = new LectorDeArchivosJson();

    @Test
    public void cargoUnaPreguntaYSeCreaCorrectamente() throws IOException, ParseException {
        List<Pregunta> preguntas = lectorDeArchivosJson.crearListaDePreguntas(Paths.get("src/main/resources/unaPregunta.json").toAbsolutePath().toUri());

        Pregunta pregunta = preguntas.get(0);
        assertEquals(pregunta.obtenerTexto(), "¿Quién gano gran hermano 2015?");
        assertEquals(pregunta.obtenerTipo().getClass(), TipoMultipleChoice.class);
        assertEquals(pregunta.getClass(),Pregunta.class);

    }

}
