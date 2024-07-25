package ru.diploma.inflate_server.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.auth.domain.Role;
import ru.diploma.inflate_server.auth.domain.User;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingIn;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingUp;
import ru.diploma.inflate_server.auth.dto.UserDTOForUpdate;
import ru.diploma.inflate_server.auth.repository.UserRepository;
import ru.diploma.inflate_server.model.enums.WorkerType;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //save user in database
    public User save(User user) {
        return userRepository.save(user);
    }

    //create new user
    public User create(UserDTOForSingUp userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        var user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(getRoleByWorkerType(userDTO.type()));
        return save(user);
    }

    //update password
    public void update(UserDTOForUpdate userDTO) {
        var user = userRepository.findByUsername(userDTO.username()).orElseThrow();
        user.setPassword(userDTO.newPassword());
        userRepository.save(user);
    }

    //get user by username(login)
    public User getByUsername(String username) {
      return userRepository.findByUsername(username)
                .orElse(null);
    }


    private Role getRoleByWorkerType(WorkerType workerType) {
        Role role = null;
        switch (workerType) {
            case WORKER: {
                role = Role.USER;
                break;
            }
            case ADMIN: {
                role = Role.ADMIN;
                break;
            }
            case STORAGE_WORKER: {
                role = Role.STORAGE_USER;
                break;
            }
        }
        return role;
    }
}
