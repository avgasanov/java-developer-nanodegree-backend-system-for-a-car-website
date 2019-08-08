package com.udacity.pricing.domain.price;

import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceDescription = @Description("vehicleApi"))
public interface PriceRepository extends CrudRepository<Price,Long> {

    default List<Price> findAll() {
        return PricingService.getAllPrices();
    }

    default Optional<Price> findById(Long id) {
        Price price;
        try {
            price = PricingService.getPrice(id);
            return Optional.of(price);
        } catch (PriceException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
