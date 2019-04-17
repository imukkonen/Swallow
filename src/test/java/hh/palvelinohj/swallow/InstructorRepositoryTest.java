package hh.palvelinohj.swallow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohj.swallow.domain.Instructor;
import hh.palvelinohj.swallow.domain.InstructorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InstructorRepositoryTest {

	@Autowired
    private InstructorRepository repository;
	
	@Test
    public void createNewInstructor() {
		
		Instructor instructor = new Instructor("Sanna", "Metsälä");
		repository.save(instructor);
		assertThat(instructor.getInstructorId()).isNotNull();
	}
	
	@Test
    public void deleteInstructor() {
		
		Instructor instructor = new Instructor("Sanna", "Metsälä");
		repository.save(instructor);
		long idc = instructor.getInstructorId();
		repository.delete(instructor);
		assertThat(repository.findById(idc)).isEmpty();
	}
	
	@Test
	public void findByLastNameShoudReturnInstructor() {
		List<Instructor> instructors = repository.findByLastName("Karhu");
		
		assertThat(instructors).hasSize(1);
		assertThat(instructors.get(0).getFirstName()).isEqualTo("Leena");
		
	}
}
