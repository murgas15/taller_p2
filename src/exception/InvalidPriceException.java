package exception;

public class InvalidPriceException extends PetVetException {
    public InvalidPriceException() {
        super("El precio del servicio debe ser mayor a cero");
    }
}