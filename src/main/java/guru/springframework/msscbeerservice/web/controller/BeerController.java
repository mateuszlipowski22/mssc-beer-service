package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@AllArgsConstructor
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerMapper.beerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<HttpStatus> updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

