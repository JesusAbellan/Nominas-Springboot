package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.exception.DatosNoCorrectosException;

@Entity
@Table(name="empleados")
public class Empleado {
	@Id
	@Column(name="DNI")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String dni;
	@Column(name="Nombre_Completo")
	public String nombre;
	public String sexo;
	private int categoria;
	private int anyos;
	
	public Empleado() {
		super();
	}
	public Empleado(String nombre, String dni, String sexo) {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
		try {
			setCategoria(1);
		} catch (DatosNoCorrectosException dnce) {
			System.out.println(dnce.getMessage());
			System.exit(1);
		}
		this.anyos = 0;
	}
	
	public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) throws DatosNoCorrectosException{
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
		try {
			setCategoria(categoria);
		} catch (DatosNoCorrectosException dnce) {
			System.out.println(dnce.getMessage());
			System.exit(1);
		}
		if (anyos >= 0) {
			this.anyos = anyos;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyos) {
		this.anyos = anyos;
	}
	
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Datos no correctos");
		}
	}
	
	public int getCategoria() {
		return this.categoria;
	}
	
	public void incrAnyo() {
		this.anyos++;
	}

	public void imprime() {
		System.out.println("Nombre: " + this.nombre + ", DNI: " + this.dni + ", Categoria: " + this.categoria
				+ ", AÃ±os trabajados: " + this.anyos);
	}
}