package es.upm.grise.profundizacion.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import es.upm.grise.profundizacion.exceptions.InvalidContentException;
import es.upm.grise.profundizacion.exceptions.WrongFileTypeException;

public class FileTest {

    /*
     * Smoke test (obligatorio)
     */
    @Test
    public void smokeTest() {
        File file = new File();
        assertNotNull(file);
    }

    /*
     * Constructor: content vacío pero no null
     */
    @Test
    public void testConstructorInicializaContentVacio() {
        File file = new File();
        assertNotNull(file.getContent());
        assertTrue(file.getContent().isEmpty());
    }

    /*
     * addProperty: newcontent == null
     */
    @Test
    public void testAddPropertyNullLanzaInvalidContentException() {
        File file = new File();
        file.setType(FileType.PROPERTY);

        assertThrows(InvalidContentException.class, () -> {
            file.addProperty(null);
        });
    }

    /*
     * addProperty: tipo IMAGE
     */
    @Test
    public void testAddPropertyEnImageLanzaWrongFileTypeException() {
        File file = new File();
        file.setType(FileType.IMAGE);

        char[] data = {'A', '=', '1'};

        assertThrows(WrongFileTypeException.class, () -> {
            file.addProperty(data);
        });
    }

    /*
     * addProperty: añade contenido correctamente
     */
    @Test
    public void testAddPropertyAgregaContenido() throws Exception {
        File file = new File();
        file.setType(FileType.PROPERTY);

        char[] data = {'A', '=', '1'};
        file.addProperty(data);

        assertEquals(3, file.getContent().size());
        assertEquals('A', file.getContent().get(0));
        assertEquals('=', file.getContent().get(1));
        assertEquals('1', file.getContent().get(2));
    }

    /*
     * addProperty: concatena contenido
     */
    @Test
    public void testAddPropertyConcatenaContenido() throws Exception {
        File file = new File();
        file.setType(FileType.PROPERTY);

        file.addProperty(new char[] {'A', '=', '1'});
        file.addProperty(new char[] {'B', '=', '2'});

        assertEquals(6, file.getContent().size());
    }

    /*
     * getCRC32: content vacío
     */
    @Test
    public void testGetCRC32ConContenidoVacioDevuelveCero() {
        File file = new File();
        assertEquals(0L, file.getCRC32());
    }

    /*
     * getCRC32: content con datos
     * (FileUtils está diseñado para ser controlado en testing)
     */
    @Test
    public void testGetCRC32DevuelveValorMockeado() throws Exception {
        File file = new File();
        file.setType(FileType.PROPERTY);
        file.addProperty(new char[] {'A'});

        // FileUtils devuelve el valor que tenga CRC32
        FileUtils utils = new FileUtils();
        utils.setCRC(999L);

        assertEquals(999L, file.getCRC32());
    }
}
