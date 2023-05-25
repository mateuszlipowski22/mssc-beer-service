package guru.sfg.brewery.model.events;

import guru.sfg.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 4420602766144722137L;
    private BeerDto beerDto;
}
