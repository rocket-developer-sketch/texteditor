# texteditor
JAVA, SPRINGBOOT, MAVEN TEXTEDITOR TO STUDY STACK

## Repository 생성 이유
> This is a simple text editor side project I created to get hands-on experience with the stack data structure.
> The frontend doesn't store any data — everything is saved on the server.
> The project was built without considering server load, focusing instead on becoming more familiar with stacks.
> 
> STACK 구조를 사용해보고 싶어 만든 간단한 텍스트 에디터 사이드 프로젝트입니다.
> 프론트엔드에서 자료를 저장하지 않고, 모든 자료는 서버에서만 저장합니다.
> 서버 부하를 고려하지 않은 stack 과 친숙해 지기 위해 만든 프로젝트 입니다.

--------------------------------------------------------------------------------------------------

## 주요 파일 구조 ( Project Structure )
+ textEditor :
  + src :
    + main/java/com/side :
      + /textEditor/DTO 
        + TextEditorDTO : text editor data transfer object 
      + /controller
        + IndexController.java : redirect to home
        + TextEditorController.java : processing response
      + /service
        + TextEditorService : logic
    + main/resources
      + application.properties : environment file
    + main/resources/static
      + css/textEditor : css files
      + js/textEditor : javascript files
    + main/resources/templates
      + index.html : home html
      + /textEditor : text editor html
  
--------------------------------------------------------------------------------------------------

## 주요 기능 ( Main features )
  save : 작성한 메모 저장
  reset : 작성 글 초기화
  undo : 작성 중인 마지막 줄 제거
  redo : 가장 최신에 제거한 줄 불러오기
  
---------------------------------------------------------------------------------------------------
## 알려진 이슈 2021-04-08 ( Known issues )
Known Issue:
  When the last input line is repeated with the same pattern, all matching lines are deleted.
  Example:
  ```
    Line 1  
    Line 2  
    3  
    (Enter)  
    3  
    (Enter)  
    3  
    (Enter)
   ```
   In this case, all six lines following the repeated "3 (Enter)" pattern are removed.
   
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

  
