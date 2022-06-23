package com.huadi.education.service.impl;

import com.huadi.education.entity.Address;
import com.huadi.education.mapper.AddressMapper;
import com.huadi.education.mapper.UserMapper;
import com.huadi.education.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public int insertOrgAddress(Address address) {
        return addressMapper.insertOrgAddress(address);
    }
}
