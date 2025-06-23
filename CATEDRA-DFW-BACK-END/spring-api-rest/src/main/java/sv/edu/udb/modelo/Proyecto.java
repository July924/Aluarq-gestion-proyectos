package sv.edu.udb.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProyecto")
    private Long idProyecto;
    private String nombreProyecto;
    private String descripcionProyecto;
    private Date fechaInicio;
    private Date fechaFin;
    private String ubicacionProyecto;
    private int numeroTrabajadores;
    @Column(nullable = false)
    private Double viaticosAsignados;
    private Double presupuestoMateriales;

    @ManyToOne
    @JoinColumn(name = "idEstado")
    private EstadoProyecto estadoProyecto;

    @ManyToOne
    @JoinColumn(name = "idTipoProyecto")
    private TipoProyecto tipoProyecto;
    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    private Empleado arquitectoEncargado;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialesProyecto> materiales;

    public void setArquitectoEncargado(Empleado empleadoNoEncontrado) {
    }
}