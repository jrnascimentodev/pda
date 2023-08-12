package pucminas.com.pda.repository;

import android.app.Application;

import org.apache.commons.lang3.math.NumberUtils;

import pucminas.com.pda.dao.OperadorDao;
import pucminas.com.pda.database.AppDataBase;
import pucminas.com.pda.domain.Operador;

public class OperadorRepository
{
    private AppDataBase appDataBase;
    private OperadorDao operadorDao;
    private Operador operador;

    public OperadorRepository(Application application){
        appDataBase = AppDataBase.getInstance(application);
    }

    public Operador Carrega(String operadorId)
    {
        operadorDao = appDataBase.operadorDao();

        if (NumberUtils.isNumber(operadorId)) {
            operador = operadorDao.Carrega(Integer.parseInt(operadorId));
        }
        else
        {
            operador = operadorDao.Carrega(operadorId);
        }

        return operador;
    }
}
