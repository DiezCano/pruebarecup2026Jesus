package Main;

import io.MeteoFile;
import service.ServicioGeneralMeteorologico;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ServicioGeneralMeteorologico sgm =
                MeteoFile.cargarCSV();

        Scanner sc = new Scanner(System.in);

        int opcion;

        do{

            System.out.println("""
                    
                    1. Temperaturas estación
                    2. Temperaturas año estación
                    3. Temperaturas comunidad
                    4. Máxima temperatura
                    5. Estaciones por comunidad
                    6. Temperatura media alta
                    7. Máxima por comunidad
                    8. Borrar comunidad
                    9. Fahrenheit
                    10. Número registros
                    0. Salir
                    
                    """);

            opcion = sc.nextInt();

            switch(opcion){

                case 1 ->
                        System.out.println(
                                sgm.getTempPorEstacion(
                                        1));

                case 2 ->
                        System.out.println(
                                sgm.getTempPorAnioEstacion(
                                        1,2025));

                case 3 ->
                        System.out.println(
                                sgm.getTempPorComunidad(
                                        "Valencia"));

                case 4 ->
                        System.out.println(
                                sgm.getTempMaxRegistrada());

                case 5 ->
                        System.out.println(
                                sgm.getEstacionesPorComunidad());

                case 6 ->
                        System.out.println(
                                sgm.isTempMediaAlta());

                //case 7 ->
                        //System.out.println(
                                //sgm.getTempMaxPorComunidad());

                case 8 ->
                        sgm.borrarEstacionesComunidad(
                                "Valencia");

                case 9 ->
                        sgm.pintaTempEstacionFarenheit(
                                1);

                case 10 ->
                        System.out.println(
                                sgm.getNumRegistros());
            }

        }while(opcion!=0);
    }
}
