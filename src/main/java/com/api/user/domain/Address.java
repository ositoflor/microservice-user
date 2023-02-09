package com.api.user.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private long idAddress;
    @Column
    private String street;
    @Column
    private int number;
    @Column
    private String complement;
    @Column
    private String district;
    @Column
    private String city;
    @Column
    private String state;
}
