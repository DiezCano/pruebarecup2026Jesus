package io;

import Model.EstacionMeteorologica;
import Model.RegistroDatosDia;
import service.ServicioGeneralMeteorologico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MeteoFile {

    public static ServicioGeneralMeteorologico cargarCSV() {

        ServicioGeneralMeteorologico sgm = new ServicioGeneralMeteorologico();
        DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try (BufferedReader br = new BufferedReader(new FileReader("src/estaciones.csv"))) {
            br.readLine();
            String linea;
            int idAutoincremental = 1;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                EstacionMeteorologica em = new EstacionMeteorologica(
                        idAutoincremental++,
                        datos[1].trim(),
                        datos[2].trim(),
                        Double.parseDouble(datos[3].trim()),
                        Double.parseDouble(datos[4].trim())
                );

                sgm.nuevaEstacion(em);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/registros.csv"))) {
            br.readLine();
            String linea;
            int indiceEstacion = 0;

            List<EstacionMeteorologica> listaEstaciones = sgm.getEstaciones();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (listaEstaciones.isEmpty()) break;

                int idRegistro = Integer.parseInt(datos[0].trim());
                LocalDate fechaOriginal = LocalDate.parse(datos[1].trim(), formateadorFecha);
                LocalDate fecha = LocalDate.of(2025, fechaOriginal.getMonth(), fechaOriginal.getDayOfMonth());

                double max = Double.parseDouble(datos[2].trim());
                double min = Double.parseDouble(datos[3].trim());
                double media = Double.parseDouble(datos[4].trim());

                RegistroDatosDia r = new RegistroDatosDia(idRegistro, fecha, max, min, media);

                EstacionMeteorologica em = listaEstaciones.get(indiceEstacion);
                em.nuevoRegistro(r);

                indiceEstacion = (indiceEstacion + 1) % listaEstaciones.size();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return sgm;
    }
}