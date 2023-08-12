package pucminas.com.pda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;

import pucminas.com.pda.domain.ProdutoInventario;

@Dao
public interface ProdutoInventarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Salvar(ProdutoInventario... produtoInventario);

    @Query("select * from cd_pda_produto_inventario where data = :data and local_estoque = :locaEstoque and produto_id = :produtoId")
    ProdutoInventario Carrega(Date data, Integer locaEstoque, Integer produtoId);

    @Query("select MAX(COALESCE(produto_id, 0)) as item_id from cd_pda_produto_inventario  "
            + "where data = :data and local_estoque = :localEstoque and produto_id = :produtoId ")
    Integer Sequencia(Date data, Integer localEstoque,Integer produtoId);

}
