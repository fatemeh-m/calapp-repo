package com.mycompany.server.exceptions;

public class InvalidOperatorException extends Exception {
    public InvalidOperatorException() {
        super("Invalid operator found!");
    }
}
