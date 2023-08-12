package pucminas.com.pda.repository;

import android.app.Application;
import android.util.Log;

import org.apache.commons.lang3.math.NumberUtils;

import pucminas.com.pda.dao.ProdutoDao;
import pucminas.com.pda.database.AppDataBase;
import pucminas.com.pda.domain.Operador;
import pucminas.com.pda.domain.Produto;
import pucminas.com.pda.domain.ProdutoInventario;

public class ProdutoRepository {
   private AppDataBase appDataBase;
   private ProdutoDao produtoDao;
   private Produto produto;

   public ProdutoRepository(Application application){
      appDataBase = AppDataBase.getInstance(application);
   }

   public Produto Carrega(Integer produtoId)
   {
      produtoDao = appDataBase.produtoDao();
      produto = produtoDao.Carrega(produtoId);

      return produto;
   }

   public void Salvar(Produto produto)
   {
      String TAG = "ProdutoRepository";
      try
      {
         appDataBase.produtoDao().Salvar(produto);
      }
      catch (Exception Ex){
         Log.e(TAG, Ex.getMessage());
      }
   }
}
