package me.thomas.security;

/**
 * Created by zhaoxs on 2015/9/29 0029.
 */
public class NoPermissionException extends Exception {

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
