# Projeto de Arquitetura com Graylog, MongoDB e Kafka (Versão Python)

Este repositório apresenta um conjunto de serviços escritos em **Python**, estruturados em módulos independentes que utilizam **MongoDB**, **Graylog**, **Kafka**, e execução via Docker. A arquitetura é organizada para garantir observabilidade, autenticação centralizada e comunicação assíncrona.

---

## Módulo 1 — Serviço de Pessoas (Python + FastAPI)

Um serviço REST responsável por cadastrar e gerenciar informações de pessoas.

### Funcionalidades

* Endpoints CRUD usando **FastAPI**.
* Persistência em **MongoDB** utilizando um ODM (ex: *Beanie* ou *Motor*).
* Logs enviados para o **Graylog** através de *GELF Logger*.
* Estrutura desacoplada, separando domínio, infraestrutura e camadas de aplicação.

### Estrutura Geral

```
pessoas/
 ├── domain/
 ├── infra/
 ├── api/
 └── main.py
```

---

## Módulo 2 — Serviço de Autenticação + API Gateway

### Serviço de Autenticação

* Gerenciamento de usuários e autenticação.
* Salvamento de credenciais usando MongoDB + ODM.
* Geração de **JWT**.
* Conversão automática de Basic Auth → Bearer Token.

### API Gateway

* Implementado com **FastAPI**.
* Expõe rotas do serviço de pessoas e autenticação.
* Valida o JWT antes de encaminhar chamadas.
* Centraliza o tráfego entre os serviços.

### Containers Incluídos

* MongoDB
* Graylog
* Serviços Python

---

## Módulo 3 — Kafka Producer + Lambda Listener

### Producer (no Serviço de Autenticação)

* Publica eventos no tópico `forget` sempre que ocorrer solicitação de recuperação de senha.

### Lambda Listener (Python)

* Aplicação Python empacotada para execução serverless.
* Consome mensagens do tópico `forget`.
* Registra no console qual usuário iniciou a recuperação.
* Imagem Docker publicada automaticamente via GitHub Actions.

---

## Módulo 4 — Kafka Consumers com Resiliência

### Características

* Dois consumers executando em containers distintos.
* Ambos possuem o mesmo `groupId` → somente um processa cada mensagem.
* Se um cair, o outro assume.
* Logs enviados ao Graylog.

---

## CI/CD — Publicação de Imagens Docker

Cada módulo possui seu próprio workflow no GitHub Actions.

### Os pipelines executam:

1. Build da imagem Docker.
2. Autenticação no Docker Hub.
3. Publicação automática das imagens.

---

## Tecnologias Utilizadas

* Python 3.12
* FastAPI
* MongoDB + Beanie/Motor
* Kafka
* Graylog
* Docker & Docker Compose
* GitHub Actions

---

## Como Executar

1. Clone este repositório e todas as branches.
2. Em cada módulo, execute:

```
docker build -t <nome_da_imagem>:latest .
```

3. No módulo do gateway, execute:

```
docker-compose up -d
```

---

## 📄 Observação

Este projeto é uma versão em Python inspirada em uma arquitetura modular com foco em escalabilidade, log centralizado e comunicação assíncrona.
