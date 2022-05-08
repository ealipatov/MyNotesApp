package by.ealipatov.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import by.ealipatov.mynotesapp.ui.AboutFragment;
import by.ealipatov.mynotesapp.ui.NotesListFragment;
import by.ealipatov.mynotesapp.ui.SettingsFragment;
import by.ealipatov.mynotesapp.ui.ToolbarHolder;

public class MainActivity extends AppCompatActivity implements ToolbarHolder {

    private DrawerLayout drawerLayout;

    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount() == 0){

            new AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage(R.string.app_close)
                    .setIcon(R.drawable.ic_baseline_clear_24)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            finish();

                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), R.string.staying, Toast.LENGTH_SHORT).show();
                        }
                    }).show();

        } else {
            getSupportFragmentManager().popBackStack();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_notes_list:

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new NotesListFragment())
                                .commit();
                        drawerLayout.close();

                        return true;

                    case R.id.action_settings:
                        getSupportFragmentManager().popBackStack();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new SettingsFragment())
                                .addToBackStack("settings")
                                .commit();
                        drawerLayout.close();

                        return true;

                    case R.id.action_about:
                        getSupportFragmentManager().popBackStack();

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new AboutFragment())
                                .addToBackStack("about")
                                .commit();
                        drawerLayout.close();

                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }
}