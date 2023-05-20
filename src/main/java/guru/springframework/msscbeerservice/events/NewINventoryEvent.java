package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public class NewINventoryEvent extends BeerEvent{
    public NewINventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
