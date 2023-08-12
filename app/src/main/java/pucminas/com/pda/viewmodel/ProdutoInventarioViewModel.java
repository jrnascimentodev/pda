package pucminas.com.pda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import pucminas.com.pda.domain.ProdutoInventario;
import pucminas.com.pda.repository.ProdutoInventarioRepository;

public class ProdutoInventarioViewModel extends ViewModel
{
    private Application application;
    private ProdutoInventarioRepository repository;
    private ProdutoInventario produtoInventario;

    public ProdutoInventarioViewModel(@NonNull Application application) {

        this.application = application;
    }

    public ProdutoInventario Carrega(Date data, Integer localEstoque, Integer produtoId)
    {
        repository = new ProdutoInventarioRepository(application);
        produtoInventario = repository.Carrega(data, localEstoque, produtoId);
        return produtoInventario;
    }

    public void Salvar(ProdutoInventario produtoInventario)
    {
        repository = new ProdutoInventarioRepository(application);
        repository.Salvar(produtoInventario);
    }

    public Integer Sequencia(Date data, Integer localEstoque,Integer produtoId)
    {
        return  new ProdutoInventarioRepository(application).Sequencia(data, localEstoque, produtoId);
    }
}
