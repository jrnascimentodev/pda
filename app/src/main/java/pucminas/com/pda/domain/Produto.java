package pucminas.com.pda.domain;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "cd_pda_produto", primaryKeys = {"produto_id"})
public class Produto
{
    @ColumnInfo(name = "produto_id")
    private Integer ProdutoId;

    @ColumnInfo(name = "referencia_id")
    private String ReferenciaId;

    @ColumnInfo(name = "descricao")
    private String Descricao;

    @ColumnInfo(name = "imagem")
    private byte[] Imagem;

    @ColumnInfo(name = "preco")
    @NonNull
    private Double Preco;

    @ColumnInfo(name = "preco_divergente")
    @NonNull
    private Integer PrecoDivergente;

    public Integer getProdutoId() {
        return ProdutoId;
    }

    public void setProdutoId(Integer produtoId) {
        ProdutoId = produtoId;
    }

    public String getReferenciaId() {
        return ReferenciaId;
    }

    public void setReferenciaId(String referenciaId) {
        ReferenciaId = referenciaId;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public byte[] getImagem() {
        return Imagem;
    }

    public void setImagem(byte[] imagem) {
        Imagem = imagem;
    }

    @NonNull
    public Double getPreco() {
        return Preco;
    }

    public void setPreco(@NonNull Double preco) {
        Preco = preco;
    }

    @NonNull
    public Integer getPrecoDivergente() {
        return PrecoDivergente;
    }

    public void setPrecoDivergente(@NonNull Integer precoDivergente) {
        PrecoDivergente = precoDivergente;
    }

    public Produto() {
    }

    public Produto(Integer produtoId,
                   String referenciaId,
                   String descricao,
                   byte[] imagem,
                   Double preco,
                   Integer precoDivergente)
    {
        ProdutoId = produtoId;
        ReferenciaId = referenciaId;
        Descricao = descricao;
        Imagem = imagem;
        Preco = preco;
        PrecoDivergente = precoDivergente;
    }

    public static Produto[] populateData() {
        return new Produto[]{
                new Produto(1, "", "PRODUTO A",null, 7.99,0),
                new Produto(2, "", "PRODUTO B",null, 2.99,1),
                new Produto(3, "", "PRODUTO C",null, 8.49,0),
                new Produto(4, "", "PRODUTO D",null, 10.99,1)
        };
    }
}
