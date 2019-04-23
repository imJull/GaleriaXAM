package net.unadeca.galeria.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.galeria.Activities.database.models.Imagenes;
import net.unadeca.galeria.Activities.database.models.Imagenes_Table;
import net.unadeca.galeria.R;

public class DetalleActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private ViewPager viewPager;
    private Imagenes  mostrar;
    private ImageView imagen;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleimagen);

        coordinatorLayout = findViewById(R.id.coordinador);
        viewPager = findViewById(R.id.viewpager);
        imagen = findViewById(R.id.imagen);


        if(getIntent().hasExtra("imagen")){
            mostrar = SQLite.select().from(Imagenes.class).where(Imagenes_Table.id.eq(getIntent().getExtras().getLong("imagen"))).querySingle();
            Glide.with(this).load(mostrar.imagen).error(Glide.with(this).load(R.drawable.ic_add_a_photo_black_24dp)).centerCrop().into(imagen);

        }
    }
}
