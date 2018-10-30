package com.example.joseluis.holamundo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;


/**
 * Hay dos formas de hacer que una vista reaccione a un click, una es que la actividad implemente
 * {@link View.OnClickListener} y la otra es que a cada botón le pongas click listener y ahí definas
 * qué hacer. En términos prácticos es cuestión de preferencias, por lo general es un poco más claro
 * el código si haces lo segundo, pero con lo primero agrupas todas las acciones en un sólo lugar y
 * son más fáciles de ver.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* Estas son varibles pero como son digamos "globales" para la clase no se llama variable sino
    miembro (*emoji solecito*) de la clase. Si el tipo de dato (tipo, de ahora en adelante) es otra
    clase que hace algo más que contener datos se le llama "dependencia", porque es otro objecto que
    necesita este para funcionar bien y hacer lo que debe. En este caso sí le podemos llamar variable
    y no dependencia porque son referencias a vistas dentro de esta pantalla. Alguien puede alegar
    que bajo ese argumento también se le puede llamar dependencia, pero no, porque las dependencias
    son mucho más funcionales y auxiliares.

    Recuerda que una clase sólo tiene dos cosas: miembros o variables y acciones o métodos. Los métodos
    llevan paréntesis y los miembros no. Hay otro modificador de miembros y métodos que es su "visibilidad"
    y hay 4 tipos; public, protected, default (no se escribe nada), y private. Eso para despúes, sólo
    tenlo en mente. Los miembros se declaran como (recuerda que aquí las mayúsculas importan mucho):

    Cuando veas esto [] significa que es algo opcional

    [visibilidad] Tipo nombre;         // Esta variable va a ser null hasta que le asignes algo
    [visibilidad] Tipo nombre = new Tipo();  // Llama al constructor sin argumentos para inicializarlo

    Si el dato no lleva mayúscula, como algunos números, los booleanos, y las palabras (String es la excepción)
    se inicializan así:
    [visibilidad] tipo nombre = [valor];

    En cambio, los método se definen así:
    [visibilidad] [tipo de dato que regresa] nombre([argumentos]) {

    }

    Como puedes ver es muy diferente. Ahora, una cosa es decir quë vas a hacer (o tu IMPLEMENTACIÓN)
    que es eso de arriba, y otra es ya hacerlo. Cuando quieres hacer algo que hace un método, lo ejecutas así:

    nombre([argumentos]);


    Poco a poco y practicando vas a ir pudiendo entender el flujo de los programas

     */
    ImageView avioncito;
    Float anchuraDePantalla = 0f;
    Float alturaContenedor = 0f;
    FrameLayout contenedorAvion;
    private static final int equisde = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenido_dibujitos);

        // Checa la firma de la función findViewById(). Es:
        // public <T extends View> View findViewById(int id)
        // Cuando veas los caracteres < y > se está haciendo uso de otro truquito avanzado del compilador
        // que por ahorita no vamos a discutir, llamado Generics, o Type Arguments.
        // Pero puedes ver que la función acepta un número entero y regresa una Vista.
        findViewById(R.id.btn_girar).setOnClickListener(this);
        findViewById(R.id.btn_despegar).setOnClickListener(this);
        findViewById(R.id.btn_depesgar_acel).setOnClickListener(this);
        findViewById(R.id.btn_decir_cui).setOnClickListener(this);

//        findViewById(R.id.btn_depesgar_acel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                // Aquí no necesitamos hacer switch porque el OnClickListener es un objeto anónimo,
                // así se le llama en java cuando usas un new en una interfaz, que en teoría no puede
                // ser instanciada o usarse con un new, pero como es una interfaz que sólo tiene una
                // función, se le llama interfaz funcional y se le permite esa excepción.
