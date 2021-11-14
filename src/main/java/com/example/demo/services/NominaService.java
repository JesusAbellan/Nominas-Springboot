package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.INomina;
import com.example.demo.interfacesService.INominaService;
import com.example.demo.models.Nomina;


@Service
public class NominaService  implements INominaService{
	@Autowired
	private INomina nominaRepository;
	
	@Override
	public Optional<Nomina> getNominaDNI(String dni) {
		return nominaRepository.findByDNI(dni);
	}

	@Override
	public void actualizarNomina(Nomina nom) {
		nominaRepository.save(nom);
	}
	
	public void eliminar(Nomina nom) {
		nominaRepository.delete(nom);
	}
}
