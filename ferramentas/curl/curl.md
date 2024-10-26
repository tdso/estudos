### Instalar parallel

Pode ser usado junto com o curl

```
sudo apt-get update
sudo apt-get install parallel
parallel --version
```

### Fazendo chamadas em paralelo

cat urls.txt | xargs -n 1 -P 10 curl -O

Neste comando:

-n 1 especifica que xargs deve passar uma URL por vez para curl.
-P 10 permite que até 10 processos curl sejam executados simultaneamente.

### Usando parallel

cat urls.txt | parallel -j 10 curl -O

Neste comando:

-j 10 especifica que até 10 processos curl podem ser executados simultaneamente.

### Curl e Operador &

Você também pode usar o operador & para executar comandos em segundo plano:

curl -O http://example.com/file1 &
curl -O http://example.com/file2 &
curl -O http://example.com/file3 &
wait

Neste exemplo, cada comando curl é executado em segundo plano, e o comando wait aguarda a conclusão de todos os processos.

## Medindo o Tempo das Execuções

### Usando Time

time cat urls.txt | xargs -n 1 -P 10 curl -O

### Usando Parallel e Time

time cat urls.txt | parallel -j 10 curl -O

### Usando Processos em Segundo Plano com Time

```
time (
  curl -O http://example.com/file1 &
  curl -O http://example.com/file2 &
  curl -O http://example.com/file3 &
  wait
)
```

### Usando curl diretamente com time

Se você estiver usando uma versão do curl que suporta a opção -w, você pode medir o tempo de cada chamada individualmente:

```
curl -w "@curl-format.txt" -o /dev/null -s http://example.com/file1 &
curl -w "@curl-format.txt" -o /dev/null -s http://example.com/file2 &
curl -w "@curl-format.txt" -o /dev/null -s http://example.com/file3 &
wait
```

Crie o arquivo curl-format.txt com o seguinte conteúdo:

```
time_namelookup:  %{time_namelookup}\n
time_connect:  %{time_connect}\n
time_appconnect:  %{time_appconnect}\n
time_pretransfer:  %{time_pretransfer}\n
time_redirect:  %{time_redirect}\n
time_starttransfer:  %{time_starttransfer}\n
time_total:  %{time_total}\n

```
