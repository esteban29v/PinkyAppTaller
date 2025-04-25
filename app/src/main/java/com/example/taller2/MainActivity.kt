package com.example.taller2;

import android.content.Context
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity: AppCompatActivity() {

    private lateinit var navController: NavController;
    private lateinit var appBarConfiguration:AppBarConfiguration;
    private lateinit var toolbar:androidx.appcompat.widget.Toolbar;
    private lateinit var drawerLayout:androidx.drawerlayout.widget.DrawerLayout;
    private lateinit var drawerToggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("MisDatos", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("nombre", "Juan Esteban Velez")
            putInt("edad", 23)
            putString("correo", "jevelez@ucompensar.edu.co")
            putString("programa", "Ingeniería de Software")
            putInt("semestre", 7)
            apply()
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        /* Configuracion de la barra de herramientas */
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /* Navegacion de los fragments */
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment?.findNavController()!!

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.misDatosFragment,
                R.id.analisisFragment,
                R.id.HistoricoFragment,
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController. navigateUp() || super. onSupportNavigateUp()

    }

}