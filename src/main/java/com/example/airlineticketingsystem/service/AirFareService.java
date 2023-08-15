package com.example.airlineticketingsystem.service;


import com.example.airlineticketingsystem.dto.request.CreateAirFareRequest;
import com.example.airlineticketingsystem.dto.request.UpdateAirFareRequest;
import com.example.airlineticketingsystem.dto.response.AirFareDto;
import com.example.airlineticketingsystem.entitiy.AirFare;
import com.example.airlineticketingsystem.exception.AirFareNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.AirFareDtoMapper;
import com.example.airlineticketingsystem.repository.AirFareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AirFareService {
    private final AirFareRepository airFareRepository;
    private final RouteService routeService;
    private final AirFareDtoMapper airFareDtoMapper;

    public AirFareService(AirFareRepository airFareRepository,
                          RouteService routeService,
                          AirFareDtoMapper airFareDtoMapper) {
        this.airFareRepository = airFareRepository;
        this.routeService = routeService;
        this.airFareDtoMapper = airFareDtoMapper;
    }

    public void createAirFare(CreateAirFareRequest request) {
        AirFare airFare = new AirFare();

        airFare.setFare(request.getFare());
        airFare.setRoute(routeService.findRouteByRouteId(request.getRouteId()));

        airFareRepository.save(airFare);
        log.info(BusinessLogMessage.AirFare.AIR_FARE_CREATED);
    }

    public void updateAirFare(final String id, UpdateAirFareRequest request) {
        AirFare airFare = findAirFareByAirFareId(id);

        airFare.setFare(request.getFare());
        airFare.setRoute(routeService.findRouteByRouteId(request.getRouteId()));

        airFareRepository.save(airFare);
        log.info(BusinessLogMessage.AirFare.AIR_FARE_UPDATED + id);
    }

    public void updateFare(final String id, final Double fare) {
        AirFare airFare = findAirFareByAirFareId(id);

        airFare.setFare(fare);

        airFareRepository.save(airFare);
        log.info(BusinessLogMessage.AirFare.AIR_FARE_UPDATED + id);
    }

    public void deleteAirFare(final String id) {
        airFareRepository.delete(findAirFareByAirFareId(id));
        log.info(BusinessLogMessage.AirFare.AIR_FARE_DELETED + id);
    }

    public AirFareDto findAirFareById(final String id) {
        AirFare airFare = findAirFareByAirFareId(id);

        log.info(BusinessLogMessage.AirFare.AIR_FARE_FOUND + id);
        return airFareDtoMapper.convert(airFare);
    }

    public List<AirFareDto> findAllAirFares() {
        List<AirFare> airFareList = airFareRepository.findAll();

        if (airFareList.isEmpty()) {
            log.info(BusinessLogMessage.AirFare.AIR_FARE_LIST_EMPTY);
            throw new AirFareNotFoundException(BusinessMessage.AirFare.AIRFARE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.AirFare.AIR_FARE_LISTED);
        return airFareDtoMapper.convert(airFareList);
    }

    private AirFare findAirFareByAirFareId(final String id) {
        return airFareRepository.findById(id)
                .orElseThrow(() -> {
                    log.info(BusinessLogMessage.AirFare.AIR_FARE_NOT_FOUND + id);
                    throw new AirFareNotFoundException(BusinessMessage.AirFare.AIRFARE_NOT_FOUND);
                });
    }
}

