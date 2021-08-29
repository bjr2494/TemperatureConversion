package gov.hasdrubal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/temperatureConversion")
public class TemperatureConversionController {

	@Autowired
	SessionManager sessionManager;

	@GetMapping("/mainPage")
	public String makeSelections(Model model) {

		Conversion conversion = this.sessionManager.getCurrentConversion();

		if (conversion == null) {
			model.addAttribute("conversion", new Conversion());
			model.addAttribute("noScales", "Give us some scales, specifically two!");	
		}
		else {
			model.addAttribute("conversion", conversion);
		}
		
		if (conversion != null && conversion.hasBothScales()) {
			if (this.sessionManager.getCurrentConversion().getTemperatureStart() == null) {
				model.addAttribute("temperatureStart", new Temperature());
			} else
				model.addAttribute("temperatureEnd", "The converted temperature!");		
		}
		
		return "temperatureConversion";
	}

	@PostMapping("/selectScales")
	public String selectScales(@Validated Conversion conversion, Errors errors, Model model,
			RedirectAttributes redirect) {

		if (conversion.getScaleStart() == null) {
			model.addAttribute("badScaleStart", "There needs to be a starting scale, yeah?");
			model.addAttribute("conversion", conversion);
			model.addAttribute("noScales", "Give us some scales, specifically two, and this time valid ones!");

			return "temperatureConversion";
		}

		if (conversion.getScaleEnd() == null) {
			model.addAttribute("badScaleEnd", "There needs to be an ending scale, yeah?");
			model.addAttribute("conversion", conversion);
			model.addAttribute("noScales", "Give us some scales, specifically two, and this time valid ones!");

			return "temperatureConversion";
		}

		if (conversion.getScaleStart() == null && conversion.getScaleEnd() == null) {
			model.addAttribute("twoBadScales", "There need to be two scales, yeah?");
			model.addAttribute("conversion", conversion);
			model.addAttribute("noScales", "Give us some scales, specifically two, and this time valid ones!");

			return "temperatureConversion";
		}

		if (validateScaleChoice(conversion.getScaleStart()) == false
				|| validateScaleChoice(conversion.getScaleEnd()) == false) {
			model.addAttribute("twoBadScales", "The scales need to be valid, yeah?");
			model.addAttribute("conversion", conversion);
			model.addAttribute("noScales", "Give us some scales, specifically two, and this time valid ones!");

			return "temperatureConversion";
		}

		this.sessionManager.setCurrentConversion(conversion);
		System.out.println("we did it!?");

		return "redirect:/temperatureConversion/mainPage";
	}

	@PostMapping("/startingTemperature")
	public String chooseStartingTemperature(@Validated Temperature tempStart, Errors errors, Model model) {

		try {
			tempStart.setNumber(Double.parseDouble(tempStart.getContent().toString()));
			System.out.println("no exception!?");
		}

		catch (NumberFormatException nfe) {
			model.addAttribute("badTemp", "We need an acceptable temperature to convert from, yeah?");

			return "temperatureConversion";
		}

		tempStart.setScale(sessionManager.getCurrentConversion().getScaleStart());
		this.sessionManager.getCurrentConversion().setTemperatureStart(tempStart);
		
		double temperatureEndPrimitive = Utility.determineOperation(this.sessionManager.getCurrentConversion().getTemperatureStart(), 
				this.sessionManager.getCurrentConversion().getScaleStart(), 
					this.sessionManager.getCurrentConversion().getScaleEnd());
		
		Temperature temperatureEnd = Utility.toScaleEnd(this.sessionManager.getCurrentConversion().getScaleEnd(), 
				temperatureEndPrimitive);
		
		temperatureEnd.setNumber(temperatureEndPrimitive);
		
		this.sessionManager.getCurrentConversion().setTemperatureEnd(temperatureEnd);

		return "redirect:/temperatureConversion/mainPage";
	}
	
	@PostMapping("/restart")
	public String restart() {
		this.sessionManager.setCurrentConversion(null);
		
		return "redirect:/temperatureConversion/mainPage";
	}

	public boolean validateScaleChoice(Scale scale) {
		for (Scale sc : Scale.values()) {
			if (sc.toString().equalsIgnoreCase(scale.toString())) {
				return true;
			}
		}
		return false;
	}
}