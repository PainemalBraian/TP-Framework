package prueba.utilizacion;

import prueba.framework.Accion;

public class AccionUno implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando Acción Uno...");
    }

    @Override
    public String nombreItemMenu() {
        return "Acción 1";
    }

    @Override
    public String descripcionItemMenu() {
        return "Esto es para mostrar una prueba simple.";
    }
}