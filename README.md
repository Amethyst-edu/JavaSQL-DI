## como conectar o banco em nuvem no mysql workbench 

- crie uma nova conexao;
- deixe o **connection method** como Standard (TCP/IP);
- preencha os campos na aba parameters com as informações do **db.properties** (host, port, e user ou username);
- em seguida clique em **store in vault** e preencha a senha do banco de dados, também no **db.properties**;
- preencha o campo **schema** com defaultdb;
- vá na aba SSL e em **Use SSL**, selecione required;
- pode testar a conexao e abrir.
