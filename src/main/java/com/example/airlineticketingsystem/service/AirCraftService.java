package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreateAirCraftRequest;
import com.example.airlineticketingsystem.dto.request.UpdateAirCraftRequest;
import com.example.airlineticketingsystem.dto.response.AirCraftDto;
import com.example.airlineticketingsystem.entitiy.AirCraft;
import com.example.airlineticketingsystem.exception.AirCraftNotFoundException;
import com.example.airlineticketingsystem.helper.Generator;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.AirCraftDtoMapper;
import com.example.airlineticketingsystem.repository.AirCraftRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AirCraftService {
    private final AirCraftRepository airCraftRepository;
    private final FlightScheduleService flightScheduleService;
    private final AirCraftDtoMapper airCraftDtoMapper;

    public AirCraftService(AirCraftRepository airCraftRepository,
                           FlightScheduleService flightScheduleService,
                           AirCraftDtoMapper airCraftDtoMapper) {
        this.airCraftRepository = airCraftRepository;
        this.flightScheduleService = flightScheduleService;
        this.airCraftDtoMapper = airCraftDtoMapper;
    }

    public void createAirCraft(CreateAirCraftRequest request) {
        AirCraft airCraft = new AirCraft();

        airCraft.setNumber(Generator.generateAirCraftNumber());
        airCraft.setCapacity(request.getCapacity());
        airCraft.setManufacturer(request.getManufacturer());
        airCraft.setManufactureDate(request.getManufactureDate());
        airCraft.setFlightSchedule(flightScheduleService
                .findFLightScheduleByFlightScheduleId(request.getFlightScheduleId()));

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_CREATED);
    }

    public void updateAirCraft(final String id, UpdateAirCraftRequest request) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        airCraft.setCapacity(request.getCapacity());
        airCraft.setManufacturer(request.getManufacturer());
        airCraft.setManufactureDate(request.getManufactureDate());
        airCraft.setFlightSchedule(flightScheduleService
                .findFLightScheduleByFlightScheduleId(request.getFlightScheduleId()));

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_UPDATED + id);
    }

    public void updateCapacity(final String id, final Integer capacity) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        airCraft.setCapacity(capacity);

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_UPDATED + id);
    }

    public void updateManufacturer(final String id, final String manufacturer) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        airCraft.setManufacturer(manufacturer);

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_UPDATED + id);
    }

    public void updateManufactureDate(final String id, final LocalDate manufactureDate) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        airCraft.setManufactureDate(manufactureDate);

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_UPDATED + id);
    }

    public void updateFlightSchedule(final String id, final String flightScheduleId) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        airCraft.setFlightSchedule(flightScheduleService
                .findFLightScheduleByFlightScheduleId(flightScheduleId));

        airCraftRepository.save(airCraft);
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_UPDATED + id);
    }

    public void deleteAirCraft(final String id) {
        airCraftRepository.delete(findAirCraftByAirCraftId(id));
        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_DELETED + id);
    }

    public AirCraftDto findAirCraftById(final String id) {
        AirCraft airCraft = findAirCraftByAirCraftId(id);

        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_FOUND + id);
        return airCraftDtoMapper.convert(airCraft);
    }

    public AirCraftDto findAirCraftByNumber(final String number) {
        AirCraft airCraft = airCraftRepository.findByNumber(number);

        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_FOUND + number);
        return airCraftDtoMapper.convert(airCraft);
    }

    public List<AirCraftDto> findAllAirCrafts() {
        List<AirCraft> airCraftList = airCraftRepository.findAll();

        if (airCraftList.isEmpty()) {
            log.error(BusinessMessage.AirCraft.AIRCRAFT_LIST_EMPTY);
            throw new AirCraftNotFoundException(BusinessMessage.AirCraft.AIRCRAFT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.AirCraft.AIR_CRAFT_LISTED);
        return airCraftDtoMapper.convert(airCraftList);
    }

    private AirCraft findAirCraftByAirCraftId(final String id) {
        return airCraftRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.AirCraft.AIRCRAFT_NOT_FOUND + id);
                    throw new AirCraftNotFoundException(BusinessMessage.AirCraft.AIRCRAFT_NOT_FOUND);
                });
    }

}
