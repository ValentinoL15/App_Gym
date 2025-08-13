package com.valentino.Gym_App.model;

import com.valentino.Gym_App.model.enums.FaseGym;
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
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rutina_id;

    @ManyToOne
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    private Integer repeticiones;
    private Integer series;
    private double peso;
    @Enumerated(EnumType.STRING)
    private FaseGym fase;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

}
