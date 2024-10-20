/*
@author - Alvaro Dias
RA - 1110482223004
 */
package br.edu.fateczl.calculobytes;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText NumBits;
    private Spinner spnConvert;
    private TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NumBits = findViewById(R.id.NumBits);
        NumBits.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        spnConvert = findViewById(R.id.spnConvert);
        txtRes = findViewById(R.id.txtRes);
        Button btnCalc = findViewById(R.id.btnCalc);

        String[] unidade = {"Bytes", "KB", "MB", "GB", "TB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, unidade);
        spnConvert.setAdapter(adapter);
        btnCalc.setOnClickListener(op -> calc());


    }

    private void calc() {
        String in = NumBits.getText().toString();

        if (!in.isEmpty()){
            double bits = Double.parseDouble(in);
            double resultado = 0;
            String unidadeSelecionada = spnConvert.getSelectedItem().toString();

            switch(unidadeSelecionada){
                case "Bytes":
                    resultado = bits/8;
                    break;

                case "KB":
                    resultado = bits / (8 * 1024);
                    break;

                case "MB":
                    resultado = bits / (8 * Math.pow(1024, 2));
                    break;

                case "GB":
                    resultado = bits / (8 * Math.pow(1024, 3));
                    break;

                case "TB":
                    resultado = bits / (8 * Math.pow(1024, 4));
                    break;
            }
            txtRes.setText(String.format("Resultado: %.2f %s ", resultado, unidadeSelecionada));
        } else {
            txtRes.setText("Numero inválido. Entre com número válido");
        }
    }
}