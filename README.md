Test 

1) Pré-requisito para execução

   	Apache-maven instalado e configurado.
   	Java versão 1.8 instalado e configurado.
   	
2) Executar o projeto

    Dentro do diretório do projeto executar o comando:
    
    **mvn spring-boot:run**
    
    
    a) Comando curl para enviar o arquivo.
    curl -F file=@"ARQUIVO.TXT" http://localhost:PORTA/uploadFile
    
    b) No projeto tem o arquivo configurando para uso no postman. (GymTest.postman_collection.json)
    