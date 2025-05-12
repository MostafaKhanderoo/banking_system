package app.service.authentication;

import app.entity.Boss;

public class AuthenticationBoss {
    private static Boss loggedInBoss;

    public static void setLoggedInBoss(Boss boss) {
        loggedInBoss = boss;
    }

    public static Boss getLoggedInBoss() {
        return loggedInBoss;
    }

    public static void logout() {
        loggedInBoss = null;
    }
}
