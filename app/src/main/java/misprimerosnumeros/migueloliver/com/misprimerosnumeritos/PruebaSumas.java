package misprimerosnumeros.migueloliver.com.misprimerosnumeritos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
 * Created by Miguel on 15/05/2015.
 * Clase encargadade la Prueba de sumas
 */
public class PruebaSumas extends Matematicas {
    private ImageView numero1, numero2, mano1, mano2, solucion;
    private TextView ok, ko, contador;
    private ImageButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btv;
    private int num1, num2, num1Aux, num2Aux, resultado, aciertos, fallos;

    //Encargado de reproducir los sonidos
    private MediaPlayer mp;

    //Contador
    CountDownTimer timer;

    //Ventana emergente
    AlertDialog.Builder dialogo;

    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_sumas);

        numero1 = (ImageView) findViewById(R.id.numero1);
        numero2 = (ImageView) findViewById(R.id.numero2);
        mano1 = (ImageView) findViewById(R.id.mano1);
        mano2 = (ImageView) findViewById(R.id.mano2);
        solucion = (ImageView) findViewById(R.id.solucion);

        ok = (TextView) findViewById(R.id.ok);
        ko = (TextView) findViewById(R.id.ko);
        contador = (TextView) findViewById(R.id.contador);

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

        num1 = this.random();
        while(num1 > 5)
            num1 = this.random();

        num2 = this.random();
        while(num2 > 5)
            num2 = this.random();

        aciertos = 0;
        fallos = 0;
        resultado = num1 + num2;
        while (resultado > 9) {
            num1 = this.random();
            resultado = num1 + num2;
        }

        this.escogenumero(this.num1, this.numero1, this.mano1);
        this.escogenumero(this.num2, this.numero2, this.mano2);

        dialogo = new AlertDialog.Builder(this);
        final Intent repetir = new Intent(this, PruebaSumas.class);
        final Intent volver = new Intent(this, MainActivity.class);

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

        // Codigo del contador
        timer = new CountDownTimer(90000, 1000) {
            public void onTick(long millisUntilFinished) {
                contador.setText(String.valueOf(millisUntilFinished/1000));

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
            public void onFinish() {
                dialogo.setTitle("Resultado");
                dialogo.setMessage("Has acertado: " + aciertos + "\n" + "Has fallado: " + fallos);
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Repetir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        startActivity(repetir);
                    }
                });
                dialogo.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo, int id) {
                        startActivity(volver);
                    }
                });
                dialogo.show();
            }
        };
        timer.start();

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
     */
    public void volver() {
        timer.cancel();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    /**
     * Metodo encargado  de reproducir el sonido cuando una respuesta ha sido correcta, luego incrementa en uno el contador de
     * operaciones correctas y modificamos el TextView de las operaciones correctas, despues de esto generamos unos nuevos numeros
     * para las operaciones y por ultimo modificamos las imagenes de la pantalla.
     */
    public void correcto() {
        mp = MediaPlayer.create(this, R.raw.bien);
        mp.start();

        this.aciertos++;
        this.ok.setText(Integer.toString(this.aciertos));

        num1Aux = num1;
        num1 = this.random();
        while(num1 > 5 || num1 == num1Aux)
            num1 = this.random();

        num2Aux = num2;
        num2 = this.random();
        while(num2 > 5 || num2 == num2Aux)
            num2 = this.random();

        resultado = num1 + num2;
        while (resultado > 9) {
            num1 = this.random();
            resultado = num1 + num2;
        }

        this.escogenumero(this.num1, this.numero1, this.mano1);
        this.escogenumero(this.num2, this.numero2, this.mano2);
    }

    /**
     * Metodo encargado de reproducir el sonido de fallo, una vez finalizado esto incrementa el contador de fallos y modifica
     * el TextView del contador de fallos
     */
    public void incorrecto() {
        mp = MediaPlayer.create(this, R.raw.oooh);
        mp.start();

        this.fallos++;
        this.ko.setText(Integer.toString(this.fallos));
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a uno, en caso incorrecto llama al metodo incorrecto.
     */
    public void uno() {

        if(this.resultado == 1) {
            this.correcto();

        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a dos, en caso incorrecto llama al metodo incorrecto.
     */
    public void dos() {

        if(this.resultado == 2) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a tres, en caso incorrecto llama al metodo incorrecto.
     */
    public void tres() {

        if(this.resultado == 3) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a cuatro, en caso incorrecto llama al metodo incorrecto.
     */
    public void cuatro() {

        if(this.resultado == 4) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a cinco, en caso incorrecto llama al metodo incorrecto.
     */
    public void cinco() {

        if(this.resultado == 5) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a seis, en caso incorrecto llama al metodo incorrecto.
     */
    public void seis() {

        if(this.resultado == 6) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a siete, en caso incorrecto llama al metodo incorrecto.
     */
    public void siete() {

        if(this.resultado == 7) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a ocho, en caso incorrecto llama al metodo incorrecto.
     */
    public void ocho() {

        if(this.resultado == 8) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }

    /**
     * Metodo que llama al metodo correcto si el resultado es igual a nueve, en caso incorrecto llama al metodo incorrecto.
     */
    public void nueve() {

        if(this.resultado == 9) {
            this.correcto();
        }
        else {
            this.incorrecto();
        }
    }
}
