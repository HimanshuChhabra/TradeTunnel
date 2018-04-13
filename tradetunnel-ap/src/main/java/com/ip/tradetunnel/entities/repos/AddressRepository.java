package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.Address;
import java.lang.String;
import java.util.List;
import java.util.Set;

public interface AddressRepository extends CrudRepository<Address, Long> {
			Set<Address> findByCity(String city);
}
