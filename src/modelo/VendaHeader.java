
package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaHeader {
    private int id;
    private LocalDateTime dataHora;
    private Integer idCliente;
    private int idFuncionario;
    private BigDecimal total;
    private List<VendaItem> itens = new ArrayList<>();
    private int qntItens;

    public VendaHeader() {}

    public VendaHeader(LocalDateTime dataHora, Integer idCliente, int idFuncionario, BigDecimal total) {
        this.dataHora = dataHora;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
        this.total = total;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public List<VendaItem> getItens() { return itens; }
    public void setItens(List<VendaItem> itens) { this.itens = itens; }
    public void addItem(VendaItem item) { this.itens.add(item); }
    public void removeItem(VendaItem item) { this.itens.remove(item); }
    public int getQntItens() { return qntItens; }
    public void setQntItens(int qntItens) { this.qntItens = qntItens; }
}