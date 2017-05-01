package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static org.junit.Assert.*;



public class Person_Test {
	private static PersonDomainModel person1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		

		 
		try {
			person1Birth = dateFormat.parse("1996-05-30");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		person1 = new PersonDomainModel();
		person1.setFirstName("Siyu");
		person1.setLastName("Xu");
		person1.setStreet("kent hall");
		person1.setPostalCode(19711);
		person1.setCity("Newark");
		person1.setBirthday(LocalDate.of(1996, 05, 30));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		PersonDomainModel pers;
		PersonDAL.deletePerson(person1.getPersonID());
		pers = PersonDAL.getPerson(person1.getPersonID());
		assertNull("Not here",pers);
	}
	
	@Test
	public void AddPersontest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull(per);
	}
	@Test
	public void UpdatePersontest() {
		PersonDomainModel per;
		PersonDAL.addPerson(person1);
		person1.setFirstName("Siyu1");
		PersonDAL.updatePerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		assertEquals(person1.getFirstName(), "Siyu1");
	}
	@Test
	public void DeletePersonTest(){
		PersonDomainModel per;		
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull(per);	
		
		PersonDAL.addPerson(person1);			
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull(per);
		
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull(per);	
		
	}
}
