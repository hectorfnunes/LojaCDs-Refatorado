package modelo;

import java.math.BigDecimal;

public class VendaItem {
    private int id;
    private int idVenda;
    private int idCd;
    private int quantidade;
    private BigDecimal subtotal;

    public VendaItem() {}

    public VendaItem(int idCd, int quantidade, BigDecimal subtotal) {
        this.idCd = idCd;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenda() { return idVenda; }
    public void setIdVenda(int idVenda) { this.idVenda = idVenda; }
    public int getIdCd() { return idCd; }
    public void setIdCd(int idCd) { this.idCd = idCd; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}