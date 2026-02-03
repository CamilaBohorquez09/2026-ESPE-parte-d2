package es.upm.grise.profundizacion.file;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.profundizacion.exceptions.InvalidContentException;
import es.upm.grise.profundizacion.exceptions.WrongFileTypeException;

public class File {

    private FileType type;
    private List<Character> content;

    /*
     * Constructor
     */
    public File() {
        this.content = new ArrayList<Character>(); // vacío pero no null
    }

    /*
     * Method to test
     */
    public void addProperty(char[] newcontent)
            throws InvalidContentException, WrongFileTypeException {

        if (newcontent == null) {
            throw new InvalidContentException();
        }

        if (type == FileType.IMAGE) {
            throw new WrongFileTypeException();
        }

        for (char c : newcontent) {
            content.add(c);
        }
    }

    /*
     * Method to test
     */
    public long getCRC32() {

        if (content.isEmpty()) {
            return 0L;
        }

        // Conversión correcta según la especificación:
        // usar SOLO el byte menos significativo [0–255]
        byte[] bytes = new byte[content.size()];

        for (int i = 0; i < content.size(); i++) {
            bytes[i] = (byte) content.get(i).charValue();
        }

        FileUtils utils = new FileUtils();
        return utils.calculateCRC32(bytes);
    }

    /*
     * Setters / getters
     */
    public void setType(FileType type) {
        this.type = type;
    }

    public List<Character> getContent() {
        return content;
    }
}
