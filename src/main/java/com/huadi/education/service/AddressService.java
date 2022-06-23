package com.huadi.education.service;

import com.huadi.education.entity.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {
    int insertOrgAddress(Address address);

}
