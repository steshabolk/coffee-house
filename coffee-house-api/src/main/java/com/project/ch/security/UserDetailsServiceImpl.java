package com.project.ch.security;

import com.project.ch.entity.User;
import com.project.ch.exception.ApiError;
import com.project.ch.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public UserDetails loadUserByUsername(String phone) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> {
            throw ApiError.USER_NOT_FOUND.toException(phone);
        });
        return new UserDetailsImpl(user);
    }
}
