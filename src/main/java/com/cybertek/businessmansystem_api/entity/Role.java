package com.cybertek.businessmansystem_api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles_table")
public class Role extends BaseEntity {
    private String description;
}
