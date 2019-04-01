package challenge.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import challenge.domain.RecipeComment;
import challenge.domain.RecipeDTO;
import challenge.domain.Recipe;
import challenge.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeController {

	@Autowired
	private RecipeServiceImpl service;

	@GetMapping("/recipe/findAll")
	public ResponseEntity<List<RecipeDTO>> findAll() {
		List<Recipe> recipe = service.findAll();
		List<RecipeDTO> listDto = recipe.stream()
										.map(recipe1 -> new RecipeDTO(recipe1.getId(), recipe1.getTitle(), recipe1.getDescription(), recipe1.getLikes(), recipe1.getIngredients(), recipe1.getComments()))
										.collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
		RecipeDTO recipe = service.findById(id);
		return ResponseEntity.ok(recipe);
	}

	@GetMapping("/recipe/{id}")
	public ResponseEntity <Recipe> get(@PathVariable String id) {
		Recipe recipe = service.get(id);
		return  ResponseEntity.ok(recipe);
	}

	@PostMapping("/recipe/salvar")
	public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
		Recipe obj = service.save(recipe);
		return ResponseEntity.ok(obj);
	}

	@PutMapping("/recipe/{id}")
	public void update(@PathVariable String id, @RequestBody Recipe recipeDTO) {
		service.update(id, recipeDTO);
	}

	@DeleteMapping("/recipe/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	//******** ENDPOINTS FINALIZADOS ***********************************************//

	@GetMapping("/recipe/ingredient")
	public ResponseEntity<List<Recipe>> listByIngredient(@RequestParam String ingredient) {
		return ResponseEntity.ok(service.listByIngredient(ingredient));
	}


	@GetMapping("/recipe/search")
	public ResponseEntity<List<Recipe>> search(@RequestParam String search) {
		return ResponseEntity.ok(service.search(search));
	}

	@PostMapping("/recipe/{id}/like/{userId}")
	public void like(@PathVariable String id, @PathVariable String userId) {
		service.like(id, userId);
	}

	@DeleteMapping("/recipe/{id}/like/{userId}")
	public void unlike(@PathVariable String id, @PathVariable String userId) {
		service.unlike(id, userId);
	}

	@PostMapping(value= {"/{id}/comment"})
	public ResponseEntity<RecipeComment> addComment(@PathVariable("id") String id, @RequestBody RecipeComment comentario) {
		return ResponseEntity.ok(service.addComment(id, comentario));
	}

	@PutMapping("/recipe/{id}/comment/{commentId}")
	public void updateComment(@PathVariable String id ,
							  @PathVariable String commentId,
							  @RequestBody RecipeComment comment) {
		service.updateComment(id, commentId, comment);
	}

	@DeleteMapping("/recipe/{id}/comment/{commentId}")
	public void deleteComment(@PathVariable String id ,
							  @PathVariable String commentId) {
		service.deleteComment(id, commentId);
	}

}
