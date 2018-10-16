package misprimerosnumeros.migueloliver.com.misprimerosnumeritos;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by Miguel on 08/05/2015.
 * Clase encargada del aprendizaje de los numeros
 */
public class AprendeNumeros extends Matematicas {

    private TextView mensaje;
    private ImageView picture;
    //Encargado de reproducir los sonidos
    private MediaPlayer mp;
    private ImageButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btv;
    private int numero, auxNum;
    //Intersticial
    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aprende_numeros);

        //Para cambiar el tipo del letra del TextView
        Typeface miTipo = Typeface.createFromAsset(getAssets(),"fonts/Jazz_Ball_Regular.ttf");
        mensaje = (TextView) findViewById(R.id.texto);
        mensaje.setTypeface(miTipo);

        picture = (ImageView) findViewById(R.id.animales);
        bt1=(ImageButton)findViewById(R.id.uno);
        bt2=(ImageButton)findViewById(R.id.dos);
        bt3=(ImageButton)findViewById(R.id.tres);
        bt4=(ImageButton)findViewById(R.id.cuatro);
        bt5=(ImageButton)findViewById(R.id.cinco);
        bt6=(ImageButton)findViewById(R.id.seis);
        bt7=(ImageButton)findViewById(R.id.siete);
        bt8=(ImageButton)findViewById(R.id.ocho);
        bt9=(ImageButton)findViewById(R.id.nueve);
        btv=(ImageButton)findViewById(R.id.volver);

        this.numero = this.random();
        this.imagenAnimal(this.numero, this.picture);

        //Publicidad(Banner)
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // Todos los emuladores
                .build();
            // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        //Publicidad (Intersticial)
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intersticial_ad_unit_id));
        adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        //requestNewInterstitial();

        /*
         * Listener de los bonones
         */
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uno();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dos();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tres();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cuatro();
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cinco();
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                seis();
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                siete();
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ocho();
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nueve();
            }
        });

        btv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (randomMenor() == 3) {
                    if (mInterstitialAd != null && mInterstitialAd.isLoaded())
                        mInterstitialAd.show();
                    else
                        volver();
                }
                else
                    volver();
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                volver();

            }
        });

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
     * Meotodo que implementa lo que debe de hacer el OnClik del boton volver
     *
     */
    public void volver() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    /**
     * Metodo que se encarga de controlar si el numero uno es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el uno no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void uno() {

        if(this.numero == 1) {
            mp = MediaPlayer.create(this, R.raw.uno);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();
            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero dos es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el dos no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void dos() {

        if(this.numero == 2) {
            mp = MediaPlayer.create(this, R.raw.dos);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
           // mp = MediaPlayer.create(this, R.raw.sonidoanimal);
           // mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero tres es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el tres no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void tres() {

        if(this.numero == 3) {
            mp = MediaPlayer.create(this, R.raw.tres);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero cuatro es el correcto, si lo es reproduce el sonido del nmero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el cuatro no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void cuatro() {

        if(this.numero == 4) {
            mp = MediaPlayer.create(this, R.raw.cuatro);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();
            SystemClock.sleep(1000);

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero cinco es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el cinco no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void cinco() {

        if(this.numero == 5) {

            mp = MediaPlayer.create(this, R.raw.cinco);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);

            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero seis es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el seis no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void seis() {

        if(this.numero == 6) {
            mp = MediaPlayer.create(this, R.raw.seis);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero siete es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el siete no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void siete() {

        if(this.numero == 7) {
            mp = MediaPlayer.create(this, R.raw.siete);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero ocho es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el ocho no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void ocho() {

        if(this.numero == 8) {
            mp = MediaPlayer.create(this, R.raw.ocho);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }

    /**
     * Metodo que se encarga de controlar si el numero nueve es el correcto, si lo es reproduce el sonido del numero, luego el sonido
     * de bien, luego pasa el numero a una variable auxiliar y crea uno nuevo, esto se hace para controlar que el numero no se
     * repite, una vez hecho esto llama a imagenAnimal, pasandole el nuevo numero y el ImagenView, que se encarga de modificar la
     * imagen del animal. Si el nueve no es el correcto se llama a incorrecto pasandole el MediaPlayer.
     */
    public void nueve() {

        if(this.numero == 9) {
            mp = MediaPlayer.create(this, R.raw.nueve);
            mp.start();
            SystemClock.sleep(1000);
            mp = MediaPlayer.create(this, R.raw.bien);
            mp.start();
            SystemClock.sleep(1000);
            //mp = MediaPlayer.create(this, R.raw.sonidoanimal);
            //mp.start();

            this.auxNum = numero;
            this.numero = this.random();
            while(this.auxNum == this.numero)
                this.numero = this.random();
            this.imagenAnimal(this.numero, this.picture);
        }
        else {
            this.incorrecto(mp);
        }
    }
}
