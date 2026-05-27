package exception;

public class NoSlotsAvailableException extends PetVetException {
    public NoSlotsAvailableException() {
        super("No hay cupos disponibles para este servicio");
    }
}