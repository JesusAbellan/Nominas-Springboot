package com.example.demo.interfacesService;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Empleado;

public interface IEmpleadoService {
	public List<Empleado> getEmpleados();
	
	public Optional<Empleado> getEmpleadoDNI(String dni);
	
	public List<Empleado> getEmpleadoNombre(String nombre);
	
	public List<Empleado> getEmpleadoSexo(String sexo);
	
	public List<Empleado> getEmpleadoCategoria(int categoria);
	
	public List<Empleado> getEmpleadoAnyos(int anyos);
	
	public List<String> getDNIs();
	
	public void actualizarEmpleado(Empleado emp);
	
	public void eliminar(Empleado emp);
}
