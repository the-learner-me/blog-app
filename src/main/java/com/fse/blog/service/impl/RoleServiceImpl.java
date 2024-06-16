package com.fse.blog.service.impl;

import com.fse.blog.repository.RoleRepository;
import com.fse.blog.service.RoleService;
import com.fse.blog.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
