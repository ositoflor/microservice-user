package com.api.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "TB_USERS")
@Table(name = "TB_USERS")
@SQLDelete(sql = "UPDATE tb_users SET is_deleted = true WHERE id_user=?")
@Where(clause = "is_deleted=false")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    @JoinColumn(name = "id_address")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private Address address;
}
