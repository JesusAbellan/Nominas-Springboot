package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.DatosNoCorrectosException;
import com.example.demo.interfacesService.IEmpleadoService;
import com.example.demo.interfacesService.INominaService;
import com.example.demo.models.Empleado;
import com.example.demo.models.Nomina;

@Controller
@RequestMapping
public class EmpresaController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private INominaService nominaService;
	
	@GetMapping("/")
	public String redirect() {
		return "welcome";
	}
	@GetMapping("/welcome")
	public String menuPrincipal() {
		return "welcome";
	}
	
	@GetMapping("/mostrarEmpleados")
	public String mostrarEmpleados(Model model) {
		List<Empleado> empleados = this.empleadoService.getEmpleados();
		model.addAttribute("empleados",empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/mostrarSalario")
	public String mostrarSalario(Model model) {
		return "mostrarSalario";
	}
	
	@PostMapping("/salarioEspecifico")
	public String salarioEspecifico(@RequestParam String dni,Model model) {
		Optional<Empleado> empleado = this.empleadoService.getEmpleadoDNI(dni);
		Optional<Nomina> nomina = this.nominaService.getNominaDNI(dni);
		
		if(!nomina.isEmpty()) {
			Nomina nom = nomina.get();
			model.addAttribute("nomina",nom);
		}
		if(!empleado.isEmpty()) {
			Empleado emp = empleado.get();
			model.addAttribute("empleado", emp);			
		}
		return "salarioEspecifico";
	}
	
	@GetMapping("/meditar/{dni}")
	public String meditar(@PathVariable String dni, Model model) {
		Optional<Empleado> empleado = empleadoService.getEmpleadoDNI(dni);
		model.addAttribute("empleado", empleado);
		return "editar";
	}
	
	@GetMapping("/buscarEmpleados")
	public String buscarEmpleados() {
		return "buscarEmpleados";
	}
	
	@PostMapping("/buscarDNI")
	public String buscarDNI(@RequestParam String dni, Model model) {
		List<Empleado> empleados = new ArrayList<Empleado>();
		Optional<Empleado> empleado = empleadoService.getEmpleadoDNI(dni);
		if(!empleado.isEmpty()) {
			Empleado emp = empleado.get();		
			empleados.add(emp);
		}
		model.addAttribute("empleados", empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/buscarNombre")
	public String buscarNombre(@RequestParam String nombre, Model model) {
		List<Empleado> empleados = empleadoService.getEmpleadoNombre(nombre);
		model.addAttribute("empleados", empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/buscarSexo")
	public String buscarSexo(@RequestParam String sexo, Model model) {
		List<Empleado> empleados = empleadoService.getEmpleadoSexo(sexo);
		model.addAttribute("empleados", empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/buscarCategoria")
	public String buscarCategoria(@RequestParam int categoria, Model model) {
		List<Empleado> empleados = empleadoService.getEmpleadoCategoria(categoria);
		model.addAttribute("empleados", empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/buscarAnyos")
	public String buscarAnyos(@RequestParam int anyos, Model model) {
		List<Empleado> empleados = empleadoService.getEmpleadoAnyos(anyos);
		model.addAttribute("empleados", empleados);
		return "mostrarEmpleados";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String eliminar(@PathVariable String dni, Model model) {
		Optional<Nomina> nomina = nominaService.getNominaDNI(dni);
		
		if(!nomina.isEmpty()) {
			Nomina nom = nomina.get();
			nominaService.eliminar(nom);
		}
		
		Optional<Empleado> empleado = empleadoService.getEmpleadoDNI(dni);
		if(!empleado.isEmpty()) {
			Empleado emp = empleado.get();
			empleadoService.eliminar(emp);
		}
		
		model.addAttribute("empleados",empleadoService.getEmpleados());
		return "redirect:/mostrarEmpleados";
	}
	
	@PostMapping("/editar")
	public String editar(@RequestParam String nombre,@RequestParam String dni,@RequestParam String sexo,@RequestParam int categoria,@RequestParam int anyos, Model model) {
		Empleado emp = null;
		try {
			emp = new Empleado(nombre,dni,sexo,categoria,anyos);
		} catch (DatosNoCorrectosException e) {
			e.printStackTrace();
		}
		empleadoService.actualizarEmpleado(emp);
		
		Optional<Nomina> nomina = nominaService.getNominaDNI(dni);
		if(!nomina.isEmpty()) {
			Nomina nom = nomina.get();
			nom.setSueldo(nom.sueldo(emp));
			nominaService.actualizarNomina(nom);
		}
		
		model.addAttribute("empleados",empleadoService.getEmpleados());
		return "mostrarEmpleados";
	}
}
