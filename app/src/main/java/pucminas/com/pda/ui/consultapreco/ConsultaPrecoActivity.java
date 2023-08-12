package pucminas.com.pda.ui.consultapreco;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import pucminas.com.pda.R;
import pucminas.com.pda.domain.Produto;
import pucminas.com.pda.viewmodel.ProdutoViewModel;

public class ConsultaPrecoActivity extends AppCompatActivity
{
    private TextInputEditText tProduto;
    private TextView tDescricao, tPreco;
    private Button bAdicionar;
    private CheckBox chkDivergente;
    private Produto produto;
    private ProdutoViewModel produtoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_consulta_preco);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tProduto = findViewById(R.id.tProduto);
        tDescricao = findViewById(R.id.tDescricao);
        tPreco = findViewById(R.id.tPreco);
        chkDivergente = findViewById(R.id.chkDivergente);
        bAdicionar = findViewById(R.id.bAdicionar);

        tProduto.setOnKeyListener((v, keyCode, event) ->
        {
            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER))
            {
                Carrega(Integer.parseInt(tProduto.getText().toString()));
                return true;
            }

            return false;
        });

        bAdicionar.setOnClickListener(view -> {
            Salvar();
        });

    }

    public void Carrega(Integer produtoId)
    {
        try
        {
            produtoViewModel = new ProdutoViewModel(getApplication());
            produto =  produtoViewModel.Carrega(produtoId);

            if(produto != null)
            {
                chkDivergente.setVisibility(View.VISIBLE);
                bAdicionar.setVisibility(View.VISIBLE);
                tDescricao.setVisibility(View.VISIBLE);

                tDescricao.setText(produto.getDescricao());
                tPreco.setText(produto.getPreco().toString());

                if (produto.getPrecoDivergente() ==1)
                {
                    chkDivergente.setChecked(true);
                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Não há informações do produto. ", Toast.LENGTH_SHORT).show();

                tDescricao.setVisibility(View.INVISIBLE);
                bAdicionar.setVisibility(View.INVISIBLE);
                chkDivergente.setVisibility(View.INVISIBLE);

                tDescricao.setText("");
                tPreco.setText("0.00");
                chkDivergente.setChecked(false);

            }

            tProduto.setText("");
            tProduto.requestFocus();
            return;

        }
        catch (Exception Ex)
        {
            Log.e("Carrega:", Ex.getMessage());
        }
    }

    public void Salvar()
    {
        try
        {
            if(produto != null)
            {
                if(chkDivergente.isChecked()) {
                    produto.setPrecoDivergente(1);
                }

               produtoViewModel.Salvar(produto);
            }

            Toast.makeText(getApplicationContext(), "Informações Salva com sucesso. ", Toast.LENGTH_SHORT).show();

            tDescricao.setVisibility(View.INVISIBLE);
            bAdicionar.setVisibility(View.INVISIBLE);
            chkDivergente.setVisibility(View.INVISIBLE);

            tDescricao.setText("");
            tPreco.setText("0.00");
            chkDivergente.setChecked(false);
        }
        catch (Exception Ex)
        {
            Log.e("Salvar():", Ex.getMessage());
        }
    }
}