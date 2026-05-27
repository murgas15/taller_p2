package exception;

public class DuplicateServiceException extends PetVetException {
    public DuplicateServiceException() {
        super("Ya existe un servicio registrado con ese código");
    }
}