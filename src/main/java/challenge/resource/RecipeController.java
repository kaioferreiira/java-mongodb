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
		List<RecipeDTO> listDto = recipe.stream().map(x -> new RecipeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}

//	@GetMapping("/findById/{id}")
//	public ResponseEntity <RecipeDTO> findById(@PathVariable String id) {
//		Recipe recipe = service.findById(id);
//		return ResponseEntity.ok(new RecipeDTO(recipe));
//	}

//	*****************************************************************************//

	@PostMapping("/recipe")
	public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
		Recipe obj = service.save(recipe);
		return ResponseEntity.ok(obj);
	}

	@PutMapping("/recipe/{id}")
	public void update(@PathVariable String id, @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping("/recipe/{id}")
	public void delete(@PathVariable String id) {
		service.delete(id);
	}


	@GetMapping("/recipe/{id}")
	public ResponseEntity<Optional<Recipe>> get(@PathVariable String id) {
		return  ResponseEntity.ok(service.get(id));
	}

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
