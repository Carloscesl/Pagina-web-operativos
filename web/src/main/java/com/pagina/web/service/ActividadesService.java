package com.pagina.web.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pagina.web.model.Actividades;
import com.pagina.web.repository.ActividadesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActividadesService {

    private final ActividadesRepository actividadesRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    // Crear carpeta uploads si no existe
    @PostConstruct
    public void init() {
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public List<Actividades> listar() {
        return actividadesRepository.findAll();
    }

    public void guardar(MultipartFile archivo) throws IOException {
        String rutaCompleta = UPLOAD_DIR + archivo.getOriginalFilename();
        archivo.transferTo(new File(rutaCompleta));

        Actividades doc = Actividades.builder()
                .nombre(archivo.getOriginalFilename())
                .nombre_archivo(archivo.getOriginalFilename())
                .ruta(rutaCompleta)
                .build();

        actividadesRepository.save(doc);
    }

    public byte[] descargar(Long id) throws IOException {
        Actividades doc = actividadesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        return Files.readAllBytes(new File(doc.getRuta()).toPath());
    }

    public void eliminarArchivo(Long id) {
        Actividades doc = actividadesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        if (doc.getRuta() != null) {
            File archivo = new File(doc.getRuta());
            if (archivo.exists()) {
                archivo.delete();
            }
            doc.setRuta(null);
            doc.setNombre_archivo(null);
            actividadesRepository.save(doc);
        }
    }

    public void actualizarArchivo(Long id, MultipartFile archivo) throws IOException {
        Actividades doc = actividadesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        String ruta = UPLOAD_DIR + archivo.getOriginalFilename();
        archivo.transferTo(new File(ruta));

        doc.setRuta(ruta);
        doc.setNombre_archivo(archivo.getOriginalFilename());

        actividadesRepository.save(doc);
    }

}
