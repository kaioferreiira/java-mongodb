package challenge.service;

import java.util.List;
import java.util.Optional;

import challenge.domain.RecipeComment;
import challenge.domain.Recipe;
import challenge.domain.RecipeDTO;
import challenge.repository.RecipeCommentRepository;
import challenge.repository.RecipeRepository;
import challenge.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private RecipeCommentRepository recipeCommentRepository;

	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	public RecipeDTO findById(String id){
		Optional<Recipe> recipe = recipeRepository.findById(id);
		recipe.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
		RecipeDTO  recipeDTO = converterRecipeFromRecipeDTO(recipe.get());
		return recipeDTO;
 	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {

		Optional<Recipe> objectRecipeBanco = Optional.of(recipeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!")));

			Recipe newObject = new Recipe();
			newObject.setId(id);
			newObject.setTitle(recipe.getTitle());
			newObject.setDescription(recipe.getDescription());
			newObject.setLikes(objectRecipeBanco.get().getLikes());
			newObject.setIngredients(recipe.getIngredients());
			newObject.setComments(objectRecipeBanco.get().getComments());

			System.out.println(newObject);

			recipeRepository.save(newObject);

	}

//	private void updateData(Recipe newObj, Recipe obj) {
//		newObj.setTitle(obj.getTitle());
//		newObj.setDescription(obj.getDescription());
//		newObj.setLikes(obj.getLikes());
//		newObj.setComents(obj.getComents());
//		newObj.setIngredients(obj.getIngredients());
//	}

	@Override
	public void delete(String id) {
//		findById(id);
		recipeRepository.deleteById(id);
	}

//	@Override
//	public Recipe get(String id) {
//		Optional<Recipe> recipe = recipeRepository.findById(id);
//		return recipe.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
//	}

	@Override
	public Recipe get(String id) {

		Optional<Recipe> recipe = recipeRepository.findById(id);

	 return recipe.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findByIngredientsOrderByTitle(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return recipeRepository.findByTitleContainingIgnoreCase(search);
	}

	@Override
	public void like(String id, String userId) {
		Recipe newObjeto = new Recipe();

		Optional<Recipe> recipe = recipeRepository.findById(id);

		if (newObjeto == null){
			throw new IllegalArgumentException("não foi localizado o id!");
		}else {
			newObjeto.setId(recipe.get().getId());
			newObjeto.setComments(recipe.get().getComments());
			newObjeto.setDescription(recipe.get().getDescription());
			newObjeto.setIngredients(recipe.get().getIngredients());
			newObjeto.setTitle(recipe.get().getTitle());

			List likes = recipe.get().getLikes();
			likes.add(userId);

			newObjeto.setLikes(likes);
		}

		System.out.println(newObjeto);
		System.out.println();

		recipeRepository.save(newObjeto);
	}

	@Override
	public void unlike(String id, String userId) {
		Recipe newObjectRecipe =  new Recipe();
		Optional<Recipe> recipe = recipeRepository.findById(id);

		if(!recipe.isPresent()) {

			//throw new IllegalArgumentException("Id nao localizado");
		}else {
			System.out.println(recipe.get().getId());

			newObjectRecipe.setId(recipe.get().getId());
			newObjectRecipe.setComments(recipe.get().getComments());
			newObjectRecipe.setDescription(recipe.get().getDescription());
			newObjectRecipe.setIngredients(recipe.get().getIngredients());
			newObjectRecipe.setLikes(recipe.get().getLikes());
			newObjectRecipe.setTitle(recipe.get().getTitle());

			List<String> likes = newObjectRecipe.getLikes();

			likes.remove(userId);

			newObjectRecipe.setLikes(likes);

			recipeRepository.save(newObjectRecipe);
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		RecipeComment recipeComment = recipeCommentRepository.save(comment);
		Optional<Recipe> r = recipeRepository.findById(id);
		Recipe recipe = new Recipe();
		recipe.setId(r.get().getId());
		recipe.setComments(r.get().getComments());
		recipe.setDescription(r.get().getDescription());
		recipe.setIngredients(r.get().getIngredients());
		recipe.setLikes(r.get().getLikes());
		recipe.setTitle(r.get().getTitle());
		List<RecipeComment> lista = recipe.getComments();
		lista.add(comment);
		recipeRepository.save(recipe);
		return recipeComment;


	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Recipe newObjectRecipe = new Recipe();
		RecipeComment objComment = new RecipeComment();

		Optional<Recipe> recipe = recipeRepository.findById(id);

		if (recipe==null) {

		}else {
			objComment.setId(commentId);
			objComment=recipeCommentRepository.save(comment);

			newObjectRecipe .setId(recipe.get().getId());
			newObjectRecipe .setComments(recipe.get().getComments());
			newObjectRecipe .setDescription(recipe.get().getDescription());
			newObjectRecipe .setIngredients(recipe.get().getIngredients());
			newObjectRecipe .setLikes(recipe.get().getLikes());
			newObjectRecipe .setTitle(recipe.get().getTitle());

			List<RecipeComment> comentario = newObjectRecipe .getComments();

			comentario.add(objComment);

			newObjectRecipe.setComments(comentario);

			recipeRepository.save(newObjectRecipe );
		}


	}

	@Override
	public void deleteComment(String id, String commentId) {
		recipeCommentRepository.deleteById(commentId);

		Recipe obj = new Recipe();

		Optional<Recipe> recipe = recipeRepository.findById(id);

		if (recipe == null) {

		} else {

			obj.setId(recipe.get().getId());
			obj.setComments(recipe.get().getComments());
			obj.setDescription(recipe.get().getDescription());
			obj.setIngredients(recipe.get().getIngredients());
			obj.setLikes(recipe.get().getLikes());
			obj.setTitle(recipe.get().getTitle());

			List<RecipeComment> comentario = obj.getComments();

			for (int i = 0; i < comentario.size(); i++) {

				if (comentario.get(i).getId().equals(commentId)) {
					comentario.remove(i);
				}
			}
			obj.setComments(comentario);
			recipeRepository.save(obj);
		}
	}

	public  RecipeDTO converterRecipeFromRecipeDTO(Recipe recipe){
		return new RecipeDTO(recipe.getId(),recipe.getTitle(), recipe.getDescription(), recipe.getLikes(), recipe.getIngredients(), recipe.getComments());
	}

	public Recipe converterRecipeDTOfromRecipe(String id, RecipeDTO recipeDTO){
		return new Recipe(recipeDTO.getTitle(), recipeDTO.getDescription(), recipeDTO.getLikes(), recipeDTO.getIngredients(), recipeDTO.getComments());
	}


}


