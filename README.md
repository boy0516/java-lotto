# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# todo
- [x] 로또 래퍼 클래스 구현
  - [x] 로또 생성자 빈값 입력 예외 처리 검증
- [x] 구입금액 입력 기능
  - [ ] 구입금액 최소 단위 제한(백원 이하 오류 처리)
- [X] 랜덤 로또 번호 발급 기능
  - [x] 로또 래퍼 클래스 반환
  - [x] 로또 번호 6자리 생성
  - [x] 로또 번호 범위 1~45 검증
  - [x] 로또 번호 원자성 보장 검증
  - [x] 로또 번호 정렬 검증
- [x] 당첨 번호 입력 기능
- [x] 당첨 통계 산출 기능
  - [x] 3개 일치 개수 산출
  - [x] 4개 일치 개수 산출
  - [x] 5개 일치 개수 산출
  - [x] 6개 일치 개수 산출
- [x] 수익률 산출 기능
- [X] 보너스 번호 입력 기능
- [X] 2등 상금 추가
- [ ] 수동 발급 추가
