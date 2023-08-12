package pucminas.com.pda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import pucminas.com.pda.domain.Operador;
import pucminas.com.pda.repository.OperadorRepository;

public class OperadorViewModel extends ViewModel {

    private Application application;
    private OperadorRepository repository;
    private Operador operador;

    public OperadorViewModel(@NonNull Application application) {

        this.application = application;
    }

    public Operador Carrega(String movimentador) {
        repository = new OperadorRepository(application);
        operador = repository.Carrega(movimentador);
        return operador;
    }
}
