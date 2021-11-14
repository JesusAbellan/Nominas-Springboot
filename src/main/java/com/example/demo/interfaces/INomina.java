package com.example.demo.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Nomina;

@Repository
public interface INomina extends CrudRepository<Nomina, Integer> {
	@Query(value = "SELECT * FROM Nominas.nominas as n WHERE n.DNI = :dni", nativeQuery=true)
	Optional<Nomina> findByDNI(@Param("dni") String dni);
}
