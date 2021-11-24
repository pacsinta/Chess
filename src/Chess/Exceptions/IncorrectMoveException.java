package Chess.Exceptions;

public class IncorrectMoveException extends Exception{
    public IncorrectMoveException(String errorMessage) {
        super(errorMessage);
    }
}
