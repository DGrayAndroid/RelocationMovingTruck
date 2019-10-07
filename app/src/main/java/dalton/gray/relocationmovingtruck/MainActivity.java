package dalton.gray.relocationmovingtruck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFERENCE_NAME = "RENTAL_PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner rentalOptions = (Spinner) findViewById(R.id.rentalOption_spn);
        final EditText numMiles = (EditText) findViewById(R.id.numMiles_et);

        Button calc = (Button) findViewById(R.id.calculateCost_btn);
        calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String rentalOption = rentalOptions.getSelectedItem().toString();
                int miles = Integer.parseInt(numMiles.getText().toString());

                SharedPreferences rentalPrefs = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = rentalPrefs.edit();
                editor.putString("rentalOption", rentalOption);
                editor.putInt("numMiles", miles);
                editor.commit();

                startActivity(new Intent(MainActivity.this, RentalCost.class));
            }
        });
    }
}
