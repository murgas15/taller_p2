package exception;

public class DuplicateAppointmentException extends PetVetException {
    public DuplicateAppointmentException() {
        super("Ya existe una cita registrada con ese código");
    }
}