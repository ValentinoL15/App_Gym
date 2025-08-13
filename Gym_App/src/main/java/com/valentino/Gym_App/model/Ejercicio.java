package com.valentino.Gym_App.model;

import com.valentino.Gym_App.model.enums.CategoryGym;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ejercicio_id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryGym category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

}
