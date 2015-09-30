package me.thomas.security;

/**
 * Created by zhaoxs on 2015/9/29 0029.
 */
public class SecurityException extends RuntimeException {

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(String message) {
        super(message);
    }

}
