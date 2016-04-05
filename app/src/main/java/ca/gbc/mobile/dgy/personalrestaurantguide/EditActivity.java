package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class EditActivity extends Activity implements Button.OnClickListener,View.OnTouchListener{

    MyDBHandler myDBHandler;
    EditText name;
    EditText address;
    EditText phone;
    EditText tag;
    EditText desc;
    EditText website;
    Restaurant current;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);



        //Setup Database
        try {
            myDBHandler = new MyDBHandler(this);
            myDBHandler.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phone);
        tag=(EditText)findViewById(R.id.tag);
        desc=(EditText)findViewById(R.id.description);
        website=(EditText)findViewById(R.id.website);
        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);

        id = getIntent().getStringExtra("_id");
        if(id!=null) {
            Toast.makeText(EditActivity.this, String.valueOf("You are modifying Restaurant"), Toast.LENGTH_SHORT).show();
            current = myDBHandler.searchRestaurantsbyId(Integer.valueOf(id));

            name.setText(current.getName());
            address.setText(current.getAddress());
            phone.setText(current.getPhone());
            tag.setText(current.getTag());
            desc.setText(current.getDescription());
            website.setText(current.getWebsite());
        }
        else{
            Toast.makeText(EditActivity.this, String.valueOf("You are creating a new restaurant"), Toast.LENGTH_SHORT).show();

        }


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
    @Override
    public void onClick(View v) {
        int curid = v.getId();
        switch (curid) {
            case R.id.submit:
                if(id!=null) {
                    myDBHandler.UpdateRestaurant("name", set_default(name.getText().toString(),current.getName()), Integer.valueOf(id));
                    myDBHandler.UpdateRestaurant("address", set_default(address.getText().toString(),current.getAddress()), Integer.valueOf(id));
                    myDBHandler.UpdateRestaurant("phone", set_phone(phone.getText().toString(),current.getPhone()), Integer.valueOf(id));
                    myDBHandler.UpdateRestaurant("tag", set_default(tag.getText().toString(),current.getTag()), Integer.valueOf(id));
                    myDBHandler.UpdateRestaurant("description", set_default(desc.getText().toString(),current.getDescription()), Integer.valueOf(id));
                    myDBHandler.UpdateRestaurant("website", set_default(website.getText().toString(),current.getWebsite()), Integer.valueOf(id));
                    Toast.makeText(EditActivity.this, String.valueOf("Restaurant has been changed"), Toast.LENGTH_SHORT).show();
                }
                else{
                    Restaurant restaurant=new Restaurant();
                    restaurant.setName(set_default(name.getText().toString(),"not set"));
                    restaurant.setAddress(set_default(address.getText().toString(),"not set"));
                    restaurant.setPhone(set_phone(phone.getText().toString(),"1111111111"));
                    restaurant.setTag(set_default(tag.getText().toString(),"not set"));
                    restaurant.setDescription(set_default(desc.getText().toString(),"not set"));
                    restaurant.setRate(3.0f);
                    restaurant.setWebsite(set_default(website.getText().toString(),"not set"));
                    restaurant.setImage(R.drawable.kfc);
                    restaurant.setLatitude(43.653226);
                    restaurant.setLongitude(-79.3831843);
                    myDBHandler.createRestaurant(restaurant);
                    Toast.makeText(EditActivity.this, String.valueOf("You will be able to add rate after this restaurant is created"), Toast.LENGTH_SHORT).show();
                }
                Intent scor = new Intent(this, MainActivity.class);
                startActivity(scor);
                this.finish();
                break;
            case R.id.cancel:
                Intent score = new Intent(this, MainActivity.class);
                startActivity(score);
                this.finish();
                myDBHandler.close();
                break;
        }
    }
    public String set_default(String orig,String dest)
    {
        String result="not set";
        if(orig==null)
        {
            result=dest;
        }
        else{
            result=orig;
        }
        return result;
    }
    public String set_phone(String orig,String dest)
    {
        String result;
        if(orig.length()!=10)
        {
            result=dest;
        }
        else{
            result=orig;
        }
        return result;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
