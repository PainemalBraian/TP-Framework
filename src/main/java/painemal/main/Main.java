package painemal.main;

import painemal.framework.MenuFramework;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Debe proporcionar la ruta al archivo de configuración.");
            System.exit(1);
        }
        String configPath = args[0];
        MenuFramework framework = new MenuFramework(configPath);
        framework.mostrarMenu();
    }
}
// usar este comando para ejecutar
// java -cp target/classes painemal.main.Main src/main/resources/acciones.config