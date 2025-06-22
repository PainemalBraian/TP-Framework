    
    Usar
    mvn clean compile
    java -cp target/classes painemal.main.Main src/main/resources/acciones.config
    
    TP10 - FrameWorks

    1. Desarrolle un framework para escribir aplicaciones que permitan lanzar ciertas acciones o procesos por línea de comandos.
    El framework deberá desplegar un menú con opciones (por consola), de los procesos que se pueden lanzar y se quedará esperando el input del usuario final (quien opera el sistema).  
    Al recibir un input, el framework ejecutará la acción en cuestión, e informará del éxito o fracaso de la ejecución (por consola también).
    Luego, nuevamente desplegará el menú y se quedará a la espera de una nueva entrada por parte del usuario final, o la opción “Salir” que termina el programa.
    El framework proveerá de una interfaz que los usuarios del framework (en adelante desarrolladores) deberán implementar para cada acción que deseen tener disponible en el menú para ejecutar.
    Esta interfaz será el mecanismo de extensión del framework. La interfaz será la siguiente:
    
    public interface Accion {
        void ejecutar();
        String nombreItemMenu();
        String descripcionItemMenu();
    }

    Además deberá ofrecer una alternativa de configuración (mediante un archivo de texto) por el cual los desarrolladores podrán especificar las clases que el framework utilizará.   
    Para una buena organización del código, utilice tres paquetes para el proyecto. En un paquete llamado {suApellido}.framework, pondrá las clases del framework.
    En el otro paquete llamado {suApellido}.utilizacion, pondrá dos clases concretas que implementen el punto de extensión del framework.
    El path donde se encuentra el archivo de configuración deberá indicarlo el usuario vía algún parámetro del framework.  
    Y en {suApellido}.main, una clase Main que muestre cómo se instancia el framework y se muestre su uso.

    Ejemplo de Uso
    Supongamos que un desarrollador utiliza nuestro framework, escribiendo los siguientes implementaciones de Accion:
    public class AccionUno implements Accion {
        @Override
        public void ejecutar() {
            System.out.println("Ejecutando AccionUno...");
        }

        @Override
        public String nombreItemMenu() {
            return "Accion 1";
        }
        
        @Override
        public String descripcionItemMenu() {
            return "Esto es para traer los twitts de José...";
            }
        }
        
    public class AccionDos implements Accion {
        
        @Override
        public void ejecutar() {
            System.out.println("Ejecutando AccionDos...");
        }
        
        @Override
        public String nombreItemMenu() {
            return "Accion 2";
        }
        
        @Override
        public String descripcionItemMenu() {
            return "Esto trae las primeras diez personas de la BD...";
        }
    }

    Y el siguiente archivo de configuración (si desea utilizar otro mecanismo para descubrir las clases que implementan Accion, puede hacerlo):
    #Implementaciones de Acciones acciones:  {paquete}.AccionUno; {paquete}.AccionDos
    Al instanciar el framework (desde un Main), en consola debería mostrarse el siguiente menú:

    Bienvenido, estas son sus opciones:
    
    1. AccionUno (Esto es para traer los twitts de José...)
    2. AccionDos (Esto trae las primeras diez personas de la BD...)
    3. Salir
    
    Ingrese su opción: _


    (Si se agregara una nueva clase Acción, entonces el menú mostraría esa nueva opción. Ahora solo se muestran dos acciones como ejemplo).
    El usuario final ingresará una opción, y el framework ejecutará la acción solicitada y una vez finalizada, volverá a mostrar el menú y esperará por una nueva entrada del usuario.
    
    Tips para implementar éste primer punto: En el repo https://github.com/enriquemolinari/oop2-frameworks
    podrá encontrar en el paquete blackbox.v2 como se utiliza reflections para instanciar objetos a partir de strings con el nombre de las clases.
    Y además cómo pueden usar archivos de configuración.

    2. Genere, solo con los fuentes del framework (no del cliente o utilización) un jar denominado framework-v1.0.jar, y cree otro proyecto en su IDE para utilizarlo.

    3. (OPCIONAL) Escriba una nueva versión (la 1.1) del framework algo más moderna, dibuje la pantalla utilizando la librería jline consoleui (https://jline.org/docs/modules/console-ui) o
    Lanterna (https://github.com/mabe02/lanterna).
    Tener en cuenta que sus clientes deben poder migrar a la nueva versión del framework sin necesidad de modificar sus clases (de forma totalmente transparente).
    
    Genere un nuevo jar framework-v1.1.jar y utilícelo ahora en el proyecto cliente generado en el punto anterior.
    Observe como cambiando de un jar al otro, cambia la UI dibujada por el framework sin cambiar código del cliente del framework.

    4. (OPCIONAL) Escriba una nueva versión (la 1.2), que soporte configurarlo en formato json del tipo:
    {
       "acciones": ["{paquete}.AccionUno", "{paquete}.AccionUno", "{paquete}.AccionXXX"]
    }

    Tener en cuenta que sus clientes deben poder migrar a la nueva versión del framework sin necesidad de modificar sus clases (de forma totalmente transparente),
    ni archivos de configuración, ambos formatos serán soportados por el framework.
    Genere un nuevo jar framework-v1.2.jar y utilícelo ahora en el proyecto cliente generado en el punto anterior.

    5. (OPCIONAL) Escriba una nueva versión del framework (1.3) que permita ejecutar varias acciones en forma concurrente.
    Ahora el menú debe permitir seleccionar 1 o varias tareas, para luego lanzarlas.
    Las tareas se ejecutarán en forma concurrente en N threads, donde N es el máximo número de threads permitidos para ser lanzados concurrentemente.
    Éste valor es un nuevo ítem de configuración del framework.
    Esta nueva opción de configuración se incluirá solo en la versión de configuración de tipo json, de la siguiente forma:
    {
       "acciones": ["{paquete}.AccionUno", "{paquete}.AccionUno", "{paquete}.AccionXXX"],
       "max-threads": N
    }
    Nota: Puede utilizar la utilidad que viene en la JDK llamada ExecutorService, que permite lanzar procesos en forma concurrente.