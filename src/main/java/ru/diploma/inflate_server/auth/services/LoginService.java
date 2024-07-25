package ru.diploma.inflate_server.auth.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.auth.domain.User;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingIn;
import ru.diploma.inflate_server.auth.dto.UserDTOForSingUp;
import ru.diploma.inflate_server.auth.dto.UserDTOForUpdate;
import ru.diploma.inflate_server.model.Worker;
import ru.diploma.inflate_server.services.WorkerService;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    private final WorkerService workerService;

    //login old user
    public Optional<User> login(UserDTOForSingIn userDTO) {
        User user = userService.getByUsername(userDTO.username());
        if (user != null && userDTO.password().equals(user.getPassword())) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    //register new user
    public User register(UserDTOForSingUp userDTO) {
        var newUser = userService.create(userDTO);
        var worker = Worker.builder()
                .id(newUser.getId())
                .login(newUser.getUsername())
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .department(userDTO.department())
                .patronymic(userDTO.patronymic())
                .type(userDTO.type())
                .joinDate(userDTO.joinDate())
                .build();
        workerService.addUser(worker);
        return userService.getByUsername(userDTO.username());
    }

    //change password
    public void updatePassword(UserDTOForUpdate userDTO) {
        var user = userService.getByUsername(userDTO.username());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (user.getPassword().equals(userDTO.oldPassword())) {
            user.setPassword(userDTO.newPassword());
        }
        userService.update(userDTO);
    }
}

