package prueba.framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
    public static List<String> cargarClases(String path) throws Exception {
        List<String> clases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank() && !linea.startsWith("#")) {
                    String[] partes = linea.split(";");
                    for (String clase : partes) {
                        clases.add(clase.trim());
                    }
                }
            }
        }
        return clases;
    }
}
