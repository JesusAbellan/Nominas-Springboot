package com.example.demo.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Empleado;

@Repository
public interface IEmpleado extends CrudRepository<Empleado,String>{
	@Query(value ="SELECT * FROM Nominas.empleados as e WHERE e.Nombre_Completo = :nombre", nativeQuery=true)
	List<Empleado> findByNombre(@Param("nombre")String nombre);
	
	@Query(value ="SELECT * FROM Nominas.empleados as e WHERE e.Sexo = :sexo", nativeQuery=true)
	List<Empleado> findBySexo(@Param("sexo")String sexo);

	@Query(value ="SELECT * FROM Nominas.empleados as e WHERE e.Categoria = :categoria", nativeQuery=true)
	List<Empleado> findByCategoria(@Param("categoria")int categoria);

	@Query(value ="SELECT * FROM Nominas.empleados as e WHERE e.Anyos = :anyos", nativeQuery=true)
	List<Empleado> findByAnyos(@Param("anyos")int anyos);
	
	@Query(value ="SELECT e.DNI FROM Nominas.empleados as e", nativeQuery=true)
	List<String> findDNIs();
}
