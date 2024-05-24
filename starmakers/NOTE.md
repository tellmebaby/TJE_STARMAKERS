
# user 컬럼 나열
user_no, name, id, email, password, phone, address, reg_date, upd_date, gender, birth

name, id, email, password, phone, address, gender, birth

# user 컬럼 매핑 변수
#{name}, #{id}, #{email}, #{password}, #{phone}, #{address}, #{gender}, #{birth}


## 로그인/비로그인
```
 <!-- 비 로그인 시 -->
<th:block sec:authorize="isAnonymous()">

</th:block>
```

```
 <!-- 로그인 시 -->
<th:block sec:authorize="isAuthenticated()">

</th:block>
```

## 사용자 관리자 타임리프 시큐리티
```
    <!-- 사용자 메뉴 -->
    <th:block sec:authorize="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
        <li><a href="/user">사용자</a></li>
    </th:block>
    <!-- 관리자 메뉴 -->
    <th:block sec:authorize="hasRole('ROLE_ADMIN')">
        <li><a href="/admin">관리자</a></li>
    </th:block>
```