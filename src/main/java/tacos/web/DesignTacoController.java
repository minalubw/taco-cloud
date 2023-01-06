package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;
import tacos.domain.Taco;
import tacos.domain.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingRepo) {
		this.ingRepo = ingRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingRepo.findAll().forEach(ing -> ingredients.add(ing));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}

	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}
		// save taco to database...will be done soon
		else {
			log.info("Processing Design: " + design);
			return "redirect:/orders/current";
		}
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(ing -> ing.getType().equals(type)).collect(Collectors.toList());
	}
}
