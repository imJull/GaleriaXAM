package net.unadeca.galeria.Activities.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.galeria.Activities.database.models.Imagenes;
import net.unadeca.galeria.Activities.database.models.Imagenes_Table;
import net.unadeca.galeria.R;

public class DetalleActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private ViewPager viewPager;
    private static Imagenes  mostrar;
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

    public static class detalleFragment extends Fragment{
        public TextView titulo;
        public TextView description;
        public TextView comentarios;
        public TextInputLayout txtComentario;
        public Button agregar;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.detalle, container, false);
            titulo = rootView.findViewById(R.id.titulo);
            description = rootView.findViewById(R.id.descripcion);
            comentarios = rootView.findViewById(R.id.comentarios);
            txtComentario = rootView.findViewById(R.id.txtcomentario);
            agregar = rootView.findViewById(R.id.agregar);
            return rootView;
        }
    }
}
