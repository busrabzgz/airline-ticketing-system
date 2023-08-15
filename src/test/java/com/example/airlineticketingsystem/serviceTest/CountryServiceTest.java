package com.example.airlineticketingsystem.serviceTest;


import com.bms.airlinesbookingsystem.dto.request.CreateCountryRequest;
import com.bms.airlinesbookingsystem.dto.request.UpdateCountryRequest;
import com.bms.airlinesbookingsystem.entitiy.Country;
import com.bms.airlinesbookingsystem.exception.CountryAlreadyExistException;
import com.bms.airlinesbookingsystem.exception.CountryNotFoundException;
import com.bms.airlinesbookingsystem.mapper.CountryDtoMapper;
import com.bms.airlinesbookingsystem.repository.CountryRepository;
import com.bms.airlinesbookingsystem.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryDtoMapper converter;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCountry() {
        CreateCountryRequest request = new CreateCountryRequest();
        request.setName("Test Country");

        when(countryRepository.existsByNameIgnoreCase(eq("Test Country"))).thenReturn(false);

        countryService.createCountry(request);

        verify(countryRepository, times(1)).save(any(Country.class));
    }

    @Test
    public void testCreateCountry_AlreadyExists() {
        CreateCountryRequest request = new CreateCountryRequest();
        request.setName("Existing Country");

        when(countryRepository.existsByNameIgnoreCase(eq("Existing Country"))).thenReturn(true);

        assertThrows(CountryAlreadyExistException.class, () -> {
            countryService.createCountry(request);
        });
    }

    @Test
    public void testUpdateCountry() {
        UpdateCountryRequest request = new UpdateCountryRequest();
        request.setName("Updated Country");

        Country existingCountry = new Country();
        when(countryRepository.findById(anyString())).thenReturn(Optional.of(existingCountry));

        countryService.updateCountry("country-id", request);

        verify(countryRepository, times(1)).save(existingCountry);
    }


    @Test
    public void testFindCountryById() {
        Country existingCountry = new Country();
        when(countryRepository.findById(anyString())).thenReturn(Optional.of(existingCountry));

        countryService.findCountryById("country-id");

        verify(converter, times(1)).convert(existingCountry);
    }

    @Test
    public void testFindAllCountries() {
        Country existingCountry = new Country();
        List<Country> countryList = Arrays.asList(existingCountry);

        when(countryRepository.findAll()).thenReturn(countryList);

        countryService.findAllCountries();

        verify(converter, times(1)).convert(countryList);
    }

    @Test
    public void testFindAllCountries_EmptyList() {
        when(countryRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(CountryNotFoundException.class, () -> {
            countryService.findAllCountries();
        });
    }
}
