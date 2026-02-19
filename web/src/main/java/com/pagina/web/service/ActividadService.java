package com.pagina.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pagina.web.model.actividad;
import com.pagina.web.model.rea;
import com.pagina.web.repository.ActividadRepository;
import com.pagina.web.repository.ReaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final ReaRepository reaRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public List<actividad> listarTodas() {
        return actividadRepository.findAll();
    }

    public List<actividad> listarPorRea(Long reaId) {
        rea rea = reaRepository.findById(reaId)
                .orElseThrow(() -> new RuntimeException("REA no encontrada"));
        return actividadRepository.findByRea(rea);
    }

    public actividad guardar(actividad actividad, Long reaId) {
        rea rea = reaRepository.findById(reaId)
                .orElseThrow(() -> new RuntimeException("REA no encontrada"));

        actividad.setRea(rea);
        return actividadRepository.save(actividad);
    }

    public void eliminar(Long id) {
        actividadRepository.deleteById(id);
    }

    public actividad subirArchivo(Long actividadId, MultipartFile archivo) {

        actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        try {

            // Crear carpeta si no existe
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename();

            Path rutaArchivo = uploadPath.resolve(nombreArchivo);

            // Guardar archivo en disco
            Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

            // Guardar ruta en base de datos
            actividad.setArchivo(nombreArchivo);
            actividadRepository.save(actividad);

            return actividad;

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }
    }

    public void eliminarArchivo(Long actividadId) {

        actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        String nombreArchivo = actividad.getArchivo();

        if (nombreArchivo != null && !nombreArchivo.isEmpty()) {

            try {
                Path rutaArchivo = Paths.get(UPLOAD_DIR).resolve(nombreArchivo);
                Files.deleteIfExists(rutaArchivo);

                // Limpiar campo en la base de datos
                actividad.setArchivo(null);
                actividadRepository.save(actividad);

            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar el archivo", e);
            }
        }
    }

    public Optional<actividad> buscarPorId(Long id) {
        return actividadRepository.findById(id);
    }
}
