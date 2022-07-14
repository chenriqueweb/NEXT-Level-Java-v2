package br.com.henrique.controller.exception;

@SuppressWarnings("serial")
public class ViaCepFormatException extends RuntimeException {
    public ViaCepFormatException(String s) {
        super(s);
    }
}
