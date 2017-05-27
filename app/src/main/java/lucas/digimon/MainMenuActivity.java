package lucas.digimon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainMenuActivity extends AppCompatActivity {

    private Button btn_comecar;
    private EditText et_nome;

    private String sexo, numFichas;

    private LinearLayout lt_background;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_comecar = (Button) findViewById(R.id.btn_comecar);
        et_nome = (EditText) findViewById(R.id.etNome);
        lt_background = (LinearLayout) findViewById(R.id.activity_main_menu);

        spinner = (Spinner) findViewById(R.id.fichas_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.fichas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                numFichas = spinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        numFichas = spinner.getSelectedItem().toString();

        btn_comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenuActivity.this, SlotMachineActivity.class);
                intent.putExtra("NOME", et_nome.getText().toString());

                if (sexo == "masculino"){
                    intent.putExtra("MASQ", true);
                } else if (sexo == "feminino"){
                    intent.putExtra("FEMIN", true);
                }

                intent.putExtra("NUM_FICHAS", numFichas);
                startActivity(intent);

            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_masculino:
                if (checked)

                    lt_background.setBackgroundResource(R.drawable.wallpapertai);
                sexo = "masculino";

                break;
            case R.id.radio_feminino:
                if (checked)

                    lt_background.setBackgroundResource(R.drawable.wallpapersora);
                sexo = "feminino";

                break;
        }
    }

}
