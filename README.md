### API 명세서

<a href="https://docs.google.com/spreadsheets/d/1VujvfRe92PRWMG7oExuMF7W5kx3e7d5dfiA6ZG9LdUU/edit?usp=sharing" target="_blank">API 명세서 바로가기</a>


### ERD
<img src="/src/main/resources/static/images/erd.png" width="350" height="250">



### 마일리지 어플리케이션에서 사용하는 쿼리 인덱스 결과

##### 사용한 인덱스
```
CREATE INDEX IDX_PID_UID ON t_review(placeId, userId);
```

##### 사용자가 한 장소에 리뷰를 1개만 작성 했는지 체크 할때 사용하는 쿼리
```
EXPLAIN SELECT * FROM t_review     
        WHERE placeId = '2e4baf1c-5acb-4efb-a1af-eddada31b00f'
        AND userId = '3e4baf1c-5acb-4efb-a1af-e2dada31b00f';
```
 
<img src="/src/main/resources/static/images/idx1.png" width="600" height="100">

##### 특정 장소에 첫 리뷰 인지  체크 할때 사용하는 쿼리
```
EXPLAIN SELECT COUNT(*) FROM t_review
		WHERE placeId = '2e4baf1c-5acb-4efb-a1af-eddada31b00f';
```
<img src="/src/main/resources/static/images/idx2.png" width="600" height="100">

### 애플리케이션 실행 방법
##### 방법1
1-1 : Mysql 3306 포트로 로컬 서비스 올린 후 <a href="/src/main/resources/static/ddl/mileageDDL.sql">DDL 문</a> root 권한으로 실행 <br>
1-2 : <a href="/src/main/resources/static/jar/mileage.jar" download="">jar다운로드</a> 후 window cmd에서 jar 파일 있는 위치에서 java -jar mileage.jar 실행

##### 방법2
2-1 : code -> Download Zip -> Spring boot 지원하는 개인 Tool로 import <br>
2-2 : 개인이 사용하는 db 정보 셋팅 후 <a href="/src/main/resources/static/ddl/mileageDDL.sql">DDL 문</a> 실행 후 어플리케이션 실행
<img src="/src/main/resources/static/images/mysql.png" width="600" height="300">