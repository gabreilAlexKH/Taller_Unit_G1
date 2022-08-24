package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tallerprueba.Employee;
import tallerprueba.EmployeeType;

public class EmployeeTest {
	
	private final float rmu = (float) 386.0;
	private int month;
	
	@Before
	public void setBefore() {	
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		month = localDate.getMonthValue();

		
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(new Employee(300, "USD", (float) 0.1, EmployeeType.Supervisor));
	}
	
	
	/**
	 * Testea que el salario de Worker sea calculado correctamente asumiendo que se usan USD
	 */
	@Test
	public void testSalarioWorker() {
		
		Employee empleado = new Employee(500 , "USD" , (float) 0.2 , EmployeeType.Worker);
		
		float salario = empleado.cs();
		
		if(month%2 == 0) {
			assertEquals( (float) 500 , salario , 0);
			
		}else {
			assertEquals( (float) 500 + rmu/12*2  , salario , 0);
		}
			
	}
	
	/**
	 * Testea que el salario de Supervisor sea calculado correctamente asumiendo que se usan USD
	 */ 
	@Test
	public void testSalarioSupervisor() {
		
		Employee empleado = new Employee(750 , "USD" , 0.25F , EmployeeType.Supervisor);
				
		float salario = empleado.cs();
		float salarioEsperado = 750 + (0.25F * 0.35F);
		
		if(month%2 == 0) {
			assertEquals( (float) salarioEsperado , salario , 0);
			
		}else {
			salarioEsperado += rmu/12*2;
			assertEquals( (float) salarioEsperado  , salario , 0);
		}
			
	}
	
	/**
	 * Testea que el salario de Manager sea calculado correctamente  asumiendo que se usan USD
	 */
	@Test
	public void testSalarioManager() {
		
		Employee empleado = new Employee(1000 , "USD" , 0.3F , EmployeeType.Manager);
		
		float salario = empleado.cs();
		float salarioEsperado = 1000 + (0.3F * 0.7F);
		
		if(month%2 == 0) {
			assertEquals( (float) salarioEsperado , salario , 0);
			
		}else {
			salarioEsperado += rmu/12*2;
			assertEquals( (float) salarioEsperado  , salario , 0);
		}
			
	}
}
