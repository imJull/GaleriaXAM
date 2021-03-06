package net.unadeca.galeria.Activities.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.Case;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.galeria.Activities.database.models.Imagenes;
import net.unadeca.galeria.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        view = findViewById(R.id.coordinador);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testBaseDatos();
                establecerAdaptador();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        establecerAdaptador();
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

    private void testBaseDatos() {
        Delete.table(Imagenes.class);
        Imagenes imagen;
        for (int a = 0; a < 6; a++) {
            imagen = new Imagenes();
            if (a <= 0){
            imagen.imagen = "https://i.pinimg.com/originals/75/00/30/7500302b182070761e3ac8269a8c4443.jpg";
            imagen.titulo = "AntiHero";}
            else if (a == 1){
            imagen.imagen = "https://steamuserimages-a.akamaihd.net/ugc/785162250415551722/518EEB89F420FCB94D620D6E500C127D482B878B/";
            imagen.titulo = "Santa Cruz";}
            else if (a == 2){
                imagen.imagen ="https://sohimages.com/images/images_soh/wrldindsflameboy1-2.jpg";
                imagen.titulo = "World Industries";}
                else if ( a == 3){
                    imagen.imagen = "https://acclaimmag.com/wp-content/uploads/2012/07/163801_500_375.jpeg";
                    imagen.titulo = "Girl Skateboards";}
                    else {imagen.imagen = "findViewById(R.drawable.ic_add_a_photo_black_24dp)";
                    imagen.titulo = "Desconocido";}


            imagen.descripcion = "Empresa top en la industria "; //+ (a + 1);
            //imagen.titulo = "AntiHero " + (a + 1);
            imagen.save();


        }
    }

    //Adaptador
    private void establecerAdaptador() {
        lista.setAdapter(new CustomAdapterRecycler(getImagenes(), this, view));
    }

    //Lista de imagenes
    private List<Imagenes> getImagenes() {
        return SQLite.select().from(Imagenes.class).queryList();
    }
}
