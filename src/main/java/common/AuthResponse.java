package common;

public class AuthResponse extends AbstractMessage {
   /* private boolean auth;

    public AuthResponse(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    } */

    private String response;

    public AuthResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}

