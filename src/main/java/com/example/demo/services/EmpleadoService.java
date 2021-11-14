package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaces.IEmpleado;
import com.example.demo.interfacesService.IEmpleadoService;
import com.example.demo.models.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private IEmpleado empleadoRepository;
	
	@Override
	public List<Empleado> getEmpleados() {
		return (List<Empleado>) empleadoRepository.findAll();
	}

	@Override
	public Optional<Empleado> getEmpleadoDNI(String dni) {
		return empleadoRepository.findById(dni);
	}

	@Override
	public List<Empleado> getEmpleadoNombre(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}

	@Override
	public List<Empleado> getEmpleadoSexo(String sexo) {
		return empleadoRepository.findBySexo(sexo);
	}

	@Override
	public List<Empleado> getEmpleadoCategoria(int categoria) {
		return  empleadoRepository.findByCategoria(categoria);
	}

	@Override
	public List<Empleado> getEmpleadoAnyos(int anyos) {
		return  empleadoRepository.findByAnyos(anyos);
	}

	@Override
	public List<String> getDNIs() {
		return  empleadoRepository.findDNIs();
	}
	
	public void actualizarEmpleado(Empleado emp) {
		empleadoRepository.save(emp);
	}
	
	public void eliminar(Empleado emp) {
		empleadoRepository.delete(emp);
	}

}
