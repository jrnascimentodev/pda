package pucminas.com.pda.ui.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pucminas.com.pda.R;
import pucminas.com.pda.domain.Produto;
import pucminas.com.pda.domain.ProdutoInventario;
import pucminas.com.pda.util.Funcoes;
import pucminas.com.pda.viewmodel.ProdutoInventarioViewModel;
import pucminas.com.pda.viewmodel.ProdutoViewModel;

public class InventarioActivity extends AppCompatActivity
{
    private String[] localEstoque = {"Área de Venda", "Depósito"};
    private ArrayList<String> localEstoques = new ArrayList<>();
    private ArrayAdapter<String> adpLocalEstoque;
    private Spinner spnLocalEstoque;
    private TextInputEditText tDataBase;
    private DatePickerDialog datePickerDialog;
    private TextInputEditText tProduto, tDescricao, tQuantidadeApurada, tQuantidade;
    private Button bAdicionar;
    private Produto produto;
    private ProdutoInventario produtoInventario;
    private ProdutoInventarioViewModel produtoInventarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_inventario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spnLocalEstoque = findViewById(R.id.spnLocalEstoque);
        tProduto = findViewById(R.id.tProduto);
        tDescricao = findViewById(R.id.tDescricao);
        tQuantidadeApurada = findViewById(R.id.tQuantidadeApurada);
        tQuantidade = findViewById(R.id.tQuantidade);
        bAdicionar = findViewById(R.id.bAdicionar);

        //Data Atual
        DatePickerDataBase();

        //Local Estoque
        adpLocalEstoque = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, localEstoque);
        adpLocalEstoque.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocalEstoque.setAdapter(adpLocalEstoque);

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

        bAdicionar.setOnClickListener(view ->
        {
            Salvar();
        });

    }

    //Data
    private void DatePickerDataBase()
    {
        tDataBase = findViewById(R.id.tDataBase);
        tDataBase.setText(Funcoes.DataAtual());

        tDataBase.setInputType(InputType.TYPE_NULL);

        tDataBase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                datePickerDialog = new DatePickerDialog(InventarioActivity.this, new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                            {
                                tDataBase.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    public void CarregaInventario(Date data,  Integer localEstoque, Integer produtoId)
    {
        try
        {
            produtoInventarioViewModel = new ProdutoInventarioViewModel(getApplication());
            produtoInventario = produtoInventarioViewModel.Carrega(data, localEstoque, produtoId);

        }
        catch (Exception Ex)
        {
            Log.e("Erro", Ex.getMessage());
        }
    }

    public void Carrega(Integer produtoId)
    {
        try
        {
            ProdutoViewModel produtoViewModel = new ProdutoViewModel(getApplication());
            produto =  produtoViewModel.Carrega(produtoId);

            if(produto != null)
            {
                CarregaInventario(Funcoes.ConvertDataBancoDados(tDataBase.getText().toString()), spnLocalEstoque.getSelectedItemPosition(), produto.getProdutoId());

                if(produtoInventario != null)
                {
                    tQuantidadeApurada.setText(produtoInventario.getQuantidadeApurada().toString());
                }

                tDescricao.setVisibility(View.VISIBLE);
                tDescricao.setText(produto.getProdutoId() + " - " + produto.getDescricao());

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Não há informações do produto. ", Toast.LENGTH_SHORT).show();
                tDescricao.setVisibility(View.INVISIBLE);
                tDescricao.setText("");
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
            if(tQuantidade.getText().equals("")||tQuantidade.getText().equals("0"))
                return;

            if(produtoInventario != null)
            {
                produtoInventario.setData(Funcoes.ConvertDataBancoDados(tDataBase.getText().toString()));
                produtoInventario.setLocalEstoque(spnLocalEstoque.getSelectedItemPosition());
                produtoInventario.setProdutoId(produto.getProdutoId());
                produtoInventario.setQuantidadeApurada(Double.parseDouble(tQuantidadeApurada.getText().toString()) + Double.parseDouble(tQuantidade.getText().toString()));
                produtoInventario.setQuantidade(Double.parseDouble(tQuantidade.getText().toString()));
            }
            else
            {
                produtoInventario = new ProdutoInventario();
                produtoInventario.setInventarioId(produtoInventarioViewModel.Sequencia(Funcoes.ConvertDataBancoDados(tDataBase.getText().toString()), spnLocalEstoque.getSelectedItemPosition(), produto.getProdutoId()));
                produtoInventario.setData(Funcoes.ConvertDataBancoDados(tDataBase.getText().toString()));
                produtoInventario.setLocalEstoque(spnLocalEstoque.getSelectedItemPosition());
                produtoInventario.setProdutoId(produto.getProdutoId());
                produtoInventario.setQuantidadeApurada(Double.parseDouble(tQuantidadeApurada.getText().toString()) + Double.parseDouble(tQuantidade.getText().toString()));
                produtoInventario.setQuantidade(Double.parseDouble(tQuantidade.getText().toString()));
            }

            produtoInventarioViewModel = new ProdutoInventarioViewModel(getApplication());
            produtoInventarioViewModel.Salvar(produtoInventario);

            tQuantidade.setText("1");
            tDescricao.setText("");
            tProduto.requestFocus();
        }
        catch (Exception Ex)
        {
            Log.e("Error", Ex.getMessage());
        }
    }
}