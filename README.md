# texteditor
JAVA, SPRINGBOOT, MAVEN TEXTEDITOR TO STUDY STACK

## Repository 생성 이유
> STACK 구조를 사용해보고 싶어 만든 간단한 텍스트 에디터 사이드 프로젝트입니다.
> 프론트엔드에서 자료를 저장하지 않고, 모든 자료는 서버에서만 저장합니다.
> 서버 부하를 고려하지 않은 stack 과 친숙해 지기 위해 만든 프로젝트 입니다.  
--------------------------------------------------------------------------------------------------

## 주요 파일 구조
+ textEditor : 프로젝트
  + src : 소스코드
    + main/java/com/side : java 파일
      + /textEditor/DTO 
        + TextEditorDTO : 텍스트에디터 Data Transfer Object 
      + /controller
        + IndexController.java : 첫 시작 화면 redirect
        + TextEditorController.java : 응답 처리
      + /service
        + TextEditorService : 텍스트에디터 로직 처리
    + main/resources
      + application.properties : 리소스 파일(css, js 파일) 경로 지정
    + main/resources/static
      + css/textEditor : css 파일
      + js/textEditor : javascript 파일
    + main/resources/templates
      + index.html : 첫 시작 화면 html 파일
      + /textEditor : 텍스트 에디터 html 파일
  
--------------------------------------------------------------------------------------------------

## 주요 기능
  save : 작성한 메모 저장
  reset : 작성 글 초기화
  undo : 작성 중인 마지막 줄 제거
  redo : 가장 최신에 제거한 줄 불러오기
  
---------------------------------------------------------------------------------------------------
## 에러 2021-04-08
  마지막 작성 줄이 같은 패턴으로 반복 될 시, 모두 삭제 됨  
  예시] 
  ```
  1번째 줄
  2번째 줄
  3
  (엔터)
  3
  (엔터)
  3
  (엔터)
  ```
  3(엔터) 패턴인 총 6줄이 삭제 됨
  
---------------------------------------------------------------------------------------------------

## 또 다른 REPOSITORY
[개발자로서 기록]  
개발에 관련된 공부를 기록하는 REPOSITORY 입니다.  
https://github.com/rocket-developer-sketch/ajansim/  

[알고리즘 문제 모음]  
마주쳤던 알고리즘 문제과 그 풀이들을 모아놓은 REPOSITORY  
https://github.com/rocket-developer-sketch/algorithmQuestions/tree/main/AlgorithmQuestions

