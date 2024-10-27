# Factory Method

Esse padrão decide qual implementação retornar de uma instância baseado
em alguma regra.

Imagine que tenhamos uma classe abstrata chamada Iphone,
e temos, também, várias classe concretas com as versões de cada Iphone,
ex: Iphone2, Iphone3,..., IPhone10.

A classe Factory deverá conseguir criar a instância de cada um dos
tipos de Iphone de acordo com os valores informados.

Ex:

```
abstract class IphoneFactory {
    public iphone orderIphone() {
        iphone device = null;
        device = createIphone();
        device.getHardware();
        device.certificate();
        device.pack();
        return device;
    }
protected abstract Iphone createIphone();
}
```

As classes concretas devem extender a classe abstrata acima e
implementar o métod createIphone de acordo com os requisitos
necessários.

Lembrando que o método orderIphone que é chamado.
