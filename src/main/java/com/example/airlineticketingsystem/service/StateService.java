package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreateStateRequest;
import com.example.airlineticketingsystem.dto.request.UpdateStateRequest;
import com.example.airlineticketingsystem.dto.response.StateDto;
import com.example.airlineticketingsystem.entitiy.State;
import com.example.airlineticketingsystem.exception.StateNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.helper.message.BusinessMessage;
import com.example.airlineticketingsystem.mapper.StateDtoMapper;
import com.example.airlineticketingsystem.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StateService {
    private final StateRepository stateRepository;
    private final CountryService countryService;
    private final StateDtoMapper converter;

    public StateService(StateRepository stateRepository,
                        CountryService countryService,
                        StateDtoMapper converter) {
        this.stateRepository = stateRepository;
        this.countryService = countryService;
        this.converter = converter;
    }

    public void createState(CreateStateRequest request) {
        State state = new State();

        state.setName(request.getName());
        state.setCountry(countryService.findCountryByCountryId(request.getCountryId()));

        stateRepository.save(state);
        log.info(BusinessLogMessage.State.STATE_CREATED);
    }

    public void updateState(final String id, UpdateStateRequest request) {
        State state = findStateByStateId(id);

        state.setName(request.getName());
        state.setCountry(countryService.findCountryByCountryId(request.getCountryId()));

        stateRepository.save(state);
        log.info(BusinessLogMessage.State.STATE_UPDATED + id);
    }

    public void updateName(final String id, final String name) {
        State state = findStateByStateId(id);

        state.setName(name);

        stateRepository.save(state);
        log.info(BusinessLogMessage.State.STATE_UPDATED + id);
    }

    public void updateCountry(final String id, final String countryId) {
        State state = findStateByStateId(id);

        state.setCountry(countryService.findCountryByCountryId(countryId));

        stateRepository.save(state);
        log.info(BusinessLogMessage.State.STATE_UPDATED + id);
    }

    public void deleteState(final String id) {
        stateRepository.delete(findStateByStateId(id));
        log.info(BusinessLogMessage.State.STATE_DELETED + id);
    }

    public StateDto findStateById(final String id) {
        State state = findStateByStateId(id);

        log.info(BusinessLogMessage.State.STATE_FOUND + id);
        return converter.convert(state);
    }

    public List<StateDto> findAllStates() {
        List<State> stateList = stateRepository.findAll();

        if (stateList.isEmpty()) {
            log.error(BusinessLogMessage.State.STATE_LIST_EMPTY);
            throw new StateNotFoundException(BusinessMessage.State.STATE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.State.STATE_LISTED);
        return converter.convert(stateList);
    }

    public State findStateByStateId(final String id) {
        return stateRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.State.STATE_NOT_FOUND + id);
            throw new StateNotFoundException(BusinessMessage.State.STATE_NOT_FOUND);
        });
    }
}
