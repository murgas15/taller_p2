package exception;

public class OwnerNotFoundException extends PetVetException {
    public OwnerNotFoundException() {
        super("No se encontró el dueño con esa cédula");
    }
}