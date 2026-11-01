package com.velox.userservice.models.entity;

import com.velox.userservice.models.eums.Roles;
import com.velox.userservice.models.eums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {

    @Column(nullable = false, unique = true, length = 50)
    private String userName;
    @Column(nullable = false)
    private String keyCloakId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Roles roles;
    @Column(nullable = false)
    private boolean isActive = true;
    @Column(nullable = false)
    private String email;
    private String phone;
    private LocalDateTime lastLoginTimeStamp;
}
