package com.example.airlineticketingsystem.serviceTest;
import com.example.airlineticketingsystem.dto.request.CreateAirCraftRequest;
import com.example.airlineticketingsystem.dto.request.UpdateAirCraftRequest;
import com.example.airlineticketingsystem.dto.response.AirCraftDto;
import com.example.airlineticketingsystem.entitiy.AirCraft;
import com.example.airlineticketingsystem.entitiy.FlightSchedule;
import com.example.airlineticketingsystem.mapper.AirCraftDtoMapper;
import com.example.airlineticketingsystem.repository.AirCraftRepository;
import com.example.airlineticketingsystem.service.AirCraftService;
import com.example.airlineticketingsystem.service.FlightScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
@SpringBootTest
public class AirCraftServiceTest {

    @Mock
    private AirCraftRepository airCraftRepository;

    @Mock
    private FlightScheduleService flightScheduleService;

    @Mock
    private AirCraftDtoMapper airCraftDtoMapper;

    @InjectMocks
    private AirCraftService airCraftService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAirCraft() {
        CreateAirCraftRequest request = new CreateAirCraftRequest();
        AirCraft fakeAirCraft = new AirCraft();

        FlightSchedule fakeFlightSchedule = new FlightSchedule();

        when(flightScheduleService.findFLightScheduleByFlightScheduleId(any())).thenReturn(fakeFlightSchedule);
        when(airCraftRepository.save(any(AirCraft.class))).thenReturn(fakeAirCraft);

        airCraftService.createAirCraft(request);

    }

    @Test
    public void testUpdateAirCraft() {
        String airCraftId = "fakeAirCraftId";
        UpdateAirCraftRequest request = new UpdateAirCraftRequest();

        AirCraft fakeAirCraft = new AirCraft();

        FlightSchedule fakeFlightSchedule = new FlightSchedule();

        when(airCraftRepository.findById(any())).thenReturn(Optional.of(fakeAirCraft));
        when(flightScheduleService.findFLightScheduleByFlightScheduleId(any())).thenReturn(fakeFlightSchedule);

        airCraftService.updateAirCraft(airCraftId, request);

    }


    @Test
    public void testFindAirCraftById() {
        String airCraftId = "fakeAirCraftId";
        AirCraft fakeAirCraft = new AirCraft();

        FlightSchedule fakeFlightSchedule = new FlightSchedule();

        when(airCraftRepository.findById(any())).thenReturn(Optional.of(fakeAirCraft));
        OngoingStubbing<AirCraftDto> airCraftDtoOngoingStubbing;
       // airCraftDtoOngoingStubbing = when(airCraftDtoMapper.convert(any(AirCraft.class))).thenReturn(fakeFlightSchedule);


        airCraftService.findAirCraftById(airCraftId);

    }
}
