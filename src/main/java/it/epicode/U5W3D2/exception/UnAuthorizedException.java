package it.epicode.U5W3D2.exception;

public class UnAuthorizedException extends RuntimeException{

    public UnAuthorizedException(String message){
        super(message);
    }
}
