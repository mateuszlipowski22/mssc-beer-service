package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.BeerService;
import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPageList;
import guru.sfg.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER=0;
    private static final Integer DEFAULT_PAGE_SIZE=25;

    private final BeerService beerService;


    @GetMapping(produces = {"application/json"}, path = "beer")
    public ResponseEntity<BeerPageList> listBeers(@RequestParam(value="pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value="pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value="beerName", required = false) String beerName,
                                                  @RequestParam(value="beerStyle", required = false) BeerStyleEnum beerStyle,
                                                  @RequestParam(value="showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if(showInventoryOnHand == null){
            showInventoryOnHand=false;
        }

        if(pageNumber==null || pageNumber<0){
            pageNumber=DEFAULT_PAGE_NUMBER;
        }

        if(pageSize==null || pageSize<1){
            pageSize=DEFAULT_PAGE_SIZE;
        }

        BeerPageList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value="showInventoryOnHand", required = false) Boolean showInventoryOnHand) {
        if(showInventoryOnHand == null){
            showInventoryOnHand=false;
        }

        return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.saveBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

    @GetMapping("beerUPC/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {

        return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
    }
}

