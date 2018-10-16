package misprimerosnumeros.migueloliver.com.misprimerosnumeritos;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Miguel on 11/05/2015.
 * Clase padre de las clase AprendeNumeros, Sumas y PruebaSumas.
 * Basicamente esta clase implementa las funcionalidades comunes que tienen las clases anteriores.
 */
public class Matematicas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo encargado de generar un numero entre el uno y el nueve de forma aleatoria
     * @return numero aleatorio
     */
    public int random() {

        Random random = new Random();
       return((random.nextInt(9)) + 1);
    }

    /**
     * Metodo encargado de generar un numero entre el uno y el tres de forma aleatoria
     * @return numero aleatorio
     */
    public int randomMenor() {

        Random random = new Random();
        return((random.nextInt(3)) + 1);
    }

    /**
     * Metodo encargado de sumar los dos numero que le vienen como parametro y devolver el resultado de la suma
     * @param a
     * @param b
     * @return a+b
     */
    public int suma(int a, int b) {

        return a + b;
    }

    /**
     * Metodo encargado de dibujar una imagen en el ImagenView que le viene como parametro, dicha imagen contendra un
     * numero de animales igual al valor del entero que recibe. Dentro del metodo se genera un entero, llamando al metodo
     * randomMenor, entre el uno y el tres, este numero se usara para variar la imagen y que en un numero no siempre
     * aparezca la misma
     * @param num
     * @param picture
     */
    public void imagenAnimal(int num, ImageView picture) {

        int animal;

        animal = randomMenor();

        switch (num) {
            case 1:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_01); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_01); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_01); break;
                } break;
            case 2:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_02); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_02); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_02); break;
                } break;
            case 3:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_03); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_03); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_03); break;
                } break;
            case 4:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_04); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_04); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_04); break;
                } break;
            case 5:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_05); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_05); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_05); break;
                } break;
            case 6:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_06); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_06); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_06); break;
                } break;
            case 7:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_07); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_07); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_07); break;
                } break;
            case 8:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_08); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_08); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_08); break;
                } break;
            case 9:
                switch (animal) {
                    case 1:
                        picture.setImageResource(R.drawable.gato_09); break;
                    case 2:
                        picture.setImageResource(R.drawable.caracol_09); break;
                    case 3:
                        picture.setImageResource(R.drawable.mariquita_09); break;
                } break;
        }
    }

    /**
     * Metodo encargado de reproducir el sonido de ohh cuando un Jugador no responde de forma correcta
     * @param mp
     */
    protected void incorrecto(MediaPlayer mp) {

        mp = MediaPlayer.create(this, R.raw.ooh);
        mp.start();
    }

    /**
     * metodo que recibe un entero y dos ImagenView, con esto segun el entero que sea modificara las imagenes de los
     * ImagenView el primero es el numero que se mostrara, y el segundo es la mano con dedos levantados igual al
     * numero
     * @param num
     * @param numero
     * @param mano
     */
    public void escogenumero(int num, ImageView numero, ImageView mano) {
        switch(num) {
            case 1:
                numero.setImageResource(R.drawable.numero1);
                mano.setImageResource(R.drawable.mano_1);
                break;
            case 2:
                numero.setImageResource(R.drawable.numero2);
                mano.setImageResource(R.drawable.mano_2);
                break;
            case 3:
                numero.setImageResource(R.drawable.numero3);
                mano.setImageResource(R.drawable.mano_3);
                break;
            case 4:
                numero.setImageResource(R.drawable.numero4);
                mano.setImageResource(R.drawable.mano_4);
                break;
            case 5:
                numero.setImageResource(R.drawable.numero5);
                mano.setImageResource(R.drawable.mano_5);
                break;
        }
    }
}
