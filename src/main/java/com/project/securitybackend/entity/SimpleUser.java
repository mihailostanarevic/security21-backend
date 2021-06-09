package com.project.securitybackend.entity;

import com.project.securitybackend.util.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUser extends User {

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.PENDING;

    private LocalDateTime confirmationTime = LocalDateTime.now();

}
