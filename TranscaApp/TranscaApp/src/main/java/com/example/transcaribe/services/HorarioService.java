package com.example.transcaribe.services;

import com.example.transcaribe.entity.Horario;
import com.example.transcaribe.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HorarioService {

    private final HorarioRepository repo;

    public HorarioService(HorarioRepository repo) {
        this.repo = repo;
    }

    /**
     * Lista todos los horarios de un conductor.
     */
    public List<Horario> obtenerHorariosPorConductor(String conductorId) {
        return repo.findAllByConductorId(conductorId);
    }

    /**
     * Obtiene un horario por su ID, lanza IllegalArgumentException si no existe.
     */
    public Horario obtenerHorarioPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Horario no encontrado con id: " + id
                ));
    }

    /**
     * Crea un nuevo horario (o actualiza si el ID ya existe),
     * validando que no haya solapamientos para el mismo conductor.
     */
    public Horario guardarHorario(Horario horario) {
        validarNoSolapamiento(horario);
        return repo.save(horario);
    }

    /**
     * Actualiza un horario existente.
     */
    public Horario actualizarHorario(String id, Horario cambios) {
        Horario existente = obtenerHorarioPorId(id);

        existente.setInicioJornada(cambios.getInicioJornada());
        existente.setInicioDescanso(cambios.getInicioDescanso());
        existente.setFinDescanso(cambios.getFinDescanso());
        existente.setFinJornada(cambios.getFinJornada());
        existente.setNumeroTranscaribe(cambios.getNumeroTranscaribe());
        existente.setRutaAsignada(cambios.getRutaAsignada());

        validarNoSolapamiento(existente);
        return repo.save(existente);
    }

    /**
     * Elimina un horario por ID, lanza IllegalArgumentException si no existe.
     */
    public void eliminarHorario(String id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Horario no encontrado con id: " + id);
        }
        repo.deleteById(id);
    }

    // ----------------- MÉTODOS PRIVADOS -----------------

    /**
     * Valida que el nuevo/actualizado horario no solape con otros del conductor.
     */
    private void validarNoSolapamiento(Horario candidato) {
        List<Horario> todos = repo.findAllByConductorId(candidato.getConductorId());
        for (Horario h : todos) {
            // Si es el mismo registro, lo saltamos
            if (Objects.equals(h.getId(), candidato.getId())) continue;
            if (solapan(h, candidato)) {
                throw new IllegalArgumentException(
                    "El horario se solapa con rango existente: " +
                    h.getInicioJornada() + "–" + h.getFinJornada()
                );
            }
        }
    }

    /**
     * Comprueba solapamiento entre dos intervalos [inicioJornada, finJornada].
     */
    private boolean solapan(Horario a, Horario b) {
        // Solapan si a.inicio <= b.fin && b.inicio <= a.fin
        return !b.getFinJornada().isBefore(a.getInicioJornada())
            && !b.getInicioJornada().isAfter(a.getFinJornada());
    }
}
