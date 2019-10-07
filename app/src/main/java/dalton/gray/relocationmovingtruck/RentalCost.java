package dalton.gray.relocationmovingtruck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class RentalCost extends AppCompatActivity {

    private final double TEN_FOOT_RATE = 19.95;
    private final double SEVENTEEN_FOOT_RATE = 29.95;
    private final double TWENTYSIX_FOOT_RATE = 39.95;
    private final double MILEAGE_RATE = .99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_cost);

        String[] rentalOptions = getResources().getStringArray(R.array.rental_options);

        TextView truckSize = (TextView) findViewById(R.id.truckSize_tv);
        TextView miles = (TextView) findViewById(R.id.miles_tv);
        TextView rentalCost = (TextView) findViewById(R.id.rentalCost_tv);
        ImageView truck = (ImageView) findViewById(R.id.truck_iv);

        SharedPreferences rentalPrefs = getSharedPreferences(MainActivity.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        String rentalOption = rentalPrefs.getString("rentalOption", "No Rental Option Saved");
        int numMiles = rentalPrefs.getInt("numMiles", 0);
        double totalCost, rate;

        if (rentalOption.equals(rentalOptions[0])){
            rate = TEN_FOOT_RATE;

            truckSize.setText("Truck Size: 10'");
            truck.setImageResource(R.drawable.tenfoot);
        } else if (rentalOption.equals(rentalOptions[1])) {
            rate = SEVENTEEN_FOOT_RATE;

            truckSize.setText("Truck Size: 17'");
            truck.setImageResource(R.drawable.seventeenfoot);
        } else {
            rate = TWENTYSIX_FOOT_RATE;

            truckSize.setText("Truck Size: 26'");
            truck.setImageResource(R.drawable.twentysixfoot);
        }

        miles.setText("Number of Miles: " + numMiles);

        totalCost = (numMiles * MILEAGE_RATE) + rate;
        DecimalFormat currencyUSD = new DecimalFormat("$###,###.##");
        rentalCost.setText("Total Rental Cost: " + currencyUSD.format(totalCost));
    }
}
