package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
