
public class CashBoxException extends RuntimeException {

    private String errorMessage;

    public CashBoxException(String msg) {
        this.errorMessage = msg;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }


}
