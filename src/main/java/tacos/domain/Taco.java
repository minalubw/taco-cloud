package tacos.domain;

import java.util.Date;
import java.util.List;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Taco {
	@NotNull
	@Size(min=5, message="name must be atleast 5 chars long")
	private String name;
	
	@Size(min=1, message="You must select at least one ingredient")
	private List<String> ingredients;
	
	private Long id;
	private Date createdAt;
}
