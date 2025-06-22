package painemal.framework;

import java.util.*;
import java.nio.file.*;

public class MenuFramework {

    private List<Accion> acciones = new ArrayList<>();

    public MenuFramework(String configPath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configPath));
            for (String line : lines) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                Class<?> clazz = Class.forName(line.trim());
                Accion accion = (Accion) clazz.getDeclaredConstructor().newInstance();
                acciones.add(accion);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando configuracion", e);
        }
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBienvenido, estas son sus opciones: (Solo ilustrativo)\n");
            for (int i = 0; i < acciones.size(); i++) {
                Accion a = acciones.get(i);
                System.out.printf("%d. %s (%s)\n", i + 1, a.nombreItemMenu(), a.descripcionItemMenu());
            }
            System.out.printf("%d. Salir\n", acciones.size() + 1);

            System.out.print("\nIngrese su opción: ");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Opción inválida.");
                continue;
            }

            if (opcion == acciones.size() + 1) {
                System.out.println("Saliendo...");
                break;
            }

            if (opcion >= 1 && opcion <= acciones.size()) {
                try {
                    acciones.get(opcion - 1).ejecutar();
                } catch (Exception e) {
                    System.out.println("Error al ejecutar la acción: " + e.getMessage());
                }
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}
