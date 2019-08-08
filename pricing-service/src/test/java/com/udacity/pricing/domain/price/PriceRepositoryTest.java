package com.udacity.pricing.domain.price;

import com.udacity.pricing.PricingServiceApplication;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@SpringBootTest
@PrepareForTest({PricingService.class})
@PowerMockIgnore({"javax.*.*", "com.sun.*", "org.xml.*"})
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Before
    public void setUp() throws PriceException {
        PowerMockito.mockStatic(PricingService.class);
        PowerMockito.when(PricingService.getPrice(12L))
                .thenReturn(new Price("USD", BigDecimal.ONE, 12L));
        PowerMockito.when(PricingService.getPrice(13L))
                .thenThrow(new PriceException("price for id is not found"));
    }

    @Test
    public void verify_getPriceForExistingId() throws PriceException {
        Price price = priceRepository.findById(12L).orElse(null);
        assertEquals(price, new Price("USD", BigDecimal.ONE, 12L));
    }

    @Test
    public void verify_getPriceForNonExistentId() {
        assertTrue(priceRepository.findById(13L).isEmpty());
    }
}
