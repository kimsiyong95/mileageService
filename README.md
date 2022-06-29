### API 명세서

<a href="https://docs.google.com/spreadsheets/d/1VujvfRe92PRWMG7oExuMF7W5kx3e7d5dfiA6ZG9LdUU/edit?usp=sharing">API 명세서 바로가기</a>


### ERD
<img src="/src/main/resources/static/images/erd.png" width="350" height="250">



### 마일리지 어플리케이션에서 사용하는 쿼리 인덱스 결과

##### 사용한 인덱스
```
CREATE INDEX IDX_PID_UID ON t_review(placeId, userId);
```

```
EXPLAIN SELECT * FROM t_review     
        WHERE placeId = '2e4baf1c-5acb-4efb-a1af-eddada31b00f'
        AND userId = '3e4baf1c-5acb-4efb-a1af-e2dada31b00f';
```

<img src="/src/main/resources/static/images/idx1.png" width="600" height="100">

```
EXPLAIN SELECT COUNT(*) FROM t_review
		WHERE placeId = '2e4baf1c-5acb-4efb-a1af-eddada31b00f';
```
<img src="/src/main/resources/static/images/idx2.png" width="600" height="100">

### 애플리케이션 실행 방법