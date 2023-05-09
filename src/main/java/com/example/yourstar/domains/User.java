package com.example.yourstar.domains;

// 부트를 2.5.0으로 내려서 문제 해결함

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


// 필드의 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
// 를 자동으로 생서해줌
@Data
@Builder
// 모든 필드의 생성자들을 자동으로 만들어줌
@AllArgsConstructor
// 파라미터가 없는 필드의 생성자들을 만들어줌
@NoArgsConstructor
// 해당클래스를 entity 클래스라고 설정하고 이름은 User로
@Entity(name = "User")
// db의 User 테이블과 클래스를 매핑 시킴
@Table(name = "user")
public class User {

    // id를 pk로 사용함
    @Id
    private String userId;
    private String userName;
    private String name;
    private String sex;
    private int age;
}
