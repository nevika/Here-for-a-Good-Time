package com.example.kory.donationtracker.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kory.donationtracker.Models.LocationClasses.Address;
import com.example.kory.donationtracker.Models.LocationClasses.Location;
import com.example.kory.donationtracker.Models.LocationClasses.LocationFacade;
import com.example.kory.donationtracker.Models.LocationClasses.LocationType;
import com.example.kory.donationtracker.Models.UserClasses.User;
import com.example.kory.donationtracker.Models.UserClasses.UserFacade;
import com.example.kory.donationtracker.Models.UserClasses.UserType;
import com.example.kory.donationtracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// importing LocationClasses package

public class Home extends AppCompatActivity implements OnItemSelectedListener {

    private ArrayList<String> dummy;
    private ArrayList<String> address = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDrawerLayout;
    private UserType ut;
    private ArrayList<String> myDataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        populateLocations();

        UserFacade facade = UserFacade.getInstance();
        User user = facade.getCurrentUser();
        String name = user.get_name();
        // TextView nameView = (TextView)findViewById(R.id.textView6);
        // nameView.setText(name);
        ut = user.get_type();
        loadMenus();

        ListView simpleList = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(v, position);
            }

        });


    }

    public void loadMenus(){
        if (ut.equals(UserType.EMPLOYEE)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.employee_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else if (ut.equals(UserType.MANAGER)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.manager_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else if (ut.equals(UserType.ADMIN)) {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.admin_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera4) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camer) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });
        } else {
            mDrawerLayout = findViewById(R.id.drawer_layout);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menuhome);

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.inflateMenu(R.menu.user_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            int id = menuItem.getItemId();
                            if (id == R.id.nav_camera1) {
                                backToHome(navigationView);
                            } else if (id == R.id.nav_camera) {
                                reloadHome(navigationView);
                            }

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here

                            return true;
                        }
                    });

        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void populateLocations() {
        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            LocationFacade locFacade = LocationFacade.getInstance();
            String line;
            String[] tokens = new String[10];
            br.readLine(); //get rid of header line

            while ((line = br.readLine()) != null) {
                //line = br.readLine();
                tokens = line.split(",");
                String name = tokens[1];
                String lat = tokens[2];
                String lon = tokens[3];
                // address
                String street = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                String zip = tokens[7];
                Address address = new Address(street, city, state, zip);
                String type = tokens[8];
                String phone = tokens[9];
                String website = tokens[10];

                Location location = new Location(name, lat, lon, address, type, phone, website);
                locFacade.addLocation(location);
            }
            br.close();



        } catch (IOException e) {
            System.out.println("Error");
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void loadLocation(View view, int pos) {
        setContentView(R.layout.activity_select_location);
        loadMenus();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> temp = (ArrayList) locFacade.getList();
        System.out.println(temp);
        Location location = temp.get(pos);
        TextView tv0 = findViewById(R.id.name);
        TextView tv1 = findViewById(R.id.latitude);
        TextView tv2 = findViewById(R.id.longitude);
        TextView tv3 = findViewById(R.id.address);
        TextView tv4 = findViewById(R.id.type);

        tv0.setText(location.getName());
        tv1.setText(location.getLat());
        tv2.setText(location.getLon());
        tv3.setText(location.getAddress().toString());
        tv4.setText(location.getType().getStringType());
    }
    public void backToHome(View view) {
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        LocationFacade locFacade = LocationFacade.getInstance();
        locFacade = new LocationFacade();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void reloadHome(View view) {
        // logs the current user out of the system
//        Intent randomIntent = new Intent(this, Home.class);
//        startActivity(randomIntent);

        setContentView(R.layout.activity_home);
        loadMenus();
        ListView simpleList = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long id){
                // Intent intent;
                loadLocation(v, position);
            }

        });
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        String a = (String) parent.getItemAtPosition(pos);
        //changeTheView(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        // logs the current user out of the system
        UserFacade facade = UserFacade.getInstance();
        facade.logout();

        Intent randomIntent = new Intent(this, StartUp.class);
        startActivity(randomIntent);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void populateSpinner() {
        ArrayList<String> temp = new ArrayList<>();
        LocationFacade locFacade = LocationFacade.getInstance();
        ArrayList<Location> locations = (ArrayList) locFacade.getList();

        for (int i = 0; i < locations.size(); i++) {
            Location l = locations.get(i);
            String name = l.getName();
            Address address1 = l.getAddress();
            String address = address1.toString();
            LocationType type1 = l.getType();
            String type = type1.getStringType();
        }

    }

//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void changeTheView(int pos) {
//        ArrayList<String> temp = new ArrayList<>();
//        try {
//            //Open a stream on the raw file
//            InputStream is = getResources().openRawResource(R.raw.locationdata);
//            //From here we probably should call a model method and pass the InputStream
//            //Wrap it in a BufferedReader so that we get the readLine() method
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//
//            String line;
//            String[] tokens = new String[10];
//            br.readLine(); //get rid of header line
//            for (int i = 0; i < pos + 1; i++) {
//                line = br.readLine();
//                tokens = line.split(",");
//            }
//            TextView tv = findViewById(R.id.textView3);
//            tv.setText(tokens[4]);
//
//            br.close();
//
//
//
//        } catch (IOException e) {
//            System.out.println("Error");
//        }
//    }
//
//
}


