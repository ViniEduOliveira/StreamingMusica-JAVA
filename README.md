# 🎵 Streaming de Música - Projeto POO Java

Este projeto é uma simulação de um serviço de streaming de música desenvolvido em Java, focado na aplicação prática e consolidação de conceitos de Programação Orientada a Objetos (POO).

## 🎯 Objetivos Concluídos
- [x] Criação e implementação de interfaces (`Reproduzivel`, `Baixavel`).
- [x] Organização do código em pacotes profissionais (`br.com.streaming.*`).
- [x] Sistema completo e funcional operando via console.
- [x] Aplicação de Clean Code (separação de responsabilidades em `view` e `service`).
- [x] Demonstração de domínio de POO (Herança, Polimorfismo, Encapsulamento, Abstração).

## 📁 Estrutura de Pacotes
A arquitetura do projeto foi dividida da seguinte forma:
* `br.com.streaming.entities`: Contém as classes de domínio (Musica, Playlist, Usuarios).
* `br.com.streaming.interfaces`: Contém os contratos do sistema (`Reproduzivel`, `Baixavel`).
* `br.com.streaming.service`: Gerencia a lógica de negócios e estado dos usuários.
* `br.com.streaming.view`: Gerencia a interação com o usuário via terminal (Menus e I/O).

## ⚙️ Como executar
1. Clone o repositório.
2. Compile as classes do pacote `br.com.streaming`.
3. Execute a classe `MainView` localizada no pacote `br.com.streaming.view`.
