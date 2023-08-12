package pucminas.com.pda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import pucminas.com.pda.domain.Produto;

@Dao
public interface ProdutoDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Salvar(Produto... produto);

    @Query("select * from cd_pda_produto where produto_id = :produtoId")
    Produto Carrega(Integer produtoId);
}
