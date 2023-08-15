package com.example.airlineticketingsystem.serviceTest;


import com.bms.airlinesbookingsystem.dto.request.CreateRouteRequest;
import com.bms.airlinesbookingsystem.dto.request.UpdateRouteRequest;
import com.bms.airlinesbookingsystem.entitiy.Route;
import com.bms.airlinesbookingsystem.exception.RouteNotFoundException;
import com.bms.airlinesbookingsystem.mapper.RouteDtoMapper;
import com.bms.airlinesbookingsystem.repository.RouteRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private RouteDtoMapper converter;

    @InjectMocks
    private RouteService routeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRoute() {
        CreateRouteRequest request = new CreateRouteRequest();
        request.setAirport("Airport A");
        request.setDestination("Destination B");

        routeService.createRoute(request);

        verify(routeRepository, times(1)).save(any(Route.class));
    }

    @Test
    public void testUpdateRoute() {
        UpdateRouteRequest request = new UpdateRouteRequest();
        request.setAirport("Updated Airport A");
        request.setDestination("Updated Destination B");

        Route existingRoute = new Route();
        when(routeRepository.findById(anyString())).thenReturn(Optional.of(existingRoute));

        routeService.updateRoute("route-id", request);

        verify(routeRepository, times(1)).save(existingRoute);
    }

    // Similar tests for other methods can be added here

    @Test
    public void testFindRouteById() {
        Route existingRoute = new Route();
        when(routeRepository.findById(anyString())).thenReturn(Optional.of(existingRoute));

        routeService.findRouteById("route-id");

        verify(converter, times(1)).convert(existingRoute);
    }

    @Test
    public void testFindAllRoutes() {
        Route existingRoute = new Route();
        List<Route> routeList = Arrays.asList(existingRoute);

        when(routeRepository.findAll()).thenReturn(routeList);

        routeService.findAllRoutes();

        verify(converter, times(1)).convert(routeList);
    }

    @Test
    public void testFindAllRoutes_EmptyList() {
        when(routeRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(RouteNotFoundException.class, () -> {
            routeService.findAllRoutes();
        });
    }

    @Test
    public void testFindRouteByRouteId() {
        Route existingRoute = new Route();
        when(routeRepository.findById(anyString())).thenReturn(Optional.of(existingRoute));

        routeService.findRouteByRouteId("route-id");

        verify(routeRepository, times(1)).findById("route-id");
    }

}

