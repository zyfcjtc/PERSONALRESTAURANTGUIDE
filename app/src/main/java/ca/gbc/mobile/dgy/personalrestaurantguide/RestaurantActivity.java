package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.util.ArrayList;



/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class RestaurantActivity extends Activity implements Button.OnClickListener,View.OnTouchListener{
    TextView nameview;
    TextView tagview;
    ImageView imageview;
    TextView descview;
    Button phoneview;
    TextView addview;
    TextView webview;
    MyDBHandler myDBHandler;
    GoogleMap map;
    GMapV2Direction md;
    Location myLocation;
    Button back;
    Button dirbtn;
    LatLng restaurant_location;
    RatingBar ratingbar;
    Restaurant current;
    Button edit;
    Button share;
    String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup Database
        try {
            myDBHandler = new MyDBHandler(this);
            myDBHandler.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }



        setContentView(R.layout.activity_restaurant);
        nameview = (TextView) findViewById(R.id.rest_name);
        tagview = (TextView) findViewById(R.id.rest_tag);
        imageview = (ImageView) findViewById(R.id.rest_image);
        descview = (TextView) findViewById(R.id.rest_desc);
        phoneview = (Button) findViewById(R.id.rest_phone);
        addview = (TextView) findViewById(R.id.rest_add);
        webview = (TextView) findViewById(R.id.rest_web);
        back=(Button)findViewById(R.id.back);
        dirbtn=(Button)findViewById(R.id.direction);
        edit=(Button)findViewById(R.id.edit);
        ratingbar=(RatingBar)findViewById(R.id.rating);
        ratingbar.setEnabled(true);
        ratingbar.setClickable(true);
        share=(Button)findViewById(R.id.sharebtn);

        id = getIntent().getStringExtra("_id");
             int num_id= Integer.valueOf(id);
        current = myDBHandler.searchRestaurantsbyId(num_id);
        nameview.setText(current.getName());
        tagview.setText(current.getTag());
        imageview.setImageResource(current.getImage());
        descview.setText(current.getDescription());

        phoneview.setText(phonenumberConverter(current.getPhone()));


        addview.setText("Address:          "+current.getAddress());

        webview.setClickable(true);
        webview.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://"+current.getWebsite()+"'> "+current.getName()+" </a>";
        webview.setText(Html.fromHtml(text));

        restaurant_location=new LatLng(current.getLatitude(),current.getLongitude());


        back.setOnClickListener(this);
        dirbtn.setOnClickListener(this);
        phoneview.setOnClickListener(this);
        edit.setOnClickListener(this);
        share.setOnClickListener(this);

        //update rating
        ratingbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingbar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    double stars = (double)starsf + 0.5;
                    ratingbar.setRating((float)stars);

                    Toast.makeText(RestaurantActivity.this, String.valueOf("Rate has been changed"), Toast.LENGTH_SHORT).show();
                    myDBHandler.UpdateRate(ratingbar.getRating(),current.getRest_id());
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }




                return true;
            }});
        //setup map
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        md = new GMapV2Direction();

        map=((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        //set makers
        map.addMarker(new MarkerOptions().position(new LatLng(current.getLatitude(),current.getLongitude())).title(current.getName()));

        //set a camera
        CameraUpdate citycamera= CameraUpdateFactory.newLatLngZoom(new LatLng(current.getLatitude(),current.getLongitude()), 10);
        map.animateCamera(citycamera);

        //setup gps
        LocationManager manager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLocation=location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        myLocation=manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,listener);
        map.setMyLocationEnabled(true);



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
        }else if(id==R.id.home)
        {
            Intent i= new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v) {
        int curid = v.getId();
        switch (curid) {
            case R.id.rest_phone:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + current.getPhone()));
                //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
                break;
            case R.id.direction:

                LatLng location=new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
                PolylineOptions rectLine;
                Document doc = md.getDocument(location,
                        restaurant_location, GMapV2Direction.MODE_DRIVING);
                ArrayList<LatLng> directionPoint = md.getDirection(doc);
                rectLine = new PolylineOptions().width(5).color(Color.RED);

                for(int i = 0 ; i < directionPoint.size() ; i++) {
                    rectLine.add(directionPoint.get(i));
                }

                map.addPolyline(rectLine);

                break;
            case R.id.back:
                Intent score = new Intent(this, MainActivity.class);
                startActivity(score);
                this.finish();
                myDBHandler.close();
                break;
            case R.id.edit:
                Intent editactivity = new Intent(this, EditActivity.class);
                editactivity.putExtra("_id", id);
                startActivity(editactivity);
                this.finish();
                break;
            case R.id.sharebtn:
                AlertDialog.Builder alert=new AlertDialog.Builder(this);

                alert.setTitle("Shared by Email");
                alert.setMessage("Please enter the email address");
                // Set an EditText view to get user input
                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String to = input.getText().toString();
                        if(to==null){
                            to="No Email Enter";
                        }
                        String subject = "Email from Personal Restaurant Guide";
                        String message="This Restaurant has been shared:\n"
                                +"Name: "+current.getName()
                                +"\nAddress: "+current.getAddress()
                                +"\nPhone Number: "+current.getPhone()
                                +"\nDescription: "+current.getDescription()
                                +"\nWebsite: "+current.getWebsite();

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
                        email.putExtra(Intent.EXTRA_SUBJECT, subject);
                        email.putExtra(Intent.EXTRA_TEXT, message);

                        // need this to prompts email client only
                        email.setType("message/rfc822");

                        startActivity(Intent.createChooser(email, "Choose an Email client"));
                        onDestroy();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent main = new Intent(RestaurantActivity.this, MainActivity.class);
                        startActivity(main);
                        onDestroy();
                    }
                });

                final AlertDialog dialog=alert.create();
                dialog.show();

        }

    }
    public String phonenumberConverter(String string)
    {
        string = "("+string.substring(0, 3)+")-"+string.substring(3, 6)+"-"+string.substring(6, 10);
        return string;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
