package com.viswateja.farmstead.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPk;

    private String username;

    private String password;

    private String emailAddress;

    private String role;

    @OneToOne
    @JoinColumn(
            name = "addressFk",
            referencedColumnName = "addressId"
    )
    private Address address;
}
