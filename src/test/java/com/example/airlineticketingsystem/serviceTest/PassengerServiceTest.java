package com.example.airlineticketingsystem.serviceTest;

import com.bms.airlinesbookingsystem.dto.request.CreatePassengerRequest;
import com.bms.airlinesbookingsystem.dto.request.UpdatePassengerRequest;
import com.bms.airlinesbookingsystem.entitiy.Passenger;
import com.bms.airlinesbookingsystem.exception.PassengerNotFoundException;
import com.bms.airlinesbookingsystem.mapper.PassengerDtoMapper;
import com.bms.airlinesbookingsystem.repository.PassengerRepository;
import com.bms.airlinesbookingsystem.service.PassengerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PassengerDtoMapper converter;

    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePassenger() {
        CreatePassengerRequest request = new CreatePassengerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setAddress("123 Main St");
        request.setAge(30);
        request.setNationality("US");

        passengerService.createPassenger(request);

        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }

    @Test
    public void testUpdatePassenger() {
        UpdatePassengerRequest request = new UpdatePassengerRequest();
        request.setFirstName("Updated John");
        request.setLastName("Updated Doe");
        request.setAddress("456 Elm St");
        request.setAge(35);
        request.setNationality("UK");

        Passenger existingPassenger = new Passenger();
        when(passengerRepository.findById(anyString())).thenReturn(Optional.of(existingPassenger));

        passengerService.updatePassenger("passenger-id", request);

        verify(passengerRepository, times(1)).save(existingPassenger);
    }


    @Test
    public void testFindPassengerById() {
        Passenger existingPassenger = new Passenger();
        when(passengerRepository.findById(anyString())).thenReturn(Optional.of(existingPassenger));

        passengerService.findPassengerById("passenger-id");

        verify(converter, times(1)).convert(existingPassenger);
    }

    @Test
    public void testFindAllPassengers() {
        Passenger existingPassenger = new Passenger();
        List<Passenger> passengerList = Arrays.asList(existingPassenger);

        when(passengerRepository.findAll()).thenReturn(passengerList);

        passengerService.findAllPassengers();

        verify(converter, times(1)).convert(passengerList);
    }

    @Test
    public void testFindAllPassengers_EmptyList() {
        when(passengerRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(PassengerNotFoundException.class, () -> {
            passengerService.findAllPassengers();
        });
    }

    @Test
    public void testFindPassengerByPassengerId() {
        Passenger existingPassenger = new Passenger();
        when(passengerRepository.findById(anyString())).thenReturn(Optional.of(existingPassenger));

        passengerService.findPassengerByPassengerId("passenger-id");

        verify(passengerRepository, times(1)).findById("passenger-id");
    }

}
