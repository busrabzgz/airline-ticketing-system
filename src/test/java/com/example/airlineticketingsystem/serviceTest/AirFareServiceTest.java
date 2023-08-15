package com.example.airlineticketingsystem.serviceTest;

import com.bms.airlinesbookingsystem.entitiy.AirFare;
import com.bms.airlinesbookingsystem.exception.AirFareNotFoundException;
import com.bms.airlinesbookingsystem.mapper.AirFareDtoMapper;
import com.bms.airlinesbookingsystem.repository.AirFareRepository;
import com.bms.airlinesbookingsystem.service.AirFareService;
import com.bms.airlinesbookingsystem.service.RouteService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AirFareServiceTest {

    @Mock
    private AirFareRepository airFareRepository;

    @Mock
    private RouteService routeService;

    @Mock
    private AirFareDtoMapper airFareDtoMapper;

    @InjectMocks
    private AirFareService airFareService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }





    @Test
    public void testDeleteAirFare() {
        AirFare existingAirFare = new AirFare();
        when(airFareRepository.findById(anyString())).thenReturn(Optional.of(existingAirFare));

        airFareService.deleteAirFare("airfare-id");

        verify(airFareRepository, times(1)).delete(existingAirFare);
    }

    // Similar tests for other methods can be added here

    @Test
    public void testFindAirFareById() {
        AirFare existingAirFare = new AirFare();
        when(airFareRepository.findById(anyString())).thenReturn(Optional.of(existingAirFare));

        airFareService.findAirFareById("airfare-id");

        verify(airFareDtoMapper, times(1)).convert(existingAirFare);
    }

    @Test
    public void testFindAllAirFares() {
        AirFare existingAirFare = new AirFare();
        List<AirFare> airFareList = Arrays.asList(existingAirFare);

        when(airFareRepository.findAll()).thenReturn(airFareList);

        airFareService.findAllAirFares();

        verify(airFareDtoMapper, times(1)).convert(airFareList);
    }

    @Test
    public void testFindAllAirFares_EmptyList() {
        when(airFareRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(AirFareNotFoundException.class, () -> {
            airFareService.findAllAirFares();
        });
    }
}

