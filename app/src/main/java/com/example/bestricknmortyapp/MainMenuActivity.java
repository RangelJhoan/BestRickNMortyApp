package com.example.bestricknmortyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bestricknmortyapp.databinding.ActivityMainMenuBinding;
import com.example.bestricknmortyapp.iu.CharacterFragment;
import com.example.bestricknmortyapp.iu.EpisodeFragment;
import com.example.bestricknmortyapp.iu.LocationFragment;

public class MainMenuActivity extends AppCompatActivity {

    private ActivityMainMenuBinding binding;
    private ActionBarDrawerToggle toggle;

    @SuppressLint({"RestrictedApi", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toggle = new ActionBarDrawerToggle(this, binding.dlLayout, R.string.open_drawer, R.string.close_drawer);
        binding.dlLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.nvMenu.setNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.nav_characters: {
                            getSupportFragmentManager().beginTransaction().replace(binding.fcvContainer.getId(), new CharacterFragment()).commit();
                        }
                        break;
                        case R.id.nav_episodes: {
                            getSupportFragmentManager().beginTransaction().replace(binding.fcvContainer.getId(), new EpisodeFragment()).commit();
                        }
                        break;
                        case R.id.nav_locations: {
                            getSupportFragmentManager().beginTransaction().replace(binding.fcvContainer.getId(), new LocationFragment()).commit();
                        }
                    }

                    binding.dlLayout.closeDrawer(GravityCompat.START);

                    return true;
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}