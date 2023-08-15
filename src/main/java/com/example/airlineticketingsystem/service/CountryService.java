package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreateCountryRequest;
import com.example.airlineticketingsystem.dto.request.UpdateCountryRequest;
import com.example.airlineticketingsystem.dto.response.CountryDto;
import com.example.airlineticketingsystem.entitiy.Country;
import com.example.airlineticketingsystem.exception.CountryAlreadyExistException;
import com.example.airlineticketingsystem.exception.CountryNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.CountryDtoMapper;
import com.example.airlineticketingsystem.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryDtoMapper converter;

    public CountryService(CountryRepository countryRepository,
                          CountryDtoMapper converter) {
        this.countryRepository = countryRepository;
        this.converter = converter;
    }

    public void createCountry(CreateCountryRequest request) {
        checkIfCountryExists(request.getName());

        Country country = new Country();

        country.setName(request.getName());

        countryRepository.save(country);
        log.info(BusinessLogMessage.Country.COUNTRY_CREATED);
    }

    public void updateCountry(final String id, UpdateCountryRequest request) {
        Country country = findCountryByCountryId(id);

        country.setName(request.getName());

        countryRepository.save(country);
        log.info(BusinessLogMessage.Country.COUNTRY_UPDATED + id);
    }

    public void deleteCountry(final String id) {
        countryRepository.delete(findCountryByCountryId(id));
        log.info(BusinessLogMessage.Country.COUNTRY_DELETED + id);
    }

    public CountryDto findCountryById(final String id) {
        Country country = findCountryByCountryId(id);

        log.info(BusinessLogMessage.Country.COUNTRY_FOUND + id);
        return converter.convert(country);
    }

    public List<CountryDto> findAllCountries() {
        List<Country> countries = countryRepository.findAll();

        if (countries.isEmpty()) {
            log.info(BusinessLogMessage.Country.COUNTRY_LIST_EMPTY);
            throw new CountryNotFoundException(BusinessMessage.Country.COUNTRY_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Country.COUNTRY_LISTED);
        return converter.convert(countries);
    }

    private void checkIfCountryExists(String name) {
        if (countryRepository.existsByNameIgnoreCase(name)) {
            log.error(BusinessLogMessage.Country.COUNTRY_ALREADY_EXISTS + name);
            throw new CountryAlreadyExistException(BusinessMessage.Country.COUNTRY_ALREADY_EXIST);
        }
    }

    protected Country findCountryByCountryId(final String id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Country.COUNTRY_NOT_FOUND + id);
                    return new CountryNotFoundException(BusinessMessage.Country.COUNTRY_NOT_FOUND);
                });
    }
}
