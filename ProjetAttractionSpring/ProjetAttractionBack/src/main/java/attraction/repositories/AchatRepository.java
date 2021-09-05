package attraction.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import attraction.model.Achat;
import attraction.model.Element;
import attraction.model.Parc;



public interface AchatRepository extends JpaRepository<Achat, Integer>  {

	Optional<Achat> findByElementAndParc(Element element,Parc parc);
	List<Achat> findByTypeElementAndParc(String type,Parc parc);
	List<Achat> findByParc(Parc p);

	@Query("select niveauAmelioration from Achat where parc=:parc and element=:element")
	Optional<Integer> Nvamelioration (@Param("parc")Parc parc,@Param("element") Element element);

	@Query("select a from Achat a where a.parc.id=:id")
	List<Achat> findByIdParc (@Param("id")Integer id);
	

}
