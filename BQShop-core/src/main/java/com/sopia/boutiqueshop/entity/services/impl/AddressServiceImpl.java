package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Address;
import com.sopia.boutiqueshop.entity.services.AddressService;
import com.sopia.boutiqueshop.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressServiceImpl implements AddressService {


    @Autowired
    public AddressesRepository addressesRepository;

    @Override
    public List<Address> getAddreses()
    {
        return addressesRepository.findAll();
    }


}
