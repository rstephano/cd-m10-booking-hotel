package br.com.iteris.bookinghotel.integrations;

import br.com.iteris.bookinghotel.integrations.dto.response.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PaymentClient", url = "http://localhost:8081")
public interface PaymentClient {

    @GetMapping(value = "/payment/{id}")
    Payment getPayment(@PathVariable("id") String id);

}
