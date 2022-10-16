package br.com.tdso.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "getCarteira",
                query = "select c from Carteira c"),
        @NamedQuery(name = "getCarteiraById",
                query = "select c from Carteira c where c.idCarteira = ?1"),
        @NamedQuery(name = "persist",
                query = "update Carteira c set c.mesCarteira = ?1, c.anoCarteira = ?2, c.nomeCarteira = ?3 where c.idCarteira = ?4")
})
// @Table(name = "crt")
public class Carteira {

    @Id
    private Long idCarteira;
    @Min(1)
    @Max(12)
    private int mesCarteira;
    @Min(2022)
    @Max(2099)
    private int anoCarteira;
    @NotBlank(message = "informe o nome da carteira - nao deixe em branco")
    @NotNull(message = "informe o nome da carteira - nao pode ser nulo")
    private String nomeCarteira;
    // private Date dataAtualizacao;
    // private Date dataVingencia;

    public Carteira() {}

    public Long getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(Long idCarteira) {
        this.idCarteira = idCarteira;
    }

    public int getMesCarteira() {
        return mesCarteira;
    }

    public void setMesCarteira(int mesCarteira) {
        this.mesCarteira = mesCarteira;
    }

    public int getAnoCarteira() {
        return anoCarteira;
    }

    public void setAnoCarteira(int anoCarteira) {
        this.anoCarteira = anoCarteira;
    }

    public String getNomeCarteira() {
        return nomeCarteira;
    }

    public void setNomeCarteira(String nomeCarteira) {
        this.nomeCarteira = nomeCarteira;
    }
    /*
     * public Date getDataAtualizacao() { return dataAtualizacao; }
     * 
     * public void setDataAtualizacao(Date dataAtualizacao) { this.dataAtualizacao =
     * dataAtualizacao; }
     * 
     * public Date getDataVingencia() { return dataVingencia; }
     * 
     * public void setDataVingencia(Date dataVingencia) { this.dataVingencia = dataVingencia; }
     * 
     */

}
