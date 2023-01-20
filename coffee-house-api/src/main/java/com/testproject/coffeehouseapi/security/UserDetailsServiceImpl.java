package com.testproject.coffeehouseapi.security;

import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone).orElseThrow(
                () -> new UsernameNotFoundException(ExceptionMessage.setMessage(MessageConstant.PHONE_NOT_FOUND, phone)));
        return new UserDetailsImpl(user);
    }
}
