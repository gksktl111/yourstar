package com.example.yourstar.domains;

// 임포트 할때 jakarta로 임포트 해야됨 보니까 버전 차이로 인한 오류 같음(boot 3.x 부터는 javax)


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
@Table(name = "User")
public class User {

    // id를 pk로 사용함
    @Id
    private String id;
    private String pw;
    private String name;
    private String sex;
    private int age;
}
