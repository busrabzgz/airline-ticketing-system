package com.example.airlineticketingsystem.serviceTest;


import com.bms.airlinesbookingsystem.entitiy.ContactDetail;
import com.bms.airlinesbookingsystem.exception.ContactDetailNotFoundException;
import com.bms.airlinesbookingsystem.mapper.ContactDetailDtoMapper;
import com.bms.airlinesbookingsystem.repository.ContactDetailRepository;
import com.bms.airlinesbookingsystem.service.ContactDetailService;
import com.bms.airlinesbookingsystem.service.PassengerService;
import com.bms.airlinesbookingsystem.service.StateService;
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
public class ContactDetailServiceTest {

    @Mock
    private ContactDetailRepository contactDetailRepository;

    @Mock
    private PassengerService passengerService;

    @Mock
    private StateService stateService;

    @Mock
    private ContactDetailDtoMapper converter;

    @InjectMocks
    private ContactDetailService contactDetailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }






    @Test
    public void testFindContactDetailById() {
        ContactDetail existingContactDetail = new ContactDetail();
        when(contactDetailRepository.findById(anyString())).thenReturn(Optional.of(existingContactDetail));

        contactDetailService.findContactDetailById("contact-detail-id");

        verify(converter, times(1)).convert(existingContactDetail);
    }

    @Test
    public void testFindAllContactDetails() {
        ContactDetail existingContactDetail = new ContactDetail();
        List<ContactDetail> contactDetailList = Arrays.asList(existingContactDetail);

        when(contactDetailRepository.findAll()).thenReturn(contactDetailList);

        contactDetailService.findAllContactDetails();

        verify(converter, times(1)).convert(contactDetailList);
    }

    @Test
    public void testFindAllContactDetails_EmptyList() {
        when(contactDetailRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(ContactDetailNotFoundException.class, () -> {
            contactDetailService.findAllContactDetails();
        });
    }
}
