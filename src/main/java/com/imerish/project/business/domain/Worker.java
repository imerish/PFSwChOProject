package com.imerish.project.business.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30)
    private String name;
    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 30)
    private String surname;
    @NotBlank(message = "Position is mandatory")
    @Size(min = 2, max = 30)
    private String position;
}
