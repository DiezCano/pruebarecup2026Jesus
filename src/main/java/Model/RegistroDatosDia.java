package Model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegistroDatosDia {

    private int id;
    private LocalDate fecha;
    private double tempMax;
    private double tempMin;
    private double tempMedia;
}
