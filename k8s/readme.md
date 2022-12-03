# Notas Quarkus - Redhat

## Consulta Rápida - comandos úteis

### Cronjob

- visualizar os pod's criados

  - kubectl get pod –watch ou get pods

- visualizar os logs dos pod's

  - kubectl logs <nome_pod>

- listar todos os cronjobs

  - kubectl get cronjob

- verificar se o agendamento foi efetuado

  - kubectl get cronjob <nome_pod_cronjob>

- consultar detalhes de um cronjob especifíco

  - kubectl describe cronjob <nome_pod_cronjob>

- excluir um cronjob

  - kubectl delete cronjob <cronjob name>
  - Excluir o trabalho cron remove todos os trabalhos e pods que ele criou e o impede de criar trabalhos adicionais.

- Ref: https://kubernetes.io/docs/tasks/job/automated-tasks-with-cron-jobs/

#########
