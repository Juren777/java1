package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SessionManager {

    private HashMap<String, Integer> key;

    private HashMap<Integer, UserSession> sessions;

    private int sessionValid;

    // создает экземпляр SessionManager и сохраняет sessionValid - период валидности сессии в секундах
    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
        this.sessions = new HashMap<>();
        this.key = new HashMap<>();

    }

    // добавляет новую сессию пользователя
    public void add(UserSession userSession) {
        sessions.put(userSession.getSessionHandle(), userSession);
        key.put(userSession.getUserName(), userSession.getSessionHandle());
    }

    public UserSession find(String userName) {
        if (key.containsKey(userName)) {
            sessions.get(key.get(userName)).updateLastAccess();
            return sessions.get(key.get(userName));
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        if (sessions.containsKey(sessionHandle)) {
            UserSession userSession = sessions.get(sessionHandle);
            Duration duration = Duration.between(userSession.getLastAccess(), LocalDateTime.now());
            if (duration.toSeconds() < this.sessionValid) {
                userSession.updateLastAccess();
                return userSession;
            }
        }
        return null;
    }

    //  удаляет указанную сессию пользователя
    public void delete(int sessionHandle) {
        key.remove(sessions.get(sessionHandle).getUserName());
        sessions.remove(sessionHandle);
    }


    // удаляет все сессии с истекшим сроком годности.
    public void deleteExpired() {

        Collection<UserSession> ss = new HashSet<>();

        for (Map.Entry<Integer, UserSession> us : this.sessions.entrySet()
        ) {
            Duration duration = Duration.between(us.getValue().getLastAccess(), LocalDateTime.now());
            if (duration.toSeconds() >= this.sessionValid) {
                ss.add(us.getValue());
            }
        }
        for (UserSession us: ss
             ) {
            key.remove(us.getUserName());
            sessions.remove(us.getSessionHandle());
        }

    }

    public static void main(String[] args) throws InterruptedException {

        SessionManager sm = new SessionManager(10);
        if (sm.find("user1") == null) {
            UserSession userSession1 = new UserSession("user1");
            sm.add(userSession1);
            System.out.println(sm.get(userSession1.getSessionHandle()));
            System.out.println(sm.get(userSession1.getSessionHandle()));
            Thread.sleep(11000L);
            System.out.println(sm.get(userSession1.getSessionHandle()));
        }
        UserSession userSession2 = new UserSession("user2");
        sm.add(userSession2);
        Thread.sleep(5000L);

        UserSession userSession3 = new UserSession("user3");
        sm.add(userSession3);
        Thread.sleep(5000L);

        sm.deleteExpired();
        for (Map.Entry<Integer, UserSession> entry : sm.sessions.entrySet()
        ) {
            System.out.println("осталось - " + entry);

        }
        sm.delete(userSession3.getSessionHandle());

        for (Map.Entry<Integer, UserSession> entry : sm.sessions.entrySet()
        ) {
            System.out.println("осталось - " + entry);

        }

    }
}
