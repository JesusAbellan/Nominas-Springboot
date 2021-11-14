package com.example.demo.exception;

public class DatosNoCorrectosException extends Exception {
	public DatosNoCorrectosException(String errorMessage) {
		super(errorMessage);
	}
}
