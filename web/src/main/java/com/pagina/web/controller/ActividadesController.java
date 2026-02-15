package com.pagina.web.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pagina.web.model.Actividades;
import com.pagina.web.service.ActividadesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ActividadesController {

    private final ActividadesService service;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("actividades", service.listar());
        return "index";
    }

    // Endpoint para mostrar archivo en iframe
    @GetMapping("/ver/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> verArchivo(@PathVariable Long id) throws IOException {
        Actividades doc = service.listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        byte[] archivo = service.descargar(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf") // suponer PDF
                .body(archivo);
    }

    // Endpoint para descargar archivo
    @GetMapping("/descargar/{id}")
    public ResponseEntity<byte[]> descargar(@PathVariable Long id) throws IOException {
        Actividades doc = service.listar().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        byte[] archivo = service.descargar(id);
        String nombreArchivo = doc.getNombre_archivo() != null ? doc.getNombre_archivo() : "archivo";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
                .body(archivo);
    }

    // Subir o actualizar archivo
    @PostMapping("/subir")
    public String subir(@RequestParam("id") Long id,
            @RequestParam("archivo") MultipartFile archivo) throws IOException {
        service.actualizarArchivo(id, archivo);
        return "redirect:/";
    }

    // Eliminar solo archivo
    @GetMapping("/eliminar-archivo/{id}")
    public String eliminarArchivo(@PathVariable Long id) {
        service.eliminarArchivo(id);
        return "redirect:/";
    }

}
