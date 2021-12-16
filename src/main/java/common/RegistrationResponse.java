package common;

public class RegistrationResponse extends AbstractMessage {
    private boolean registrated;

    RegistrationResponse(boolean registrated) {
        this.registrated = registrated;
    }

   public boolean isRegistrated() {
        return registrated;
   }

}
