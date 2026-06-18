package service;

import Model.EstacionMeteorologica;
import Model.RegistroDatosDia;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ServicioGeneralMeteorologico {

    private ArrayList<EstacionMeteorologica> estaciones = new ArrayList<>();

    public void nuevaEstacion(EstacionMeteorologica em) {
        estaciones.add(em);
    }

    public List<RegistroDatosDia> getTempPorEstacion(int id) {
        return estaciones.stream()
                .filter(e -> e.getId() == id)
                .flatMap(e -> e.getRegistros().stream())
                .sorted(Comparator.comparing(RegistroDatosDia::getFecha))
                .toList();
    }

    public List<RegistroDatosDia> getTempPorAnioEstacion(int id, int anio) {
        return estaciones.stream()
                .filter(e -> e.getId() == id)
                .flatMap(e -> e.getRegistros().stream())
                .filter(r -> r.getFecha().getYear() == anio)
                .sorted(Comparator.comparing(RegistroDatosDia::getTempMedia))
                .toList();
    }

    public List<RegistroDatosDia> getTempPorComunidad(String comunidad) {
        return estaciones.stream()
                .filter(e -> e.getComunidad().equalsIgnoreCase(comunidad))
                .flatMap(e -> e.getRegistros().stream())
                .sorted(Comparator.comparing(RegistroDatosDia::getFecha))
                .toList();
    }

    public RegistroDatosDia getTempMaxRegistrada() {
        return estaciones.stream()
                .flatMap(e -> e.getRegistros().stream())
                .max(Comparator.comparing(RegistroDatosDia::getTempMax))
                .orElse(null);
    }

    public Map<String, List<EstacionMeteorologica>> getEstacionesPorComunidad() {
        return estaciones.stream()
                .collect(Collectors.groupingBy(EstacionMeteorologica::getComunidad));
    }

    public Boolean isTempMediaAlta() {
        return estaciones.stream()
                .flatMap(e -> e.getRegistros().stream())
                .anyMatch(r -> r.getTempMedia() > 30);
    }

    public void borrarEstacionesComunidad(String comunidad) {
        estaciones.removeIf(e -> e.getComunidad().equalsIgnoreCase(comunidad));
    }

    public void pintaTempEstacionFarenheit(int id) {
        getTempPorEstacion(id)
                .forEach(r -> System.out.println((r.getTempMedia() * 9 / 5) + 32));
    }

    public Map<String, Double> getNumRegistros() {
        return estaciones.stream()
                .collect(Collectors.toMap(
                        EstacionMeteorologica::getNombre,
                        e -> (double) e.getRegistros().size(),
                        (existente, reemplazo) -> existente
                ));
    }

    public RegistroDatosDia getTempMaxPorComunidad(String comunidad) {
        return estaciones.stream()
                .filter(e -> e.getComunidad().equalsIgnoreCase(comunidad))
                .flatMap(e -> e.getRegistros().stream())
                .max(Comparator.comparing(RegistroDatosDia::getTempMax))
                .orElse(null);
    }
}