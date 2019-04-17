package hh.palvelinohj.swallow;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohj.swallow.domain.User;
import hh.palvelinohj.swallow.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
    private UserRepository repository;
	
	@Test
    public void createNewUser() {
		
		User usert = new User("usert@gmail.com", "$2a$04$hFxjj6Y41fiP9RuaP5./feAR6G99wZGcNE8DuLnnGgmXFQ8QRbtxS", "Nimi", "Sukunimi", 2011, "USER");
		repository.save(usert);
		assertThat(usert.getId()).isNotNull();
	}
	
	@Test
    public void deleteUser() {
		
		User userd = new User("usert@gmail.com", "$2a$04$hFxjj6Y41fiP9RuaP5./feAR6G99wZGcNE8DuLnnGgmXFQ8QRbtxS", "Nimi", "Sukunimi", 2011, "USER");
		repository.save(userd);
		long idd = userd.getId();
		repository.delete(userd);
		assertThat(repository.findById(idd)).isEmpty();
	}
	
	@Test
	public void findByUsernameShoudReturnUser() {
		User user = repository.findByUsername("admin");
		
		assertThat(user).isNotNull();
		
	}
}

