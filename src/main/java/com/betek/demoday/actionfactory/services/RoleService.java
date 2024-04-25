package com.betek.demoday.actionfactory.services;

import com.betek.demoday.actionfactory.exceptions.ApiException;
import com.betek.demoday.actionfactory.models.Role;
import com.betek.demoday.actionfactory.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
        try {
            return roleRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Role with this id does not exist"));
        } catch (ApiException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Role with this id does not exist");
        }
    }
}
