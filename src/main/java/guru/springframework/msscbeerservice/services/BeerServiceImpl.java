package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPageList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {

        BeerPageList beerPageList;
        Page<Beer> beerPage;

//        if(StringUtils.hasLength(beerName) && StringUtils.hasLength(String.valueOf(beerStyle))){
//            beerPage=beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
//        }else if(StringUtils.hasLength(beerName) && !StringUtils.hasLength(String.valueOf(beerStyle))){
//            beerPage=beerRepository.findAllByBeerName(beerName, pageRequest);
//        }else if(!StringUtils.hasLength(beerName) && StringUtils.hasLength(String.valueOf(beerStyle))){
//            beerPage=beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
//        }else {
//            beerPage=beerRepository.findAll(pageRequest);
//        }

        beerPage=beerRepository.findAll(pageRequest);

        beerPageList = new BeerPageList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest.of(
                        beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPageList;
    }

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

            beer.setBeerName(beerDto.getName());
            beer.setBeerStyle(beerDto.getBeerStyle());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
