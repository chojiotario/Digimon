package lucas.digimon;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class SlotMachineActivity extends AppCompatActivity {

    private ImageView ivSlot1, ivSlot2, ivSlot3;
    private Roda slot1, slot2, slot3;
    private Button btn_iniciar;

    private RelativeLayout background;

    private TextView tvNome, tvFichas, tvTimer;

    private int fichas;

    private int count = 0;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper){
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
        ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
        ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);

        tvNome = (TextView) findViewById(R.id.tvNomeInGame);
        tvFichas = (TextView) findViewById(R.id.tvQtdFichas);
        tvTimer = (TextView) findViewById(R.id.timer);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);

        String nome = getIntent().getStringExtra("NOME");
        tvNome.setText(nome);

        String numFichas = getIntent().getStringExtra("NUM_FICHAS");
        fichas = Integer.parseInt(numFichas);
        tvFichas.setText(String.valueOf(fichas));

        background = (RelativeLayout) findViewById(R.id.activity_slot_machine);

        boolean masq = getIntent().getExtras().getBoolean("MASQ");
        boolean femin = getIntent().getExtras().getBoolean("FEMIN");

        if (masq){
            background.setBackgroundResource(R.drawable.wallpapertai);
        }

        if (femin){
            background.setBackgroundResource(R.drawable.wallpapersora);
        }

        Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tvTimer.setText("tempo: " + count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);


    }

    public void jogar(View v){

        tvFichas.setText(String.valueOf(fichas));

        if(fichas > 0){

            fichas--;

            rodarSlot1();
            rodarSlot2();
            rodarSlot3();

            btn_iniciar.setEnabled(false);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    slot1.paraDeRodar();
                    slot2.paraDeRodar();
                    slot3.paraDeRodar();

                    exibeResultado();

                    btn_iniciar.setEnabled(true);
                }
            }, 3000);

        } else {
            Toast.makeText(this, "Acabaram suas fichas :(", Toast.LENGTH_LONG).show();

        }

    }

    private void exibeResultado(){
        if(slot1.indiceAtual == slot2.indiceAtual && slot2.indiceAtual == slot3.indiceAtual){
            Toast.makeText(this, "Voce ganhou!", Toast.LENGTH_SHORT).show();
        } else if(slot1.indiceAtual == slot2.indiceAtual || slot2.indiceAtual == slot3.indiceAtual || slot1.indiceAtual == slot3.indiceAtual){
            Toast.makeText(this, "Pequena premiação!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Voce perdeu!", Toast.LENGTH_SHORT).show();
        }
    }

    private void rodarSlot1(){
        slot1 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resourceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(0, 200));
        slot1.start();
    }

    private void rodarSlot2(){
        slot2 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resouceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(resouceID);
                    }
                });
            }
        }, 200, randomLong(150, 400));
        slot2.start();
    }

    private void rodarSlot3(){
        slot3 = new Roda(new Roda.RodaListener(){
            @Override
            public void novaImagem(final int resouceID){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(resouceID);
                    }
                });
            }
        }, 200, randomLong(300, 600));
        slot3.start();
    }
}
