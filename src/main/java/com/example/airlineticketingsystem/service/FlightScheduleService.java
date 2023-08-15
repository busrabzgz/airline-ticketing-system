package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreateFlightScheduleRequest;
import com.example.airlineticketingsystem.dto.request.UpdateFlightScheduleRequest;
import com.example.airlineticketingsystem.dto.response.FlightScheduleDto;
import com.example.airlineticketingsystem.entitiy.FlightSchedule;
import com.example.airlineticketingsystem.exception.FlightScheduleNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.FlightScheduleDtoMapper;
import com.example.airlineticketingsystem.repository.FlightScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class FlightScheduleService {
    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightScheduleDtoMapper converter;

    public FlightScheduleService(FlightScheduleRepository flightScheduleRepository,
                                 FlightScheduleDtoMapper converter) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.converter = converter;
    }

    public void createFlightSchedule(CreateFlightScheduleRequest request) {
        FlightSchedule flightSchedule = new FlightSchedule();

        flightSchedule.setFlightDate(request.getFlightDate());
        flightSchedule.setDeparture(request.getDeparture());
        flightSchedule.setArrival(request.getArrival());

        flightScheduleRepository.save(flightSchedule);
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_CREATED);
    }

    public void updateFlightSchedule(final String id, UpdateFlightScheduleRequest request) {
        FlightSchedule flightSchedule = findFLightScheduleByFlightScheduleId(id);

        flightSchedule.setFlightDate(request.getFlightDate());
        flightSchedule.setDeparture(request.getDeparture());
        flightSchedule.setArrival(request.getArrival());

        flightScheduleRepository.save(flightSchedule);
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_UPDATED + id);
    }

    public void updateFlightDate(final String id, final LocalDate flightDate) {
        FlightSchedule flightSchedule = findFLightScheduleByFlightScheduleId(id);

        flightSchedule.setFlightDate(flightDate);

        flightScheduleRepository.save(flightSchedule);
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_UPDATED + id);
    }

    public void updateDeparture(final String id, final LocalDate departure) {
        FlightSchedule flightSchedule = findFLightScheduleByFlightScheduleId(id);

        flightSchedule.setDeparture(departure);

        flightScheduleRepository.save(flightSchedule);
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_UPDATED + id);
    }

    public void updateArrival(final String id, final LocalDate arrival) {
        FlightSchedule flightSchedule = findFLightScheduleByFlightScheduleId(id);

        flightSchedule.setArrival(arrival);

        flightScheduleRepository.save(flightSchedule);
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_UPDATED + id);
    }

    public void deleteFlightSchedule(final String id) {
        flightScheduleRepository.delete(findFLightScheduleByFlightScheduleId(id));
        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_DELETED + id);
    }

    public FlightScheduleDto findFlightScheduleById(final String id) {
        FlightSchedule flightSchedule = findFLightScheduleByFlightScheduleId(id);

        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_FOUND + id);
        return converter.convert(flightSchedule);
    }

    public List<FlightScheduleDto> findAllFlightSchedules() {
        List<FlightSchedule> flightScheduleList = flightScheduleRepository.findAll();

        if (flightScheduleList.isEmpty()) {
            log.error(BusinessMessage.FlightSchedule.FLIGHT_SCHEDULE_LIST_EMPTY);
            throw new FlightScheduleNotFoundException(BusinessMessage.FlightSchedule.FLIGHT_SCHEDULE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_LISTED);
        return converter.convert(flightScheduleList);
    }

    public FlightSchedule findFLightScheduleByFlightScheduleId(final String id) {
        return flightScheduleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.FlightSchedule.FLIGHT_SCHEDULE_NOT_FOUND + id);
                    throw new FlightScheduleNotFoundException(BusinessMessage.FlightSchedule.FLIGHT_SCHEDULE_NOT_FOUND);
                });
    }
}
