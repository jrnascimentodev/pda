package pucminas.com.pda.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import pucminas.com.pda.domain.Operador;

@Dao
public interface OperadorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Salvar(Operador... operador);

    @Query("select * from cd_pda_operador where operador_id = :operadorId")
    Operador Carrega(Integer operadorId);

    @Query("select * from cd_pda_operador where nome = :nome")
    Operador Carrega(String nome);
}
