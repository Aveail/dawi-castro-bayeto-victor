package pe.edu.i202221776.dto;


public record FilmTosaveDTO(
		Integer filmId,
		String title,
		String description,
		Integer realese,
		Integer rentalDuration,
		Double rentalRate,
		Integer length,
		Double replacementCost,					
		String rating,
		Integer languageId
		) {


}
