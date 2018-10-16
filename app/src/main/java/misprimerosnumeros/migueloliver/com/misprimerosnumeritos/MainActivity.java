package misprimerosnumeros.migueloliver.com.misprimerosnumeritos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Publicidad
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // Todos los emuladores
                .build();
// Start loading the ad in the background.
        mAdView.loadAd(adRequest);

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
     * Metodo que implemeta lo que debe de hacer el OnClick del boton aprendeNumeros
     * @param v
     */
    public void aprendeNumeros(View v) {
        Intent i = new Intent(this, AprendeNumeros.class);
        startActivity(i);
        finish();
    }

    /**
     * Metodo que implemeta lo que debe de hacer el OnClick del boton Sumas
     * @param v
     */
    public void sumar(View v) {
        Intent i = new Intent(this, Sumas.class);
        startActivity(i);
        finish();

    }

    /**
     * Metodo que implemeta lo que debe de hacer el OnClick del boton pruebaSumas
     * @param v
     */
    public void PruebaSumas(View v) {
        Intent i = new Intent(this, PruebaSumas.class);
        startActivity(i);
        finish();

    }
}
