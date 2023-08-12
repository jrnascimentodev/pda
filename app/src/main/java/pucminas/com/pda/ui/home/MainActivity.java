package pucminas.com.pda.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import pucminas.com.pda.R;
import pucminas.com.pda.databinding.ActivityMainBinding;
import pucminas.com.pda.domain.Operador;
import pucminas.com.pda.ui.consultapreco.ConsultaPrecoActivity;
import pucminas.com.pda.ui.inventario.InventarioActivity;
import pucminas.com.pda.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private Operador operador;
    private TextView tOperadorLogado;
    private CardView btConsultaPreco, btInventario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        btConsultaPreco = findViewById(R.id.btConsultaPreco);
        btInventario = findViewById(R.id.btInventario);

        View view = navigationView.getHeaderView(0);
        tOperadorLogado = view.findViewById(R.id.tOperadorLogado);

        operador = (Operador) getIntent().getSerializableExtra("operador");

        if (operador != null)
        {
            tOperadorLogado.setText("Bem-Vindo, " + operador.getNome());
        }

        btConsultaPreco.setOnClickListener(view1 ->
        {
            Intent i = new Intent(MainActivity.this, ConsultaPrecoActivity.class);
            startActivity(i);
        });

        btInventario.setOnClickListener(view12 ->
        {
            Intent i = new Intent(MainActivity.this, InventarioActivity.class);
            startActivity(i);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_consulta_preco)
        {
            Intent i = new Intent(MainActivity.this, ConsultaPrecoActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_inventario)
        {
            Intent i = new Intent(MainActivity.this, InventarioActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_logout)
        {
            Sair();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void Sair()
    {
      supportFinishAfterTransition();
    }
}