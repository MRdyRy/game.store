package com.rudy.ryanto.user.management.util;

import com.rudy.ryanto.user.management.dto.Customer;
import com.rudy.ryanto.user.management.dto.CustomerDto;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CustomerMapper {

    public Customer mappingDtoToCustomer(CustomerDto dto) throws ParseException {
        return Customer.builder()
                .customerName(dto.getCustomerName().trim())
                .dob(CustomerUtil.formatReportDate.parse(dto.getDob()))
                .password(DigestUtils.md5Hex(dto.getPassword()).toUpperCase())
                .sex(dto.getSex())
                .statusActive(dto.getStatusActive())
                .build();
    }
}
