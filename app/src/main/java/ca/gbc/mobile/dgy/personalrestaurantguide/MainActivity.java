package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/

public class MainActivity extends Activity implements Button.OnClickListener{

    MyDBHandler myDBHandler;
    Restaurant_list restaurant_list=new Restaurant_list();
    List<Restaurant> listviewer=new ArrayList<Restaurant>();
    ListView listView;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);

        //Setup Database
       try {
           myDBHandler = new MyDBHandler(this);
           myDBHandler.getWritableDatabase();
       }catch(Exception e)
        {
            e.printStackTrace();
        }

        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        restaurants=restaurant_list.getlist();
          if(myDBHandler.default_not_exist()) {
            try {
                for (Restaurant r : restaurants) {
                    myDBHandler.createRestaurant(r);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        listView=(ListView)findViewById(R.id.list);
        create=(Button)findViewById(R.id.button);
        //ListView data in default
        listviewer=myDBHandler.getTop10Restaurants();
        MyAdapter adapter=new MyAdapter(this,listviewer.toArray(new Restaurant[]{}));
        listView.setAdapter(adapter);

        //onitemclicklistener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant restaurant = listviewer.get(i);
                 int id=restaurant.getRest_id();

                Intent intent = new Intent(getBaseContext(), RestaurantActivity.class);
                intent.putExtra("_id", String.valueOf(id));
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });
        create.setOnClickListener(this);
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
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("About");
            alertDialog.setMessage("Group Member:\nYafan Zhang\n" +
                    "David Olano\nGary Chan");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int curid = v.getId();
        switch (curid) {
            case R.id.button:
                Intent i= new Intent(this, EditActivity.class);
                startActivity(i);
                //this.finish();
                break;
        }

    }
}
