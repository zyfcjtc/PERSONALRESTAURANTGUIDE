package ca.gbc.mobile.dgy.personalrestaurantguide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.android.gms.maps.model.LatLng;

/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class Restaurant_list {
    ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
    public Restaurant_list() {
        //id,name,address,phone,tag,description,rate,website,pic
        restaurants.add(new Restaurant("Phoenix Restaurant","120	7155 Woodbine Ave. Markham L3R1A3",       "9059401113","Chinese", "Hong Kong style",3.5f,"http://phoenixrestaurant.cc/",R.drawable.phoenix_desc,43.817686, -79.349343));
        restaurants.add(new Restaurant("Wasabi Restaurant", "203	668 Silver Star Blvd. Scarborough M1V4S5","4162269988","Japanese","Sushi, Buffets", 3.0f,"http://www.wasabibuffet.ca/", R.drawable.wasabi_desc,43.822585, -79.299765));
        restaurants.add(new Restaurant("Ten-Ichi Restaurant","213 4810 Sheppard Ave. E Scarborough M1S4N6","4162975787","Japanese","Sushi, Buffets", 5.0f,"no website",				    R.drawable.tenichi_desc,43.791592, -79.250890));
        restaurants.add(new Restaurant("Green Grotto Tea Room","115 3623 Highway 7 E	Markham	L3R8X6",      "9056040101","Chinese", "Taiwan, dessert",4.0f,"http://www.greengrotto.ca/",	R.drawable.greengrotto_desc,43.820646, -79.326572));
        restaurants.add(new Restaurant("Congee Wong"			,"12 900 Don Mills Rd. North York M3C1V6",	  "4163858822","Chinese", "Congee",		    4.5f,"http://www.congeewong.com/",	R.drawable.congeewong_desc,43.726978, -79.342221));
        restaurants.add(new Restaurant("Restoran Malaysia",   "815 Major Mackenzie Dr. E	Richmond Hill L4C9X2","9055081432","Malaysian","Casual dining",4.0f,"restoranmalaysia.com/",    R.drawable.restoranmalaysia_desc,43.875679, -79.413873));
        restaurants.add(new Restaurant("Alice Fazoolis",		 "294 Adelaide St. W Toronto L4B3B4",	      "9057094280","Italian", "Wine bars, beer, spirits",3.0f,"alicefazoolis.ca",	R.drawable.alicefazoolis_desc,43.647751, -79.390881));
        restaurants.add(new Restaurant("Frankie Tomatto's",   "7225 Woodbine Ave. Markham L4R1A3",	      "9059401900","Italian", "Buffets, Italian",3.5f,"frankietomattos.com",		R.drawable.frankietomattos_desc,43.818856, -79.349143));
        restaurants.add(new Restaurant("Nak Won"				,"5594 Yonge St. North York	M2N5S4",	      "4165901435","Korean",  "No description"  ,4.0f, "nakwon.ca",			        R.drawable.nakwon_desc,43.779421, -79.415470));
        restaurants.add(new Restaurant("Udupi Palace"		,"1460 Gerrard St. E Toronto M4L2A3",	      "4164058189","Indian",  "Vegetarian"	,   4.5f, "udupipalace.ca",			    R.drawable.udupipalace_desc,43.672507, -79.321076));
        restaurants.add(new Restaurant("Bangkok Garden"		,"18 Elm St. Toronto M5G1G7",                 "4169776748","Thai",	  "No description",	5.0f, "bangkokgarden.ca",		    R.drawable.bangkokgarden_desc,43.657729, -79.382654));
        restaurants.add(new Restaurant("Big Fat Burrito"		,"285 Augusta Ave. Toronto M5T2M1",	          "4169137487","Mexican", "Tex-Mex",	    5.0f, "bigfatburrito.ca",		    R.drawable.bigfatburrito_desc,43.656043, -79.402615));
        restaurants.add(new Restaurant("Astoria Shish Kebob House","390 Danforth Ave. Toronto M4K1P3",	  "4164632838","Greek",	  "Mediterranean",	4.0f, "astoriashishkebobhouse.com",	R.drawable.astoriashishkebobhouse_desc,43.677597, -79.351887));
        restaurants.add(new Restaurant("Scaramouche Restaurant Pasta Bar & Grill","1 Benvenuto Pl. Toronto M4V2L1","4169618011","French","No description",4.5f,"scaramoucherestaurant.com",R.drawable.scaramoucherestaurant_desc,43.681829, -79.400278));
        restaurants.add(new Restaurant("Canoe",				 "54/F 66 Wellington St. W Toronto M5K1H6",	  "4163640054","Canadian","No description",	4.5f, "canoerestaurant.com",		R.drawable.canoerestaurant_desc,43.647609, -79.381525));
        restaurants.add(new Restaurant("Herban Herbivore"	,"64 Oxford St.	Toronto M5T1P1",	          "4169271231","Vegan",   "Sanwiches",		3.5f, "herbivore.to",			    R.drawable.herbanherbivore_desc,43.656126, -79.402642));
        restaurants.add(new Restaurant("Red Lobster"			,"7291 Yonge St. Thornhill	L3T2A9",	      "9057313550","American","Seafood",		3.0f, "redlobster.com",			    R.drawable.redlobster_desc,43.805281, -79.421241));
        restaurants.add(new Restaurant("Ten23"				,"102 3100 Steeles Ave. E Markham L3R8T3",	  "9054796488","Asian",   "Fusion Karaoke, lounge, dessert",2.5f,"ten23.ca",	R.drawable.ten23_desc,43.815014, -79.346872));
        restaurants.add(new Restaurant("Panzerotto Pizza"    ,"884 Danforth Ave. Toronto	M4J1L7"	       ,  "4164064682","Pizza",	  "No description", 4.0f, "www.panzerottopizza.com/",	R.drawable.panzerottopizza_desc,43.680096, -79.339852));
        restaurants.add(new Restaurant("Gerhard's Cafe"	    ,"1085 Bellamy Rd. N Scarborough M1H3C7",	  "4164389800","Cafes",	  "Bakeries"	,	4.0f, "www.gerhards.ca",		    R.drawable.gerhardscafe_desc,43.775576, -79.241567));


    }
    public ArrayList<Restaurant> getlist()
    {
        return restaurants;
    }
}
