package pucminas.com.pda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import pucminas.com.pda.domain.Produto;
import pucminas.com.pda.repository.ProdutoRepository;

public class ProdutoViewModel extends ViewModel {

    private Application application;
    private ProdutoRepository repository;
    private Produto produto;

    public ProdutoViewModel(@NonNull Application application) {

        this.application = application;
    }

    public Produto Carrega(Integer produtoId) {
        repository = new ProdutoRepository(application);
        produto = repository.Carrega(produtoId);
        return produto;
    }

    public void Salvar(Produto produto)
    {
        repository = new ProdutoRepository(application);
        repository.Salvar(produto);
    }
}
