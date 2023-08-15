package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreatePassengerRequest;
import com.example.airlineticketingsystem.dto.request.UpdatePassengerRequest;
import com.example.airlineticketingsystem.dto.response.PassengerDto;
import com.example.airlineticketingsystem.entitiy.Passenger;
import com.example.airlineticketingsystem.exception.PassengerNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.PassengerDtoMapper;
import com.example.airlineticketingsystem.repository.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerDtoMapper converter;



    public void createPassenger(CreatePassengerRequest request) {
        Passenger passenger = new Passenger();

        passenger.setFirstName(request.getFirstName());
        passenger.setLastName(request.getLastName());
        passenger.setAddress(request.getAddress());
        passenger.setAge(request.getAge());
        passenger.setNationality(request.getNationality());

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_CREATED);
    }

    public void updatePassenger(final String id, UpdatePassengerRequest request) {
        Passenger passenger = findPassengerByPassengerId(id);

        if (!passenger.getFirstName().equals(request.getFirstName())) {
            checkIfPassengerExistsByFirstName(request.getFirstName());
            passenger.setFirstName(request.getFirstName());
        }

        if (!passenger.getLastName().equals(request.getLastName())) {
            checkIfPassengerExistsByLastName(request.getLastName());
            passenger.setLastName(request.getLastName());
        }

        passenger.setAddress(request.getAddress());
        passenger.setAge(request.getAge());
        passenger.setNationality(request.getNationality());

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void updateFirstName(final String id, final String firstName) {
        Passenger passenger = findPassengerByPassengerId(id);

        if (!passenger.getFirstName().equals(firstName)) {
            checkIfPassengerExistsByFirstName(firstName);
            passenger.setFirstName(firstName);
        }

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void updateLastName(final String id, final String lastName) {
        Passenger passenger = findPassengerByPassengerId(id);

        if (!passenger.getLastName().equals(lastName)) {
            checkIfPassengerExistsByLastName(lastName);
            passenger.setLastName(lastName);
        }

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void updateAddress(final String id, final String address) {
        Passenger passenger = findPassengerByPassengerId(id);

        passenger.setAddress(address);

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void updateAge(final String id, final Integer age) {
        Passenger passenger = findPassengerByPassengerId(id);

        passenger.setAge(age);

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void updateNationality(final String id, final String nationality) {
        Passenger passenger = findPassengerByPassengerId(id);

        passenger.setNationality(nationality);

        passengerRepository.save(passenger);
        log.info(BusinessLogMessage.Passenger.PASSENGER_UPDATED + id);
    }

    public void deletePassenger(final String id) {
        passengerRepository.delete(findPassengerByPassengerId(id));
        log.info(BusinessLogMessage.Passenger.PASSENGER_DELETED + id);
    }

    public PassengerDto findPassengerById(final String id) {
        Passenger passenger = findPassengerByPassengerId(id);

        log.info(BusinessLogMessage.Passenger.PASSENGER_FOUND + id);
        return converter.convert(passenger);
    }

    public List<PassengerDto> findAllPassengers() {
        List<Passenger> passengerList = passengerRepository.findAll();

        if (passengerList.isEmpty()) {
            log.error(BusinessLogMessage.Passenger.PASSENGER_LIST_EMPTY);
            throw new PassengerNotFoundException(BusinessMessage.Passenger.PASSENGER_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Passenger.PASSENGER_LISTED);
        return converter.convert(passengerList);
    }

    public Passenger findPassengerByPassengerId(final String id) {
        return passengerRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.Passenger.PASSENGER_NOT_FOUND + id);
            throw new PassengerNotFoundException(BusinessMessage.Passenger.PASSENGER_NOT_FOUND);
        });
    }

    private void checkIfPassengerExistsByFirstName(final String firstName) {
        if (passengerRepository.existsByFirstNameIgnoreCase(firstName)) {
            log.error(BusinessLogMessage.Passenger.PASSENGER_ALREADY_EXISTS + firstName);
            throw new PassengerNotFoundException(BusinessMessage.Passenger.PASSENGER_ALREADY_EXIST);
        }
    }

    private void checkIfPassengerExistsByLastName(final String lastName) {
        if (passengerRepository.existsByLastNameIgnoreCase(lastName)) {
            log.error(BusinessLogMessage.Passenger.PASSENGER_ALREADY_EXISTS + lastName);
            throw new PassengerNotFoundException(BusinessMessage.Passenger.PASSENGER_ALREADY_EXIST);
        }
    }
    @Autowired
    public PassengerService(PassengerRepository passengerRepository, PassengerDtoMapper converter) {
        this.passengerRepository = passengerRepository;
        this.converter = converter;
    }

    public Optional<Passenger> getPassengerById(String passengerId) {
        return passengerRepository.findById(passengerId);
    }


}

