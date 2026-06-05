# Instruções de Compilação, Configuração e Execução
---
### 1. Configuração do Banco de Dados Local (XAMPP)

- Certifique-se de que o servidor MySQL do XAMPP foi criado e está ativo.
- Acesse o painel de controle do XAMPP e inicie o módulo MySQL.
- A classe Conexao.java já está pré-configurada para o padrão:

    - **URL:** `jdbc:mysql://localhost:3306/defaultdb`
    - **Usuário:** `root`
    - **Senha:** em branco (sem nenhuma senha)

- Se a conexão local utiliza uma porta diferente de 3306, possua uma senha definida para o usuário root ou tem outra configuração padrão, é necessário ajustar manualmente as variáveis `url`, `user` e `password` no método `getConn()` dentro de [Conexão.java](src/Conexao.java) antes de compilar.

- Utilize a [query](.sql) na schema para criar a base de dados. A schema deve ser chamada `defaultdb` para corresponder à configuração da classe Conexao. Caso queira utilizar outro nome, atualize o final da URL de conexão no código.

- **AVISO**: O sistema não é compatível com SQLite, sem um um servidor MySQL local ou no caso de falha na conexão, o sistema perguntará se deseja utilizar variáveis do próprio código para simular a conexão, permitindo o teste das funcionalidades de POO (menus, entidades e lógica de negócio) mesmo sem um banco de dados ativo, o que elimina a necessidade de um ambiente de banco de dados apenas para avaliar a POO. Porém, o console exibirá o aviso `ERRO DE CONEXÃO` por não ser o meio recomendado.
---

### 2. Compilação E Execução (Terminal da IDE)

- Clone o repositório e abra a pasta do projeto em sua IDE.

A compilação deve ser realizada a partir da **raiz do projeto** (fora das pastas [src](src) e [lib](lib)) para respeitar a estrutura de pacotes do Java.

- Abra o seu terminal na raiz do projeto e execute o comando abaixo:
```
javac src/Conexao.java src/Main.java
```
- ainda no terminal, execute o comando abaixo para iniciar o programa:
```
java -cp ".;lib/*" src.Main
```
- No MacOS ou Linux, deve ser utilizado `:` ao invés de `;` para separar os caminhos:
```
java -cp ".:lib/*" src.Main
```
---