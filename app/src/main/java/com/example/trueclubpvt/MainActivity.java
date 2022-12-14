package com.example.trueclubpvt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.trueclubpvt.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<CountryModel> call = RetrofitClient.getInstance().getMyApi().getCountryGuess(binding.editTextTextPersonName.getText().toString());
                call.enqueue(new Callback<CountryModel>() {
                    @Override
                    public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                        CountryModel model = response.body();

                        String[] list = new String[model.country.size()];

                        for(int i=0;i<list.length;i++){
                            list[i] = "country : " + model.country.get(i).country_id + "\n" + "probability" + model.country.get(i).probability;
                        }

                        binding.listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list));

                    }

                    @Override
                    public void onFailure(Call<CountryModel> call, Throwable t) {

                    }
                });

            }
        });




    }
}