package hh.palvelinohj.swallow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohj.swallow.domain.Place;
import hh.palvelinohj.swallow.domain.PlaceRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PlaceRepositoryTest {

	@Autowired
    private PlaceRepository repository;
	
	@Test
    public void createNewPlace() {
		
		Place place = new Place("Koulu 2", "Osoite 2");
		repository.save(place);
		assertThat(place.getPlaceId()).isNotNull();
	}
	
	@Test
    public void deletePlace() {
		
		Place place = new Place("Koulu 2", "Osoite 2");
		repository.save(place);
		long idc = place.getPlaceId();
		repository.delete(place);
		assertThat(repository.findById(idc)).isEmpty();
	}
	
	@Test
	public void findByNameShoudReturnPlace() {
		List<Place> places = repository.findByName("Urheilutalo 1");
		
		assertThat(places).hasSize(1);
		assertThat(places.get(0).getAddress()).isEqualTo("Osoite 1");
	}
}
