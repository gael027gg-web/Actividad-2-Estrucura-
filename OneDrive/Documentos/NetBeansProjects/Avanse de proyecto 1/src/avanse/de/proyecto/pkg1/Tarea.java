package avanse.de.proyecto.pkg1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarea {

    private String nombre;
    private String departamento;
    private int urgencia; // 1 = baja, 2 = media, 3 = alta
    private LocalDate fechaLimite;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Tarea(String nombre, String departamento, int urgencia, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.urgencia = urgencia;
        this.fechaLimite = fechaLimite;
    }

    // Getters (encapsulación)
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public int getUrgencia() { return urgencia; }
    public LocalDate getFechaLimite() { return fechaLimite; }

    @Override
    public String toString() {
        String fechaStr = (fechaLimite == null) ? "Sin fecha" : fechaLimite.format(FORMATO);
        String urgTexto = switch (urgencia) {
            case 3 -> "ALTA";
            case 2 -> "MEDIA";
            default -> "BAJA";
        };
        return String.format("[Tarea: %s | Depto: %s | Urg: %s(%d) | Fecha: %s]",
                nombre, departamento, urgTexto, urgencia, fechaStr);
    }
}
