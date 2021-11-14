package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nominas")
public class Nomina {
		@Id
		@Column(name="ID")
		private int id;
		
		private String dni;
		private int sueldo;
		private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
				230000 };

		public int sueldo(Empleado emp) {
			int sueldo = SUELDO_BASE[emp.getCategoria() - 1] + 5000 * emp.getAnyos();
			return sueldo;
		}
		
		public int getSueldo() {
			return sueldo;
		}
		public void setSueldo(int sueldo) {
			this.sueldo = sueldo;
		}
}
