package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    @Mapping(target = "name", source = "beer.beerName")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(target = "name", source = "beer.beerName")
    BeerDto beerToBeerDtoWithInventory(Beer beer);

    @Mapping(target = "beerName", source = "name")
    Beer beerDtoToBeer(BeerDto beerDto);

}
