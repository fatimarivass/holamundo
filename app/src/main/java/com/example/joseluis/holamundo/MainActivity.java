package com.example.joseluis.holamundo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


/**
 * Hay dos formas de hacer que una vista reaccione a un click, una es que la actividad implemente
 * {@link View.OnClickListener} y la otra es que a cada botón le pongas click listener y ahí definas
 * qué hacer. En términos prácticos es cuestión de preferencias, por lo general es un poco más claro
 * el código si haces lo segudo, pero con lo primero agrupas todas las acciones en un sólo lugar y
 * son más fáciles de ver.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenido_dibujitos);

        // Aquí decimos que el click listener es la actividad, o sea 'this' porque así se llama la clase
        findViewById(R.id.btn_anim_1).setOnClickListener(this);
        findViewById(R.id.btn_anim_2).setOnClickListener(this);

        findViewById(R.id.btn_anim_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí no necesitamos hacer switch porque el OnClickListener es un objeto anónimo,
                // así se le llama en java cuando usas un new en una interfaz, que en teoría no puede
                // ser instanciada o usarse con un new, pero como es una interfaz que sólo tiene una
                // función, se le llama interfaz funcional y se le permite esa excepción.
            }
        });
    }

    /**
     * Como hicimos que la actividad implementara la interfaz OnClickListener, y hay muchos IDs que
     * no tienen click listener, tenemos que poder distinguir a cuál le aplicamos la acción.
     * D:
     * Khómo diablos?
     */
    @Override
    public void onClick(View v) {
        /* switch es una palabra reservada que funciona precisamente como lo que suena. Lo que va
         * dentro de los primeros paréntesis en una función se llama argumento y en este caso el
         * tipo de argumento es un número entero. Si aprietas Ctrl y pones el mouse sobre .getId()
         * vas a ver la "definición" o "firma" de la función, la cual es pública y regresa un número
         * entero, que es lo que necesita el switch para funcionar. ¿Cómo sé que regresa un entero?
         * Porque dice:
         * public int getId()
         *        ^la segunda palabra de la funcion dice qué tipo de dato obtienes, o regresa
         *        No todas las funciones regresan algo, a veces sólo tienes que hacer una acción,
         *        en ese caso regresas 'void' que significa literalmente nada. ¿Ya te fijaste qué
         *        regresa la función del click? Ah, int es una abrevación de "integer" o sea número entero
         */
        switch (v.getId()) {
            /* ¿Por qué v.getId()? No está muy claro. ¿Qué diablos es "v"? Cuando escribes código debes
            tener cuidado de ser expresiv@ porque cuando funciona lo dejas y después a fuerza vas a
            tener que regresar a arreglar algo y aunque tú mism@ escribiste las cosas, a veces se te
            olvida qué o cómo funciona lo que hiciste. Como ya viste es demasiado escribir. Vamos a
            dejarlo así por esta vez, pero lo importante es que aprendas un concepto que se llama
            ámbito de variable. Es simplemente cuánto tiempo "existe". Las variables ocupan memoria
            y hay que liberarla cada que sea posible. Las varibles en java existen dentro de los
            {   y   }
            Si cambias la v en la linea que define la implementación del método o sea donde dice
            public void onClick(View v) {
            y le pones view, o vista, o algo así es mejor, pero debes cambiar también todas las otras
            líneas donde uses sólo v o el programa no va a compilar porque la referecia a la letra
            v no está definida.
            La referencia de v es un tipo de dato, o clase, View, o Vista.

            Ahora sí, a lo que nos truje, que es ya una sintaxis que sí debes memorizar un poco
             */

            // Literalmente dices "en caso de que sea este ID, haz lo que sigue hasta donde dice break;
            case R.id.btn_anim_1:
                // Más sobre el ámbito de variables, si por alguna razón necesito usar otra variable
                // aquí, sólo va a existir en memoria en este bloque y no se puede usar en ninguna
                // otra parte
                String palabra = "Holi crayoli :3";
                int numeroEntero = 1;
                double numeroConDecimal = 3.1416;

                /* String.format es una función para encadenar palabras con algún formato. Cada
                %s
                es un argumento que va después de la palabra que quieres formatear y van separados por
                comas, el hecho de que puedas hacer una suma dentro de otro argumento es algo así
                como un truquito del compilador que su mente inocente debe aprender a aprenciar, como
                ese hay muchos.
                 */
                String elMensaje = String.format("%s la suma de Pi y %s es %s", palabra, numeroEntero, numeroConDecimal + numeroEntero);

                // Por qué la linea de abajo puede terminar sin punto y coma sin error? Porque
                // está "encadenada" a la siguiente línea que también es un método. Sólo se puede
                // omitir el punto y coma cuando encadenas funciones. El punto y coma no es necesario
                // en Kotlin como espero haya podido oczerbar porque se reemplaza por el enter o
                // "salto de línea", espero haya escuchado el término en alguna de sus clases.
                //
                Toast.makeText(this, elMensaje, Toast.LENGTH_SHORT)
                        .show();

                // Y ahora la animación


                break;

            // Esta es otra sintaxis del case con la que te ahorras el break, no la conocía hasta hace poco
            case R.id.btn_anim_2: {

            }
        }
    }
}
