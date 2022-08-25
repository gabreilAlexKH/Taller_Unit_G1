package test;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	/**
	 * Test para el constructor de la clase Employee
	 */
	@Test
	public void testConstructor() {
		assertNotNull(new Employee(300, "USD", 0.1f, EmployeeType.Supervisor));
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
		
		Employee empleado = new Employee(750 , "USD" ,0.5f , EmployeeType.Supervisor);
				
		float salario = empleado.cs();
		float salarioEsperado = 750.175f;
		
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
		
		Employee empleado = new Employee(1000 , "USD" , 0.8f , EmployeeType.Manager);
		
		float salario = empleado.cs();
		float salarioEsperado = 1000.56f;
		
		if(month%2 == 0) {
			assertEquals( (float) salarioEsperado , salario , 0);
			
		}else {
			salarioEsperado += rmu/12*2;
			assertEquals( (float) salarioEsperado  , salario , 0);
		}
			
	}
	
	/**
	 * Testea que el salario  sea calculado cuando no sean dolares
	 */
	@Test
	public void testSalarioNoUSD() {
		
		Employee empleado = new Employee(1000 , "GBP" , 0.4f , EmployeeType.Manager);
		
		float salario = empleado.cs();
		float salarioEsperado = 950.28f;
		
		if(month%2 == 0) {
			assertEquals( (float) salarioEsperado , salario , 0);
			
		}else {
			salarioEsperado += rmu/12*2;
			assertEquals( (float) salarioEsperado  , salario , 0);
		}
			
	}
	
	/**
	 * Testea el bono segun el tipo de trabajador 'USD'
	 */
	@Test
	public void testBonoWorkerUsd() {
		Employee worker = new Employee(500.0f , "USD", 0.1f, EmployeeType.Worker);
	    assertEquals(386.0, worker.CalculateYearBonus(), 1);
	
		
	}
	@Test
	public void testBonoSupervisorUsd() {
		
	    Employee supervisor = new Employee(800.0f , "USD", 0.2f, EmployeeType.Supervisor);
	    assertEquals(993.0,  supervisor.CalculateYearBonus(), 1);	
	}
	

	@Test
	public void testBonoManagerUsd() {
		
	    Employee manager = new Employee(1500.0f , "USD", 0.3f, EmployeeType.Manager);
	    
	    assertEquals(1886.0, manager.CalculateYearBonus(), 1);

	   
		
	}
	
	/**
	 * Testea el bono si currency no es 'USD'
	 */
	@Test
	public void testBonoNotUsd() {
		Employee worker = new Employee(500.0f , "EUR", 0.1f, EmployeeType.Worker);
	    assertEquals(386.0, worker.CalculateYearBonus(), 1);
	        
	    
	
	}
	



}
