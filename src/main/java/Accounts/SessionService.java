package Accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex Nevolinf on 22.12.2016.
 */
public class SessionService {
    private final Map<String, String> sessionIdToLogin;

    public SessionService() {
        sessionIdToLogin = new HashMap<String, String>();
    }
    public void addSession(String sessionId, String login) {
        sessionIdToLogin.put(sessionId, login);
    }
    public void deleteSession(String sessionId) {
        sessionIdToLogin.remove(sessionId);
    }
    public boolean checkToEmpty(String sessionId){
        if (sessionIdToLogin.containsKey(sessionId))
            return true;
        return false;
    }
}
