package es.upm.grise.profundizacion.file;

public class FileUtils {

    private long CRC32;

    void setCRC(long CRC32) {
        this.CRC32 = CRC32;
    }

    long calculateCRC32(byte[] bytes) {
        return this.CRC32;
    }
}
