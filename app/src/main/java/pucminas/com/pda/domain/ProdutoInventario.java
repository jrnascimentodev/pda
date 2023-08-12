package pucminas.com.pda.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "cd_pda_produto_inventario", primaryKeys = {"inventario_id"})
public class ProdutoInventario {

    @ColumnInfo(name = "inventario_id")
    private Integer InventarioId;

    @ColumnInfo(name = "data")
    private Date Data;

    @ColumnInfo(name = "local_estoque")
    private Integer LocalEstoque;

    @ColumnInfo(name = "produto_id")
    private Integer ProdutoId;

    @ColumnInfo(name = "quantidade_atual")
    private Double QuantidadeAtual;

    @ColumnInfo(name = "quantidade_apurada")
    private Double QuantidadeApurada;

    @ColumnInfo(name = "quantidade")
    private Double Quantidade;

    public Integer getInventarioId() {
        return InventarioId;
    }

    public void setInventarioId(Integer inventarioId) {
        InventarioId = inventarioId;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }

    public Integer getLocalEstoque() {
        return LocalEstoque;
    }

    public void setLocalEstoque(Integer localEstoque) {
        LocalEstoque = localEstoque;
    }

    public Integer getProdutoId() {
        return ProdutoId;
    }

    public void setProdutoId(Integer produtoId) {
        ProdutoId = produtoId;
    }

    public Double getQuantidadeAtual() {
        return QuantidadeAtual;
    }

    public void setQuantidadeAtual(Double quantidadeAtual) {
        QuantidadeAtual = quantidadeAtual;
    }

    public Double getQuantidadeApurada() {
        return QuantidadeApurada;
    }

    public void setQuantidadeApurada(Double quantidadeApurada) {
        QuantidadeApurada = quantidadeApurada;
    }

    public Double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Double quantidade) {
        Quantidade = quantidade;
    }

    public ProdutoInventario() {
    }

    public ProdutoInventario(Integer inventarioId,
                             Date data,
                             Integer localEstoque,
                             Integer produtoId,
                             Double quantidadeAtual,
                             Double quantidadeApurada,
                             Double quantidade)
    {
        InventarioId = inventarioId;
        Data = data;
        LocalEstoque = localEstoque;
        ProdutoId = produtoId;
        QuantidadeAtual = quantidadeAtual;
        QuantidadeApurada = quantidadeApurada;
        Quantidade = quantidade;
    }
}
