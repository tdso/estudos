package template;

public class Client {

    public static void main (String[] args){

        Funci empregado1 = new Empregado("Joao", 120);
        Funci empregado2 = new Empregado("Maria", 122);
        Funci empregado3 = new Empregado ("Daniel", 124);
        Funci empregado4 = new Empregado("Samuel", 125);
        Gerente gerenteGeral = new Gerente("Silvia", 21000.00);
        Gerente gerenteOperacao = new Gerente("Carmo", 19000.00);
        Gerente gerenteComercial = new Gerente("Lima", 18000.00);
        gerenteComercial.addFunci(empregado2);
        gerenteComercial.addFunci(empregado4);
        gerenteOperacao.addFunci(empregado1);
        gerenteOperacao.addFunci(empregado3);
        gerenteGeral.addFunci(gerenteComercial);
        gerenteGeral.addFunci(gerenteOperacao);
        gerenteGeral.showInfo();
    }
}
