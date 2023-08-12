package pucminas.com.pda.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pucminas.com.pda.domain.Operador;
import pucminas.com.pda.ui.home.MainActivity;
import pucminas.com.pda.R;
import pucminas.com.pda.viewmodel.OperadorViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextView tOperador;
    private TextView tOperadorSenha;
    private Button bEntrar;

    private Operador operador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        tOperador = findViewById(R.id.tOperador);
        tOperadorSenha = findViewById(R.id.tOperadorSenha);
        bEntrar = findViewById(R.id.bEntrar);

        tOperador.setOnKeyListener((v, keyCode, event) ->
        {
            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER))
            {
                if(TextUtils.isEmpty(tOperador.getText().toString()))
                {
                    tOperador.setError("Campo requerido)");
                    tOperador.requestFocus();
                    return false;
                }

                Carrega(tOperador.getText().toString().toUpperCase().trim());
                return true;
            }

            return false;
        });

        bEntrar.setOnClickListener(view ->
        {
            if(TextUtils.isEmpty(tOperadorSenha.getText().toString()))
            {
                tOperadorSenha.setError("Campo requerido)");
                tOperadorSenha.requestFocus();
                return;
            }

            ValidaUsuario(tOperadorSenha.getText().toString());
        });
    }

    public void Carrega(String operadorId)
    {
        try
        {
            OperadorViewModel operadorViewModel = new OperadorViewModel(getApplication());
            operador =  operadorViewModel.Carrega(operadorId);

           if(operador != null)
              tOperador.setText(operador.getNome());

        }
        catch (Exception Ex)
        {
            Log.e("Carrega:", Ex.getMessage());
        }
    }

    public void ValidaUsuario(String senha)
    {
        if(operador != null)
        {
            if(operador.getSenha().equals(senha))
            {
                // Inicia a activity login
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("operador", operador);
                startActivity(i);
                finish();
            }
            else
            {
                tOperadorSenha.setError("Campo senha Inválido)");
                tOperadorSenha.requestFocus();
                return;
            }
        }
    }
}