//            }
//        });

        // Aquí asignamos el valor de la variable. Si dejas una variable o dependencia sin inicializar,
        // o sea darle un valor y sobre todo que se guarde en memoria, vas a provocar un crash a la
        // hora de querer usarla. Los "errores" en java se llaman Excepciones. Este tipo de excepción
        // se llama NullPointerException, null es un valor parecido a void, pero en vez de significar
        // "nada" significa "no asignado", los pointers o punteros es un concepto que no necesitas aprender
        // por ahora pero son una referencia a la memoria. NullPointerException significa entonces que
        // estás tratando de usar una variable que no está en memoria. Si usas mi tema, los miembros
        // son de color como verdecito claro
        avioncito = findViewById(R.id.avioncito);

        contenedorAvion = findViewById(R.id.contenedor_avoincito);
    }

    private void empezarAnimacion(final Animacion animacion) {
        switch (animacion) {
            case GIRAR: {
                // va a girar dos veces, porque empieza en 0 grados y termina en 720, o sea 360 x 2
                // y va a durar 2 segundos, puede decirme por qué?
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 720f);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        // Aquí tenemos que hacer otro "truco" aunque se puede considerar como "falla"
                        // del compilador porque getAnimatedValue() regresa un Object, y lo tenemos
                        // que convertir a float, por eso esta sintaxis un poco rara:
                        avioncito.setRotation((float) animation.getAnimatedValue());
                    }
                });
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(equisde);
                animator.start();

                break;
            }

            case DESPEGAR: {
                ValueAnimator animator = ValueAnimator.ofFloat(0f, -anchuraDePantalla);
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(equisde);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        avioncito.setTranslationX((float)animation.getAnimatedValue());
                    }
                });

                // Con esto regresamos el avioncito a su posición original
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        avioncito.setTranslationX(0);
                    }
                });
                animator.start();

                break;
            }

            case DESPEGAR_ACELERANDO: {
                ValueAnimator izquierda = ValueAnimator.ofFloat(0f, -anchuraDePantalla);
                izquierda.setInterpolator(new AccelerateInterpolator(1.4f));
                izquierda.setDuration(equisde);
                izquierda.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        avioncito.setTranslationX((float)animation.getAnimatedValue());
                    }
                });

                // Con esto regresamos el avioncito a su posición original
                izquierda.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        avioncito.setTranslationX(0);
                    }
                });
                izquierda.start();

                ValueAnimator arriba = ValueAnimator.ofFloat(0f, -anchuraDePantalla);
                arriba.setInterpolator(new AccelerateInterpolator(2f));
                arriba.setDuration(equisde);
                arriba.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        avioncito.setTranslationY((float)animation.getAnimatedValue());
                    }
                });
                arriba.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        avioncito.setTranslationY(0);
                    }
                });

                arriba.start();
            }
        }
    }

    // Este es otro método del ciclo de vida que nos va a ayudar a saber cuánto espacio tenemos disponible
    // para animar el avioncito
    // Por ejemplo esto no lo sabía hacer, ni qué hace exactamente, pero toda la info sobre
    // animaciones lo saqué de aquí: https://www.raywenderlich.com/350-android-animation-tutorial-with-kotlin
    // sólo que está en Kotlin y pues es muy diferente el proyecto
    @Override
    protected void onResume() {
        super.onResume();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Otro casting
        anchuraDePantalla = (float) metrics.widthPixels;
        alturaContenedor = (float)contenedorAvion.getHeight();
    }

    /**
     * Como hicimos que la actividad implementara la interfaz OnClickListener, y hay muchos IDs que
     * no tienen click listener, tenemos que poder distinguir a cuál le aplicamos la acción.
     * D:
     * Khómo diablos?
     * Un claro ejemplo de código claro :P y bien escrito es la definición del click. Debes poderlo
     * leer como "cuando se hace click en una vista, o referencia de una vista llamada 'v', se hará lo siguiente, sin regresar nada"
     * Omite el "override" por ahora.
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
         *        regresa la función del click? Ah, int es una abreviación de "integer" o sea número entero
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
            case R.id.btn_despegar:
                // Y ahora la animación. Normalmente, si necesitas ejecutar algún código repetitivo
                // incluso con pequeñas variaciones debes pensar en que debes hacer un método que lo haga
                empezarAnimacion(Animacion.DESPEGAR);
                break;

            // Esta es otra sintaxis del case con la que te ahorras el break, no la conocía hasta hace poco
            // CORRECCION: no te ahorras el break pero te ayuda a ver más fácil el cuerpo del case.
            // Si no pones break se sigue ejecutando lo de abajo valiendole berga al compilador
            case R.id.btn_girar: {
                empezarAnimacion(Animacion.GIRAR);
                break;
            }

            case R.id.btn_decir_cui: {
                // Más sobre el ámbito de variables, si por alguna razón necesito usar otra variable
                // aquí, sólo va a existir en memoria en este bloque y no se puede usar en ninguna
                // otra parte
                String palabra = "Holi crayoli :3";
                int numeroEntero = 1;
                double numeroConDecimal = 3.1416;

                /* String.format es una función para encadenar palabras con algún formato. Cada
                %s
                es un argumento que va dentro de la palabra que quieres formatear y van separados por
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


                final MediaPlayer player = MediaPlayer.create(this, R.raw.cui);

                if (player.isPlaying()) {
                    player.stop();
                    player.release();
                }

                // Esto no es neceario, pero si no lo hacemos llenaríamos la memoria del cel y podemos
                // causar un crash
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        player.release();
                    }
                });

                // Esto pues sí es necesario :P
                player.start();

                break;
            }

            case R.id.btn_depesgar_acel: {
                empezarAnimacion(Animacion.DESPEGAR_ACELERANDO);
                break;
            }
        }
    }

    enum Animacion {
        GIRAR,
        DESPEGAR,
        DESPEGAR_ACELERANDO
    }
}
