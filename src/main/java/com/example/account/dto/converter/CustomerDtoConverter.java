package com.example.account.dto.converter;

import com.example.account.dto.AccountCustomerDto;
import com.example.account.dto.CustomerDto;
import com.example.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter converter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.converter = converter;
    }

    public AccountCustomerDto convertToAccountCustomer(Customer from) {
        if (from == null) {
            return new AccountCustomerDto("null", "null", "null");
        }
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }

    public CustomerDto convertToCustomerDto(Customer from) {
        return new CustomerDto(from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts()
                        .stream()
                        .map(converter::convert)
                        .collect(Collectors.toSet()));
    }
}