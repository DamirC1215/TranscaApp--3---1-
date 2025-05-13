package com.example.transcaribe.services;

import com.example.transcaribe.entity.Horario;
import com.example.transcaribe.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Obtener el horario de un conductor específico
    public List<Horario> obtenerHorarioPorConductor(Long conductorId) {
        return horarioRepository.findByConductorId(conductorId);
    }

    // Guardar un nuevo horario
    public void guardarHorario(Horario horario) {
        horarioRepository.save(horario);
    }

    // Eliminar un horario por ID
    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
