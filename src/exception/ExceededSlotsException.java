package exception;

public class ExceededSlotsException extends PetVetException {
    public ExceededSlotsException() {
        super("No se pueden reservar más de 3 cupos por cita");
    }
}