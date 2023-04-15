# meets
![표지(목업 사이즈_2)](https://user-images.githubusercontent.com/68562176/211758408-a963ff66-98dc-4434-af7d-6d2a2274122e.png)

## 📱 밋츠 프로젝트 소개
<!-- ### "저기다"로 주차 상황을 한 눈에 확인하세요 -->
* 성신여자대학교 소프트웨어 경진 대회 출품작입니다.
* 모임의 일정을 관리할 수 있는 서비스로 일정을 잡을 때마다 날짜와 시간을 조율해야 하는 부분이 번거롭게 느껴져 제작하게 되었습니다. 

## 🗓 프로젝트 기간
* 2022.06 ~ 2022.08

## 👤 팀원 소개
* 김유정: Backend
* 윤서영: Backend
* 신현서: Designer
* 양지원: Android
* 이영주: Android

## 🛠 기술 스택
* Design : Figma
* Frontend : Java
* Backend : Java11, Spring Boot2.5, Amazon EC2, Amazon RDS
* Dagabase : MySQL

## 📑 API 명세서
* Swagger를 활용한 API 문서 자동화
* [자세히 보러가기](http://ec2-43-200-32-178.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui.html)

## 🤦🏻‍♀️ 개발하며 했던 고민들
<table>
<tr>
  <td colspan="2">날짜별로 가능한 시간을 어떻게 저장할까?</td>
</tr>
<tr>
  <td width="200px" align="center">
  <img src="https://user-images.githubusercontent.com/68562176/232203265-13fc856b-42e5-432c-9e7f-7599b5090e0f.png" />
  <br>
  참고 이미지
  </td>
  <td>
  [ 고려했던 부분 ]
  <br>
  1. 날짜별로 여러 개의 시간이 가능할 수 있음
  <br>
  → 날짜(date_time)와 시간(av_time)으로 테이블을 분리 
  <br>
  2. 시작시간과 종료시간으로 묶어서 저장 vs 30분 단위로 나눈 후 시작 시간 저장
  <br>  
  → 사용자는 30분 단위로 선택이 가능함.
  <br>  
  → 30분 단위로 저장할 경우 선택된 데이터를 바로 보내면 되기 때문에, 클라이언트 측에서는 가공하는 로직이 필요 없음
  <br>  
  → 시작 시간별로 가능한 인원 수를 저장하여 추천하는 기능의 로직을 편리하게 개발하기 위해 "30분 단위로 나눈 후 시작 시간 저장" 쪽을 선택
  <br><br>
  [ 예시 ]
  <br>
  1월1일에 10:00 ~ 12:30 가능할 때
  <br>
  날짜 테이블에는 1월 1일, 시간 테이블에는 10:00, 10:30, 11:00, 11:30, 12:00 데이터 삽입
  <br>
  - Pull Request 에서 자세히 보기: https://github.com/sswu-meets/meets/pull/47
  </td>
</tr>
</table>
