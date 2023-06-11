# Intellij Spring Boot와 React 연동

## 주의 사항
1. 본인의 npm 버전과 node의 버전이 pom.xml의 플러그인과 동일한지 확인하기
2.  frontend-maven-plugin 의 버전이 최신 버전인지 확인하기
[버전확인하기](https://github.com/eirslett/frontend-maven-plugin)
3. 수정사항이 생기면 수정후 ./mvnw clean install 통해서 꼭 빌드 후 서버를 실행 해야 한다
4. 서버 시작은 java -jar target/yourstar-0.0.1-SNAPSHOT.jar  키워드로 시작하고 localhost:8080 불러오기