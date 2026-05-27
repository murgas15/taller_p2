package exception;

public class ServiceNotAvailableException extends PetVetException {
    public ServiceNotAvailableException() {
        super("El servicio no está en estado Disponible");
    }
}