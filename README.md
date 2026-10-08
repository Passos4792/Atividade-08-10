# Elo Acesso

Atividade acadêmica com cadastro, login, logout e três perfis: ALUNO, PROFESSOR e ADMINISTRADOR. Base visual e organização inspiradas no Elo, com seções separadas por comentários.

## Tecnologias

Java 17, Maven 3.9, Spring Boot 3.5.16, Spring Security, Thymeleaf, Spring Data MongoDB e Spring Session MongoDB 3.5.7.

## Executar

Crie um cluster no MongoDB Atlas, um usuário de banco com acesso somente ao banco `elo_acesso` e libere seu IP. Copie a conexão SRV do Atlas. No PowerShell, dentro da pasta do projeto:

```powershell
$env:MONGODB_URI='mongodb+srv://USUARIO:SENHA_CODIFICADA@SEU_CLUSTER/elo_acesso?retryWrites=true&w=majority&tls=true'
$env:SPRING_PROFILES_ACTIVE='local'
mvn spring-boot:run
```

Abra http://localhost:8080. Substitua os exemplos por seus dados e não publique credenciais. A configuração está em `src/main/resources/application.properties`. O perfil local permite HTTP; para hospedar, use o perfil `producao` com HTTPS.

## Organização

`aplicacao` contém modelos, formulários, repositórios, serviços e controladores. `configuracao` reúne segurança, sessões e tema. `templates` contém as páginas e o layout compartilhado. `static` contém apenas a apresentação. Não há arquivos de testes.

## Banco e acesso

Usuários ficam na coleção `usuarios`, com email único e senha em hash BCrypt. Sessões ficam na coleção `sessoes`, com expiração após 30 minutos sem atividade. O cadastro público sempre cria ALUNO. A atribuição dos demais perfis pelo responsável pelo banco está explicada no TXT.

## Personalização e entrega

O tema está em `static/temas/elo/tema.css`, selecionado pela variável `APP_TEMA`. O layout compartilhado fica em `templates/fragmentos/estrutura.html`.

Consulte `PASSO_A_PASSO.txt` para configurar, executar, hospedar e publicar no GitHub com Gitflow. A documentação acadêmica está em `documentacao.pdf`, com campos de identificação para preencher. O repositório deve ser público e separado do PFC.
