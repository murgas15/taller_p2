package exception;

public class AppointmentNotFoundException extends PetVetException {
    public AppointmentNotFoundException() {
        super("No se encontró la cita con ese código");
    }
}