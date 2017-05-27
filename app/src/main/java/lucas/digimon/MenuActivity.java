package lucas.digimon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btn_sobre, btn_jogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_sobre = (Button) findViewById(R.id.btn_sobre);
        btn_jogar = (Button) findViewById(R.id.btn_jogar);

        btn_jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        btn_sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SobreActivity.class);
                startActivity(intent);
            }
        });
    }
}