package com.pagina.web.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "cadi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class cadi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "total_actividades", nullable = false)
    private Integer totalActividades;

    @OneToMany(mappedBy = "cadi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<rea> reas;
}