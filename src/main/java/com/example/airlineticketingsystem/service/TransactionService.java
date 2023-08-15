package com.example.airlineticketingsystem.service;

import com.example.airlineticketingsystem.dto.request.CreateTransactionRequest;
import com.example.airlineticketingsystem.dto.response.TransactionDto;
import com.example.airlineticketingsystem.entitiy.Transaction;
import com.example.airlineticketingsystem.exception.TransactionalNotFoundException;
import com.example.airlineticketingsystem.helper.message.BusinessLogMessage;
import com.example.airlineticketingsystem.mapper.TransactionDtoMapper;
import com.example.airlineticketingsystem.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final PassengerService passengerService;
    private final FlightScheduleService flightScheduleService;
    private final TransactionDtoMapper converter;

    public TransactionService(TransactionRepository transactionRepository,
                              PassengerService passengerService,
                              FlightScheduleService flightScheduleService,
                              TransactionDtoMapper converter) {
        this.transactionRepository = transactionRepository;
        this.passengerService = passengerService;
        this.flightScheduleService = flightScheduleService;
        this.converter = converter;
    }

    public void createTransaction(CreateTransactionRequest request) {
        Transaction transaction = new Transaction();

        transaction.setBookingDate(request.getBookingDate());
        transaction.setDepartureDate(request.getDepartureDate());
        transaction.setPassenger(passengerService.findPassengerByPassengerId(request.getPassengerId()));
        transaction.setFlightSchedule(flightScheduleService
                .findFLightScheduleByFlightScheduleId(request.getFlightScheduleId()));
        transaction.setType(request.getType());

        transactionRepository.save(transaction);
        log.info(BusinessLogMessage.Transaction.TRANSACTION_CREATED);
    }

    public void updateTransaction(final String id, CreateTransactionRequest request) {
        Transaction transaction = findTransactionByTransactionId(id);

        transaction.setBookingDate(request.getBookingDate());
        transaction.setDepartureDate(request.getDepartureDate());
        transaction.setPassenger(passengerService.findPassengerByPassengerId(request.getPassengerId()));
        transaction.setFlightSchedule(flightScheduleService
                .findFLightScheduleByFlightScheduleId(request.getFlightScheduleId()));
        transaction.setType(request.getType());

        transactionRepository.save(transaction);
        log.info(BusinessLogMessage.Transaction.TRANSACTION_UPDATED + id);
    }

    public void deleteTransaction(final String id) {
        transactionRepository.delete(findTransactionByTransactionId(id));
        log.info(BusinessLogMessage.Transaction.TRANSACTION_DELETED + id);
    }

    public TransactionDto findTransactionById(final String id) {
        Transaction transaction = findTransactionByTransactionId(id);

        log.info(BusinessLogMessage.Transaction.TRANSACTION_FOUND + id);
        return converter.convert(transaction);
    }

    public List<TransactionDto> findAllTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();

        if (transactionList.isEmpty()) {
            log.info(BusinessLogMessage.Transaction.TRANSACTION_LIST_EMPTY);
            throw new TransactionalNotFoundException(BusinessLogMessage.Transaction.TRANSACTION_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Transaction.TRANSACTION_LISTED);
        return converter.convert(transactionList);
    }

    private Transaction findTransactionByTransactionId(final String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Transaction.TRANSACTION_NOT_FOUND + id);
                    throw new TransactionalNotFoundException(BusinessLogMessage.Transaction.TRANSACTION_NOT_FOUND + id);
                });
    }
    public Optional<Transaction> getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId);
    }
}
