package template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Funci {
    void showInfo();
}
class Empregado implements Funci{

    String nome;
    Integer matricula;

    public Empregado (String nome, Integer matricula){
        this.nome = nome;
        this.matricula = matricula;
    }
    @Override
    public void showInfo() {
        System.out.println("-----------------------------------------------");
        System.out.println("Funci: " + nome);
        System.out.println("Matricula: " + matricula);
    }
}

class Gerente implements Funci {

    String nome;
    Double salario;
    List<Funci> listaEmpregados = new ArrayList<>();
    public Gerente(String nome, Double salario){
        this.nome = nome;
        this.salario = salario;
    }

    public void addFunci(Funci f){
        listaEmpregados.add(f);
    }

    public void removeFunci(Funci f){
        listaEmpregados.remove(f);
    }

    @Override
    public void showInfo() {
        System.out.println("-----------------------------------------------");
        System.out.println("Manager: " + nome);
        System.out.println("Salario: " + salario);

        Iterator<Funci> iteratorFunci = listaEmpregados.iterator();
        while(iteratorFunci.hasNext()){
            Funci funci = iteratorFunci.next();
            funci.showInfo();
        }

    }
}