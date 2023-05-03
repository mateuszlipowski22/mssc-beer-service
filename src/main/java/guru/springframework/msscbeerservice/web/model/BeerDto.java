package guru.springframework.msscbeerservice.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    private LocalDateTime createdDate;

    @Null
    private LocalDateTime lastModifiedDate;

    @NotBlank
    private String name;

    @NotNull
    private BeerStyleEnum beerStyle;

    @NotNull
    @Positive
    private Long upc;

    @NotNull
    @Positive
    private BigDecimal price;

    private Integer quantityOnHand;


}
