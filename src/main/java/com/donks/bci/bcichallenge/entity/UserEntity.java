package com.donks.bci.bcichallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;

    private Timestamp createdAt = Timestamp.from(Instant.now());
    private Timestamp updatedAt = Timestamp.from(Instant.now());
    private Timestamp lastLogin =  Timestamp.from(Instant.now());


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "inner_table",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")})
    private List<PhoneNumber> phones;
}
