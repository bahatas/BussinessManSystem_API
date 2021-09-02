package com.cybertek.businessmansystem_api.entity;



import com.cybertek.businessmansystem_api.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private Boolean enabled;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Role role;
}
