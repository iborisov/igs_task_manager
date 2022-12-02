package ru.iborisov.igshw.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private final Integer user_id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
}
