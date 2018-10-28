package com.example.joseluis.holamundo;

import android.util.Log;

/**
 * Hice este pequeño ejemplo para que se terminen de aclarar algunas dudas que creo que sigue teniendo
 * de las clases. Sé que lo que dije suena muy cumplicado, pero en realidad no lo es, nada más que es
 * paso a paso. No vamos a usar la clase ClaseYHerencias para nada. Es sólo para que no haya errores
 * del compilador :P
 */
public class ClasesYHerencia {

    abstract class Sonido {

        /**
         * Esta sintaxis, donde es la clase, seguido de paréntesis, como si ejecutaras a una función,
         * pero estás como ejectuando a la clase, se llama constructor y aunque no lo pongas todas
         * las clases tienen uno por default. Es lo que se ejecuta cuando se usa la clase por primera
         * vez no por tí sino por el sistema y pone datos en la memoria. La distingues en mi estilo
         * porque es blanca. Podemos hacer muchos, siempre y cuando lor argumentos, lo que va entre
         * paréntesis, sean diferentes. El que se crea por default no tiene ningún argumento.
         */
        Sonido() { }

        Sonido (int frecuencia) {
            // Aquí qué hacemos? Un poquito de lógica: un sonido no puede tener una frecuencia de cero
            // o negativa, entonces si alguien hace algo mal cuando quiere un sonido ponemos una frecuencia de 1.
            // El  <  así solito significa menor que, el  >  significa mayor que, y puedes también
            // usar   >=   o   <=   que significan mayor o igual que y menor o igual que.
            // Las 4 líneas de abajo dicen: si la frecuencia que viene como argumento es menor que cero,
            // la frecuencia de esa instancia de la clase será uno, si no, será igual al argumento.
            if (frecuencia < 0) {
                this.frecuencia = 1;
            } else {
                this.frecuencia = frecuencia;
            }
        }

        int frecuencia;

        abstract void sonar();
    }


    class Sol extends Sonido {

        Sol() {
            this.frecuencia = 392;
        }

        @Override
        void sonar() {
            // Aquí de alguna forma haríamos sonar la nota usando su frecuencia, algo así como:
            // Multimedia.makeSound(frecuencia);
            // La clase Multimedia no existe así que por eso lo pongo comentado. Si fuera java normal
            // habría una forma, si es Android es otra. Esa es la ventaja de un método abstracto,
            // puedes cambiar la forma en la que funciona o lo que hace adecuado a tus ciunecesidades
        }
    }

    class Re extends Sonido {

        Re() {
            this.frecuencia = 329;
        }

        @Override
        void sonar() {
            // Si no ponemos nada no se ejecuta nada, pero si borramos el método el programa no corre
        }
    }

    class ProgramaQueTocaSonido {
        Sonido sol = new Sol();
        Sonido re = new Re();
        Sonido otroSol = new Sol();

        ProgramaQueTocaSonido() {
            sol.sonar();
            re.sonar();
            otroSol.sonar();
        }
    }

    /**
     * A estas alturas se puede correr ese programita simple que toca un Sol luego un Re y luego otro Sol
     * simplemente con llamar a su constructor. Los constructores también son métodos internamente,
     * pero como ves sale gris porque hasta ahorita no lo hemos usado en ninguna parte. Descomenta
     * el código de abajo para que te des una idea de cómo podríamos ejecutar ese código EN ANDROID,
     * java puro tiene otra forma. No trates de correr esto xD no va a salir nada que se le parezca
     */

//    class ActividadSonido extends Activity {
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//
//            new ProgramaQueTocaSonido();
//        }
//    }


    /**
     * Trate de seguir la línea de pensamiento aunque viene un giro :P
     */

    /**
     * Una interfaz es lo mismo que un método abstracto, como el de {@link Sonido#sonar()}
     */
    interface Presentarse {
        void decirHoli();
    }

    class Animalito extends Sonido implements Presentarse {

        String nombre;
        int edadEnMeses;

        Animalito(String nombre, int edad) {
            this.nombre = nombre;
            this.edadEnMeses = edad;
        }

        Animalito(String nombre, int edad, int otraFrecuencia) {
            this.nombre = nombre;
            this.edadEnMeses = edad;
            this.frecuencia = otraFrecuencia;
        }

        @Override
        public void decirHoli() {
            Log.i("Ejemplo", String.format("Holi, soy %s y tengo %s meses", nombre, edadEnMeses));
            sonar();
        }

        @Override
        void sonar() {
            Log.i("Ejemplo", String.format("Y cuando chillo mi frecuencia es %s", frecuencia));
        }
    }

    /**
     *
     * Con eso de arriba, puedes hacer algo como:                                               */

    Animalito cuicui = new Animalito("Fatikulz", 3, 999);

    /**
     * Crearía una variable llamada cuicui que se llama Fatikulz, que tiene tres meses de edad, y cuando chilla lo hace a 999Hz
     */
}
