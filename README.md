# Project integrador-qrcode
API Rest para integração com banco para geração QRCode vinculado ao código de barras da guia.

# Getting Started

* Clone this repo using
```
git clone https://github.com/rdgmart/integrador-qrcode.git
  ```
* Move yourself to the appropriate directory: 
```  
cd integrador-qrcode
```
* Run 
```  
./gradlew bootRun
```

## API
- Geração do QRCode para pagamento
- Geração da Imagem do QRCode
- Geração do token necessário para integração
- Consulta do QRCode

### Reference Documentation Banco do Brasil
* [Roteiro credenciais/endpoints](https://api-developers.bb.com.br/docs/oauth/pt-BR/oauth-credentials-api.html)
* [Documentação Para Desenvolvedor](https://apoio.developers.bb.com.br/referency/post/5f4f8342b71fb5001268c9ac)
* [Roteiro como usar Sandbox](https://apoio.developers.bb.com.br/sandbox/spec/post/5fe9e7f13b02bd0012eca9f1)
* [Intruções para realizar teste](https://apoio.developers.bb.com.br/referency/post/602446d5e7ffb0001294fc02)
* [Credenciais para produção](https://apoio.developers.bb.com.br/referency/post/6050dda3737e1c0012e2d00e)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Native Reference Guide](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Configure the Spring AOT Plugin](https://docs.spring.io/spring-native/docs/0.10.0/reference/htmlsingle/#spring-aot-gradle)

## Spring Native

This project has been configured to let you generate either a lightweight container or a native executable.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started with Spring Native.
Docker should be installed and configured on your machine prior to creating the image, see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.10.0/reference/htmlsingle/#getting-started-buildpacks).

To create the image, run the following goal:

```
$ ./gradlew bootBuildImage
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 integradorqrcode:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM native-image compiler should be installed and configured on your machine, see [the Getting Started section of the reference guide](https://docs.spring.io/spring-native/docs/0.10.0/reference/htmlsingle/#getting-started-native-build-tools).

To create the executable, run the following goal:

```
$ ./gradlew nativeBuild
```

Then, you can run the app as follows:
```
$ build/native-image/integradorqrcode
```
### Database
* [H2](https://www.h2database.com/html/main.html)
  
After starting the application, the database can access:
```  
http://localhost:8080/h2-console)
```
