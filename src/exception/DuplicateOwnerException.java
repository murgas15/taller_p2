package exception;

public class DuplicateOwnerException extends PetVetException {
    public DuplicateOwnerException() {
        super("Ya existe un dueño registrado con esa cédula");
    }
}