# JUnit

- **assetEquals**
- **assetNull**
- **assetThrows**

### Agrupamento de Assertions:

```java
@Teste
void groupAssertions() {
   int[] numbers = {0, 1, 2, 3, 4};
   assertAll("numbers",
        () -> assertEquals[numbers[0], 1),
        () -> assertEquals[numbers[3], 3),
        () -> assertEquals[numbers[4], 1)
            );
}
```


### Anotações Úteis:

- **@Test:** O método anotado é um teste unitário
- **@BeforEach:** O método anotado será executado ANTES de CADA teste unitário
- **@AfterEach:** O método anotado será executado DEPOIS de CADA teste unitário
- **@BeforeAll:** O método anotado será executado ANTES de TODOS os testes
- **@AfterAll:**  O método anotado será executado DEPOIS de TODOS os testes
- **@Disabled:**  O método ou a classe anotada NÃO será executada na execução dos testes
- **@Timeout:**   O teste falhará se a execução ultrapassar o TEMPO informado


### Assumptions:
Métodos úteis para rodar testes apenas se alguma condição for verdadeira

- **assumeFalse**
- **assumeTrue**