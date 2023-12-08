package com.example.example_app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RandomActivity extends AppCompatActivity {
    private EditText barCountEditText;
    private EditText minEditText;
    private EditText maxEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Random Activity");
        }

        final EditText barCountEditText = findViewById(R.id.barCount);
        minEditText = findViewById(R.id.minEditText);
        maxEditText = findViewById(R.id.maxEditText);
        Button barCountButton = findViewById(R.id.barCountButton);
        LinearLayout container = findViewById(R.id.container);

        barCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText'ten girilen değeri al
                int barCount = Integer.parseInt(barCountEditText.getText().toString());
                int min = Integer.parseInt(minEditText.getText().toString());
                int max = Integer.parseInt(maxEditText.getText().toString());

                container.removeAllViews(); // Önceki ProgressBarları temizle

                for (int i = 0; i < barCount; i++) {
                    ProgressBar progressBar = new ProgressBar(RandomActivity.this, null, android.R.attr.progressBarStyleHorizontal);
                    progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 26, 0, 0);
                    progressBar.setLayoutParams(params);

                    // Maksimum ve minimum değerleri sırala
                    int range = Math.abs(max - min);
                    min = Math.min(min, max);
                    max = min + range;

                    // Rastgele bir değer üret ve çubuk üzerinde yüzdelik olarak ayarla
                    int randomValue = (int) (Math.random() * range) + min;
                    progressBar.setMax(range);

                    // Çubuk üzerinde yüzdelik olarak göster
                    progressBar.setProgress(randomValue - min);

                    // Değerleri yazdırma
                    TextView values = new TextView(RandomActivity.this);
                    values.setLayoutParams(params);
                    values.setText(String.format("Min: %d, Max: %d, Value: %d, Percentage: %d%%", min, max, randomValue, (int) ((randomValue - min) / (float) range * 100)));
                    container.addView(values);

                    container.addView(progressBar);
                }
            }
        });



    }

    // Geri butonuna tıklandığında geri dön
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}