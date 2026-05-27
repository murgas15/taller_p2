package exception;

public class InvalidEmailException extends PetVetException {
    public InvalidEmailException() {
        super("El email debe contener el símbolo @");
    }
}