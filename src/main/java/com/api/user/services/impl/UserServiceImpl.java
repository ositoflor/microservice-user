package com.api.user.services.impl;

import com.api.user.domain.Address;
import com.api.user.domain.User;
import com.api.user.domain.dtos.UserDto;
import com.api.user.repositories.UserRepository;
import com.api.user.services.UserService;
import com.api.user.exceptionhandler.excptions.CPFExistsException;
import com.api.user.exceptionhandler.excptions.CPFInvalidException;
import com.api.user.exceptionhandler.excptions.UserNotFund;
import com.api.user.services.util.CpfValidate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(UserDto newUser) {
        if (!CpfValidate.isValidCpf(newUser.getCpf())) {
            throw new CPFInvalidException();
        }
        if (userRepository.existsByCpf(CpfValidate.formattedCPF(newUser.getCpf()))){
            throw new CPFExistsException();
        }
        Address address = new Address();
        User user = new User();
        BeanUtils.copyProperties(newUser, user);
        BeanUtils.copyProperties(newUser.getAddress(), address);
        user.setAddress(address);
        user.setCpf(CpfValidate.formattedCPF(newUser.getCpf()));

        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getUser(Long id) {
        Optional user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFund();
        }
        return user;
    }

    @Override
    public Page<User> findByName(String name, Pageable pageable) {
        return userRepository.getName(name,pageable);
    }

   @Override
    public Page<User> findByState(String state, Pageable pageable) {
        return userRepository.findByState(state,pageable);
    }

    @Override
    public Page<User> findByIsActive(Boolean status, Pageable pageable) {
        return userRepository.findByIsActive(status,pageable);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User changeStatus(Long id) {
        Optional<User> userGet = getUser(id);
        userGet.get().setIsActive(!userGet.get().getIsActive());
        return userRepository.save(userGet.get());
    }

}
