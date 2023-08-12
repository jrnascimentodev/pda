package pucminas.com.pda.repository;

import android.app.Application;
import android.util.Log;

import java.util.Date;

import pucminas.com.pda.dao.ProdutoDao;
import pucminas.com.pda.dao.ProdutoInventarioDao;
import pucminas.com.pda.database.AppDataBase;
import pucminas.com.pda.domain.ProdutoInventario;

public class ProdutoInventarioRepository {

   private AppDataBase appDataBase;
   private ProdutoInventarioDao produtoInventarioDao;
   private ProdutoInventario produtoInventario;

   public ProdutoInventarioRepository(Application application){
      appDataBase = AppDataBase.getInstance(application);
   }

   public ProdutoInventario Carrega(Date data, Integer localEstoque,Integer produtoId)
   {
      produtoInventarioDao = appDataBase.produtoInventarioDao();
      produtoInventario = produtoInventarioDao.Carrega(data, localEstoque, produtoId);
      return produtoInventario;
   }

   public void Salvar(ProdutoInventario produtoInventario)
   {
      String TAG = "ProdutoInventarioRepository";
      try
      {
         appDataBase.produtoInventarioDao().Salvar(produtoInventario);
      }
      catch (Exception Ex){
         Log.e(TAG, Ex.getMessage());
      }
   }

   public Integer Sequencia(Date data, Integer localEstoque,Integer produtoId)
   {
      Integer sequencia = null;

      produtoInventarioDao = appDataBase.produtoInventarioDao();
      sequencia = produtoInventarioDao.Sequencia(data, localEstoque, produtoId);

      if(sequencia != null) {
         sequencia += 1;
      }
      else
      {
         sequencia = 1;
      }

      return sequencia;
   }
}
