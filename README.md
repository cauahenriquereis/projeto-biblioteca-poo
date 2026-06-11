# 📚 Sistema de Gerenciamento de Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em Java, aplicando os principais conceitos de Programação Orientada a Objetos como parte do projeto final da disciplina de POO do INATEL.

---

## 📋 Sobre o Projeto

O sistema permite o gerenciamento completo de uma biblioteca, com dois perfis de acesso: **Bibliotecário** e **Usuário**. O Bibliotecário é responsável por gerenciar o catálogo e cadastrar usuários, enquanto o Usuário pode realizar empréstimos, devoluções e buscar itens disponíveis.

---

## ✅ Funcionalidades

### Bibliotecário
- Cadastrar itens no catálogo (Livro, Revista e DVD)
- Remover itens do catálogo
- Cadastrar usuários
- Listar catálogo completo

### Usuário
- Listar catálogo completo
- Buscar itens por título
- Realizar empréstimo de itens disponíveis
- Realizar devolução com cálculo automático de multa por atraso (R$ 1,00/dia)

### Sistema
- Persistência de dados entre sessões (catálogo, usuários e empréstimos salvos em arquivos `.txt`)
- Validação de disponibilidade de itens
- Limite de 3 empréstimos simultâneos por usuário

---

## 🧱 Conceitos de POO Aplicados

| Conceito | Como é aplicado |
|---|---|
| **Herança** | `Livro`, `Revista` e `DVD` estendem `ItemBiblioteca`; `Usuario` e `Bibliotecario` estendem `Pessoa` |
| **Polimorfismo** | `exibirDetalhes()` se comporta diferente em cada tipo de item |
| **Abstração** | `ItemBiblioteca` e `Pessoa` são classes abstratas |
| **Encapsulamento** | Atributos privados com getters/setters controlados |
| **Composição** | `Biblioteca` contém `Catalogo`; `Catalogo` contém `List<ItemBiblioteca>` |
| **Interface** | Interface `Pesquisavel` implementada pelo `Catalogo` |
| **Coleções** | `ArrayList` para gerenciar listas de itens, usuários e empréstimos |
| **Exceções** | `ItemIndisponivelException` e `LimiteEmprestimoException` customizadas |
| **Arquivos** | Leitura e escrita de dados com `java.nio` |
| **GUI** | Interface gráfica desenvolvida com Swing |
| **Enum** | `StatusEmprestimo`: `EM_ANDAMENTO`, `DEVOLVIDO`, `ATRASADO` |

---

## 🛠️ Tecnologias

- **Java 21**
- **Swing** — Interface gráfica
- **java.nio** — Leitura e escrita de arquivos
- **Maven** — Gerenciamento de dependências
- **IntelliJ IDEA** — IDE de desenvolvimento
- **Git/GitHub** — Controle de versão

---

## 📁 Estrutura de Pacotes

```
br.inatel.poo.biblioteca
├── exception
│   ├── ItemIndisponivelException
│   └── LimiteEmprestimoException
├── gui
│   ├── MenuBibliotecario
│   ├── MenuUsuario
│   ├── TelaBuscarItem
│   ├── TelaCadastroItem
│   ├── TelaCadastroUsuario
│   ├── TelaCatalogoCompleto
│   ├── TelaPrincipal
│   ├── TelaRealizarDevolucao
│   ├── TelaRealizarEmprestimo
│   └── TelaRemoverItem
├── model
│   ├── Bibliotecario
│   ├── DVD
│   ├── Emprestimo
│   ├── ItemBiblioteca
│   ├── Livro
│   ├── Pessoa
│   ├── Revista
│   ├── StatusEmprestimo
│   └── Usuario
├── persistence
│   └── GerenciadorArquivos
├── service
│   ├── Biblioteca
│   └── Catalogo
└── Main
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 21 instalado
- IntelliJ IDEA instalado

### Passos

**1. Clonar o repositório**
```bash
git clone https://github.com/cauahenriquereis/projeto-biblioteca-poo.git
```

**2. Abrir no IntelliJ**
- Abra o IntelliJ IDEA
- Clique em `File` → `Open`
- Selecione a pasta do projeto clonado
- Clique em `OK`

**3. Executar**
- Localize a classe `Main.java` em `br.inatel.poo.biblioteca`
- Clique no botão ▶ ao lado do método `main`


