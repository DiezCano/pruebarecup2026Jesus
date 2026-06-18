package Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class EstacionMeteorologica {

    private int id;
    private String nombre;
    private String comunidad;
    private double latitud;
    private double longitud;

    private TreeSet<RegistroDatosDia> registros;

    public EstacionMeteorologica(int id, String nombre, String comunidad, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.comunidad = comunidad;
        this.latitud = latitud;
        this.longitud = longitud;

        registros = new TreeSet<>(
                Comparator.comparing(RegistroDatosDia::getFecha)
        );
    }

    public void nuevoRegistro(RegistroDatosDia rdd) {
        registros.add(rdd);
    }

    public void eliminarRegistro(RegistroDatosDia rdd) {
        registros.remove(rdd);
    }

    public List<RegistroDatosDia> buscarRegistro(LocalDate fecha) {

        List<RegistroDatosDia> lista = new ArrayList<>();

        for (RegistroDatosDia r : registros) {
            if (r.getFecha().equals(fecha)) {
                lista.add(r);
            }
        }

        return lista;
    }
}
