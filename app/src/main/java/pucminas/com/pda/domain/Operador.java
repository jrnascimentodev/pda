package pucminas.com.pda.domain;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "cd_pda_operador", primaryKeys = {"operador_id"})
public class Operador implements Serializable {

    @ColumnInfo(name = "operador_id")
    @NonNull
    private Integer OperadorId;

    @ColumnInfo(name = "nome")
    @NonNull
    private String Nome;

    @ColumnInfo(name = "senha")
    @NonNull
    private String Senha;

    public Integer getOperadorId() {
        return OperadorId;
    }

    public void setOperadorId(Integer operadorId) {
        OperadorId = operadorId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public Operador() {
    }

    public Operador(Integer operadorId,
                    String nome,
                    String senha)
    {
        OperadorId = operadorId;
        Nome = nome;
        Senha = senha;
    }

    public static Operador[] populateData() {
        return new Operador[]{
                new Operador(1, "SUPERVISOR", "1234")
        };
    }
}
