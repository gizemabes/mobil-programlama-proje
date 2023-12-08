package com.example.example_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConvertorActivity extends AppCompatActivity {

    EditText editTextInputDecimal;
    TextView textViewDecimalResult;
    EditText editTextInputMegaByte;
    TextView textViewMegaByteResult;
    EditText editTextInputCelcius;
    TextView textViewCelciusResult;

    static int decimalPosition;
    static int megabytePosition;
    static int celciusPosition;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Convertor Activity");
        }

        editTextInputDecimal = findViewById(R.id.editTextInputDecimal);
        textViewDecimalResult = findViewById(R.id.textViewDecimalResult);
        Button buttonConvertDecimal = findViewById(R.id.buttonConvertDecimal);

        editTextInputMegaByte = findViewById(R.id.editTextInputMegaByte);
        textViewMegaByteResult = findViewById(R.id.textViewMegaByteResult);
        Button buttonConvertMegaByte = findViewById(R.id.buttonConvertMegaByte);

        editTextInputCelcius = findViewById(R.id.editTextInputCelcius);
        textViewCelciusResult = findViewById(R.id.textViewCelciusResult);
        Button buttonConvertCelcius = findViewById(R.id.buttonConvertCelcius);

        Spinner spinnerDecimal = findViewById(R.id.spinnerDecimal);
        Spinner spinnerMegaByte = findViewById(R.id.spinnerMegaByte);
        Spinner spinnerCelcius = findViewById(R.id.spinnerCelcius);


        ArrayAdapter<CharSequence> decimalAdapter = ArrayAdapter.createFromResource(this,
                R.array.decimal_conversion_options, android.R.layout.simple_spinner_item);
        decimalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDecimal.setAdapter(decimalAdapter);

        ArrayAdapter<CharSequence> megaByteAdapter = ArrayAdapter.createFromResource(this,
                R.array.megabyte_conversion_options, android.R.layout.simple_spinner_item);
        megaByteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMegaByte.setAdapter(megaByteAdapter);

        ArrayAdapter<CharSequence> celciusAdapter = ArrayAdapter.createFromResource(this,
                R.array.celsius_conversion_options, android.R.layout.simple_spinner_item);
        celciusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCelcius.setAdapter(celciusAdapter);



        spinnerDecimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                decimalPosition = position; // Seçilen pozisyona göre dönüşümü yap
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Implemente edilebilir gerektiğinde
            }
        });

        spinnerMegaByte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                megabytePosition = position; // Seçilen pozisyona göre dönüşümü yap
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Implemente edilebilir gerektiğinde
            }
        });

        spinnerCelcius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                celciusPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Implemente edilebilir gerektiğinde
            }
        });

        buttonConvertDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performDecimalConversion();
            }
        });

        buttonConvertMegaByte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performMegaByteConversion();
            }
        });

        buttonConvertCelcius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCelciusConversion();
            }
        });


    }

    @SuppressLint("SetTextI18n")
    private void performMegaByteConversion() {
        String inputValue = editTextInputMegaByte.getText().toString().trim();

        if (inputValue.isEmpty()) {
            textViewMegaByteResult.setText("Lütfen bir sayı girin!");
            return;
        }

        try {
            double megaByteValue = Double.parseDouble(inputValue);

            String resultText = "";

            switch (megabytePosition) {
                case 0: // Kilobyte
                    resultText = "Kilobyte: " + (megaByteValue * 1024);
                    break;
                case 1: // Byte
                    resultText = "Byte: " + (megaByteValue * 1024 * 1024);
                    break;
                case 2: // Kibibyte
                    resultText = "Kibibyte: " + (megaByteValue * 1024 * 1024 / 1024);
                    break;
                case 3: // Bit
                    resultText = "Bit: " + (megaByteValue * 1024 * 1024 * 8);
                    break;
                default:
                    break;
            }

            textViewMegaByteResult.setText(resultText);

        } catch (NumberFormatException e) {
            textViewMegaByteResult.setText("Lütfen geçerli bir sayı girin!");
        }
    }

    @SuppressLint("SetTextI18n")
    private void performDecimalConversion() {
        String inputValue = editTextInputDecimal.getText().toString().trim();

        if (inputValue == "") {
            textViewDecimalResult.setText("Lütfen bir sayı girin!");
            return;
        }

        try {
            int decimalValue = Integer.parseInt(inputValue);

            String resultText = "";

            switch (decimalPosition) {
                case 0: // İkilik Taban
                    resultText = "İkilik Taban: " + Integer.toBinaryString(decimalValue);
                    break;
                case 1: // Sekizlik Taban
                    resultText = "Sekizlik Taban: " + Integer.toOctalString(decimalValue);
                    break;
                case 2: // On Altılık Taban
                    resultText = "On Altılık Taban: " + Integer.toHexString(decimalValue);
                    break;
                default:
                    break;
            }

            textViewDecimalResult.setText(resultText);

        } catch (NumberFormatException e) {
            textViewDecimalResult.setText("Lütfen geçerli bir sayı girin!");
        }
    }


    @SuppressLint("SetTextI18n")
    private void performCelciusConversion() {
        String inputValue = editTextInputCelcius.getText().toString().trim();

        if (inputValue.isEmpty()) {
            textViewCelciusResult.setText("Lütfen bir sayı girin!");
            return;
        }

        try {
            double celsiusValue = Double.parseDouble(inputValue);
            double convertedValue = 0;

            String resultText = "";

            switch (celciusPosition) {
                case 0:
                    convertedValue = celsiusValue * 9 / 5 + 32;
                    resultText = "Fahrenheit: " + convertedValue;
                    break;
                case 1:
                    convertedValue = celsiusValue + 273.15;
                    resultText = "Kelvin: " + convertedValue;
                    break;
                default:
                    break;
            }

            textViewCelciusResult.setText(resultText);

        } catch (NumberFormatException e) {
            textViewCelciusResult.setText("Lütfen geçerli bir sayı girin!");
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}