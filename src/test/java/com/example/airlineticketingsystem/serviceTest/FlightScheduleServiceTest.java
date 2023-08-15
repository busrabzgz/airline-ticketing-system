package com.example.airlineticketingsystem.serviceTest;


import com.bms.airlinesbookingsystem.dto.request.CreateFlightScheduleRequest;
import com.bms.airlinesbookingsystem.dto.request.UpdateFlightScheduleRequest;
import com.bms.airlinesbookingsystem.entitiy.FlightSchedule;
import com.bms.airlinesbookingsystem.exception.FlightScheduleNotFoundException;
import com.bms.airlinesbookingsystem.mapper.FlightScheduleDtoMapper;
import com.bms.airlinesbookingsystem.repository.FlightScheduleRepository;
import com.bms.airlinesbookingsystem.service.FlightScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FlightScheduleServiceTest {

    @Mock
    private FlightScheduleRepository flightScheduleRepository;

    @Mock
    private FlightScheduleDtoMapper converter;

    @InjectMocks
    private FlightScheduleService flightScheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFlightSchedule() {
        CreateFlightScheduleRequest request = new CreateFlightScheduleRequest();
        request.setFlightDate(LocalDate.now());
        request.setDeparture(LocalDate.parse("Departure"));
        request.setArrival(LocalDate.parse("Arrival"));

        flightScheduleService.createFlightSchedule(request);

        verify(flightScheduleRepository, times(1)).save(any(FlightSchedule.class));
    }

    @Test
    public void testUpdateFlightSchedule() {
        UpdateFlightScheduleRequest request = new UpdateFlightScheduleRequest();
        request.setFlightDate(LocalDate.now());
        request.setDeparture(LocalDate.parse("Updated Departure"));
        request.setArrival(LocalDate.parse("Updated Arrival"));

        FlightSchedule existingFlightSchedule = new FlightSchedule();
        when(flightScheduleRepository.findById(anyString())).thenReturn(Optional.of(existingFlightSchedule));

        flightScheduleService.updateFlightSchedule("flight-schedule-id", request);

        verify(flightScheduleRepository, times(1)).save(existingFlightSchedule);
    }

    // Similar tests for other methods can be added here

    @Test
    public void testFindFlightScheduleById() {
        FlightSchedule existingFlightSchedule = new FlightSchedule();
        when(flightScheduleRepository.findById(anyString())).thenReturn(Optional.of(existingFlightSchedule));

        flightScheduleService.findFlightScheduleById("flight-schedule-id");

        verify(converter, times(1)).convert(existingFlightSchedule);
    }

    @Test
    public void testFindAllFlightSchedules() {
        FlightSchedule existingFlightSchedule = new FlightSchedule();
        List<FlightSchedule> flightScheduleList = Arrays.asList(existingFlightSchedule);

        when(flightScheduleRepository.findAll()).thenReturn(flightScheduleList);

        flightScheduleService.findAllFlightSchedules();

        verify(converter, times(1)).convert(flightScheduleList);
    }

    @Test
    public void testFindAllFlightSchedules_EmptyList() {
        when(flightScheduleRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(FlightScheduleNotFoundException.class, () -> {
            flightScheduleService.findAllFlightSchedules();
        });
    }
}

