package com.example.demo.interfacesService;

import java.util.Optional;

import com.example.demo.models.Nomina;

public interface INominaService {
	public Optional<Nomina> getNominaDNI(String dni);

	public void actualizarNomina(Nomina nom);

	public void eliminar(Nomina nom);
}
