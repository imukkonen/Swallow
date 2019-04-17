package hh.palvelinohj.swallow.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface DancegroupRepository extends CrudRepository<Dancegroup, Long> {
	List<Dancegroup> findByName (@Param("name") String name);
}
