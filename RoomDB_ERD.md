# Room DB 구조 및 ERD 설계

---

##  1. 주요 Entity 목록

| Entity | 설명 | 주요 역할 |
|--------|------|-----------|
| GoalEntity | 공부 목표 | 자격증/시험/과목 등 상위 목표 단위 |
| TodoEntity | 할 일(Task) | 각 목표에 속한 공부 항목 |
| RoutineLogEntity | 루틴 수행 기록 | 날짜별 공부 기록 |
| AwardEntity | 어워드/배지 | 성취에 따라 발급되는 보상 |

---

##  2. Entity 상세 정의

###  GoalEntity

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | Int (PK) | 자동 생성되는 목표 ID |
| title | String | 목표명 (예: 정보처리기사) |
| tag | String | 태그 (예: 자격증, 어학 등) |
| deadline | String | 마감일 (`yyyy-MM-dd`) |
| createdAt | String | 생성일 (`yyyy-MM-dd`) |

---

###  TodoEntity

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | Int (PK) | 할 일 ID |
| goalId | Int (FK) | 연결된 목표 ID |
| content | String | 할 일 내용 |
| dueDate | String | 기한일 |
| isDone | Boolean | 완료 여부 |
| repeatType | String | 반복 설정 (none, daily, weekly 등) |

---

###  RoutineLogEntity

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | Int (PK) | 루틴 기록 ID |
| date | String | 날짜 (`yyyy-MM-dd`) |
| goalId | Int (FK) | 연관된 목표 ID |
| duration | Int | 공부 시간 (분 단위) |
| memo | String? | 공부한 내용 요약 |

---

###  AwardEntity

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| id | Int (PK) | 어워드 ID |
| name | String | 배지 이름 |
| earnedAt | String | 획득일 (`yyyy-MM-dd`) |
| description | String | 배지 설명 |
| type | String | 조건 구분 (예: 연속 7일, 100시간 등) |

---

## 3. ERD(Entity Relationship Diagram) 구조 설명

```
GoalEntity (1)
   └───< TodoEntity (N)

GoalEntity (1)
   └───< RoutineLogEntity (N)

AwardEntity (독립적, 조건 기반)
```

- Goal 1개 → Todo 여러 개  
- Goal 1개 → RoutineLog 여러 개  
- Award는 독립적으로 조건 달성 시 자동 생성

---

## 4. 예시 데이터 흐름 시나리오

1. 사용자가 “정보처리기사” 목표를 생성 → GoalEntity 저장  
2. 해당 목표에 “1과목 정리”, “기출 풀이” 등의 할 일 생성 → TodoEntity  
3. 루틴 수행 시 “1시간 공부” 기록 입력 → RoutineLogEntity  
4. 루틴이 7일 연속이면 → AwardEntity에 “연속 7일 달성” 배지 저장

