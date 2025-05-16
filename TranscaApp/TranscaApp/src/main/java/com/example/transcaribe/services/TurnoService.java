// src/main/java/com/example/transcaribe/services/TurnoService.java
package com.example.transcaribe.services;

import com.example.transcaribe.dto.TurnoDto;
import com.example.transcaribe.dto.DetalleTurnoDto;
import com.example.transcaribe.entity.Horario;
import com.example.transcaribe.entity.Vehiculo;
import com.example.transcaribe.repository.HorarioRepository;
import com.example.transcaribe.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    private final HorarioRepository  horarioRepo;
    private final VehiculoRepository vehiculoRepo;

    public TurnoService(HorarioRepository horarioRepo,
                        VehiculoRepository vehiculoRepo) {
        this.horarioRepo  = horarioRepo;
        this.vehiculoRepo = vehiculoRepo;
    }

    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("HH:mm");

    public List<TurnoDto> obtenerTurnosPorConductor(String conductorId) {
        return horarioRepo.findAllByConductorId(conductorId)
                .stream()
                .map(h -> {
                    String inicio   = h.getInicioJornada().format(TF);
                    String fin      = h.getFinJornada().format(TF);
                    Duration d      = Duration.between(h.getInicioDescanso(), h.getFinDescanso());
                    String descanso = d.toMinutes() + " min";
                    return new TurnoDto(
                            h.getDia(),
                            inicio,
                            fin,
                            descanso,
                            h.getRutaAsignada(),
                            h.getNumeroTranscaribe()
                    );
                })
                .collect(Collectors.toList());
    }

    public DetalleTurnoDto obtenerDetallePorDia(String conductorId, String dia) {
        Horario h = horarioRepo.findByConductorIdAndDia(conductorId, dia);
        if (h == null) return null;

        DetalleTurnoDto dto = new DetalleTurnoDto();
        dto.setDia       (h.getDia());
        dto.setHoraInicio(h.getInicioJornada().format(TF));
        dto.setHoraFin   (h.getFinJornada().format(TF));
        Duration d       = Duration.between(h.getInicioDescanso(), h.getFinDescanso());
        dto.setDescanso  (d.toMinutes() + " min");
        dto.setRuta      (h.getRutaAsignada());

        // Aquí ya no usamos getVehiculoId: usamos numeroTranscaribe para buscar
        Integer numeroTrans = h.getNumeroTranscaribe();
        Vehiculo v = vehiculoRepo
                .findByIdTranscar(numeroTrans)
                .orElse(null);

        if (v != null) {
            dto.setModeloVehiculo(v.getModelo());
            dto.setPlaca        (v.getPlaca());
            dto.setIdTranscar   (v.getIdTranscar()); // que debe ser tu Integer
        }
        return dto;
    }
}
