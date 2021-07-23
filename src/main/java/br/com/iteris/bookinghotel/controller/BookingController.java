package br.com.iteris.bookinghotel.controller;

import br.com.iteris.bookinghotel.Paths;
import br.com.iteris.bookinghotel.dto.Booking;
import br.com.iteris.bookinghotel.dto.request.BookingRequest;
import br.com.iteris.bookinghotel.integrations.PaymentClient;
import br.com.iteris.bookinghotel.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    private final PaymentClient paymentClient;

    @PostMapping(path = Paths.BOOKING)
    public ResponseEntity<Void> book(@RequestBody BookingRequest booking) {
        log.info("Checking payment...");
        paymentClient.getPayment(booking.getPaymentId());
        log.info("Processing booking...");
        bookingService.book( //
            Booking.builder() //
                .begin(booking.getBegin()) //
                .finish(booking.getFinish()) //
                .adults(booking.getAdults()) //
                .kids(booking.getKids()) //
                .build() //
        );
        log.info("Booking created");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
