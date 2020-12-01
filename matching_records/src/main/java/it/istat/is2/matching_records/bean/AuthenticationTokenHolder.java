package it.istat.is2.matching_records.bean;


public class AuthenticationTokenHolder {

    private static volatile AuthenticationTokenHolder instance;
    private String authenticationToken;

    public static AuthenticationTokenHolder getInstance() {
        if (instance == null) {
            synchronized (AuthenticationTokenHolder.class) {
                if (instance == null) {
                    instance = new AuthenticationTokenHolder();
                }
            }
        }
        return instance;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
