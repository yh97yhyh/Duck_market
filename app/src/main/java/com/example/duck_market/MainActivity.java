package com.example.duck_market;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    TextView usernametv;

    //public static String User_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent intent = getIntent(); /*데이터 수신*/

        // String User_name = intent.getExtras().getString("UserName");
        //Log.d("username",User_name);

        /*
        Bundle bundle=new Bundle();
        bundle.putString("Username",User_name);
        //WriteFragement fragment=new WriteFragement();
        //fragment.setArguments(bundle);
        WriteFragement fragInfo=new WriteFragement();
        fragInfo.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.layout.fragment_write,fragInfo);
        fragmentTransaction.commit();
        */

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_write, R.id.navigation_myshop, R.id.navigation_category)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}


