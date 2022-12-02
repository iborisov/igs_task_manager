package ru.iborisov.igshw.users.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.iborisov.igshw.users.db.Users;
import ru.iborisov.igshw.users.models.User;

@RestController
public class UsersController {

    private final Users users;

    @Autowired
    public UsersController(Users users) {
        this.users = users;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRequest {
        private String first_name;
        private String last_name;
        private String username;
        private String password;

        public User toUserModel() {
            return new User(null, first_name, last_name, username, password);
        }
    }

    @Data
    public static class CreateUserResponse {
        private final Integer id;
    }

    @PostMapping("/user/sign-up")
    public Mono<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        final User createdUser = users.createUser(request.toUserModel());
        return Mono.just(new CreateUserResponse(createdUser.getUser_id()));
    }
}
