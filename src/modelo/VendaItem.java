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

    /**
     * Calcula o subtotal de um item multiplicando o preço pela quantidade.
     *
     * @param preco      Preço unitário do CD (deve ser maior que zero)
     * @param quantidade Quantidade de CDs (deve ser maior que zero)
     * @return           O subtotal (preco * quantidade)
     * @throws IllegalArgumentException se preço ou quantidade forem inválidos
     */
    public static BigDecimal calcularSubtotal(BigDecimal preco, int quantidade) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        if (quantidade <= 0)
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    // Getters e Setters
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