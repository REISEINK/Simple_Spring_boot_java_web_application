package com.huadi.education.mapper;

import com.huadi.education.entity.Address;
import com.huadi.education.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressMapper {
    int insertOrgAddress(Address address);
}
