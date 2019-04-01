package challenge.repository;

import challenge.domain.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository  extends MongoRepository<Recipe, String> {

    List<Recipe> findByIngredientsOrderByTitle(String ingredientes);

//    Deve pesquisar nos campos title e description
//    Deve pesquisar em qualquer lugar do texto
//    Deve pesquisar usando case-insensitive
//    Ordenar pelo campo title em ordem alfabética ascendente.
//    Ex: /recipe/search?search=choco
//    Search

//    List<Recipe> findAllByTitleContainsOrDescriptionContainsOrderByTitle(String palavra, String palavra2);
      List<Recipe> findByTitleContainingIgnoreCase(String palavra);


}
