package pucminas.com.pda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pucminas.com.pda.dao.OperadorDao;
import pucminas.com.pda.dao.ProdutoDao;
import pucminas.com.pda.dao.ProdutoInventarioDao;
import pucminas.com.pda.domain.Operador;
import pucminas.com.pda.domain.Produto;
import pucminas.com.pda.domain.ProdutoInventario;
import pucminas.com.pda.util.DateConverter;

@Database(entities = {Operador.class,
                      Produto.class,
                      ProdutoInventario.class}, version = 1)

@TypeConverters(DateConverter.class)
public abstract class AppDataBase  extends RoomDatabase
{

   private final static String DB_NAME="gestor_pda";
   private static AppDataBase INSTANCE;
   private static final int NUMBER_OF_THREADS = 4;
   public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

   public abstract OperadorDao operadorDao();
   public abstract ProdutoDao produtoDao();
   public abstract ProdutoInventarioDao produtoInventarioDao();

   public static AppDataBase getInstance(Context context)
   {
      if (INSTANCE == null)
      {
         INSTANCE = Room.databaseBuilder(context, AppDataBase.class, DB_NAME)
                 .allowMainThreadQueries()
                 .addCallback(new Callback() {
                    @Override
                    public void onCreate(SupportSQLiteDatabase db) {
                       super.onCreate(db);
                       Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                          @Override
                          public void run()
                          {
                             getInstance(context).operadorDao().Salvar(Operador.populateData());
                             getInstance(context).produtoDao().Salvar(Produto.populateData());
                          }
                       });
                    }
                 })
                 .build();
      }

      return INSTANCE;
   }

   @Override
   public void close()
   {
      super.close();
      INSTANCE = null;
   }
}
