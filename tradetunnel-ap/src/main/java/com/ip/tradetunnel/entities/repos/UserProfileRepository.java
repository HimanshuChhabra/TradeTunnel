package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.Address;
import com.ip.tradetunnel.entities.UserProfile;
import java.lang.String;
import java.util.List;
import java.util.Set;


public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
		List<UserProfile> findByEmailId(String emailid);
		List<UserProfile> findByAddress(Address address);
}
