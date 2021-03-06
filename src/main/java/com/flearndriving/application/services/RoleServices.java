package com.flearndriving.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.application.entities.Role;
import com.flearndriving.application.responsitories.RoleRespository;

@Service
public class RoleServices {

	@Autowired
	private RoleRespository roleRespository;
	
	public List<Role> findAllRole() {
	    List<Role> listRole = roleRespository.findAll();
		if (listRole != null) {
			return listRole;
		}
		return new ArrayList<Role>();
	}
}
