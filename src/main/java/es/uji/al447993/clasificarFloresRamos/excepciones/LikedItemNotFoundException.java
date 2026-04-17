package es.uji.al447993.clasificarFloresRamos.excepciones;

public class LikedItemNotFoundException extends RuntimeException {
    public LikedItemNotFoundException(String mensaje) {
        super(mensaje);
    }

    public LikedItemNotFoundException() {
        super("El elemento indicado no existe");
    }
}
