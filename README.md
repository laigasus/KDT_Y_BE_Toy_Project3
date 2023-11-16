## 개요

■ 프로젝트명

- 여행 여정을 기록과 관리하는 서비스

■ 기간, 참여인원

- 2023.11.10 ~ 2023.11.16
- 옥재욱(팀장), 이유상, 홍용현, 김정훈, 서은

■ 목적

- 협업 및 팀워크 증진을 통하여 동일한 목표 달성 및 성취

---

## 아키텍처 패턴

MVC

---

## 진행 방식

Agile - Scrum

XP - PairProgramming(Intellij codewithme)

---

## 구현 환경

- Java 17
- Spring Boot
- Spring security
- Docker
- Restdocs
- intellij
- gradle

---

## 브랜치 전략

Git-flow 사용

---

## 추가된 기능

### 회원 회원가입 기능

회원은 회원가입을 할 수 있습니다.

기본 정보는 ID 역할로 이메일 주소와, 비밀번호, 이름 입니다.

### 회원 로그인 기능

이메일과 비밀번호로 로그인할 수 있습니다.

### 여행 정보 ‘좋아요’ 기능

회원은 본인을 포함한 타인의 여행 정보에 ‘좋아요’를 표시할 수 있습니다.

여행 정보 조회 시 ‘좋아요’ 개수도 같이 출력되어야 합니다.

### ‘좋아요’ 리스트 조회 기능

본인이 ‘좋아요’를 누른 여행 리스트를 조회할 수 있습니다.

### 여행 정보 댓글 기능

본인을 포함한 타인의 여행 정보에 댓글을 달 수 있습니다.

여행 정보 조회 시 ‘댓글’ 리스트도 같이 출력되어야 합니다.

### 오픈 API 활용 위치 정보 표현

오픈API를 활용하여 위치 정보를 표현합니다.

### 레이어별 (Controller, Service, Repository) 테스트 케이스

Repository 부분은 커스텀하여 사용한 것이 없기 때문에 제외했습니다

### 예외 처리

회원 가입 시 이메일 형식이 맞지 않으면 오류 메세지를 출력합니다.

로그인에 실패하면, 오류 메세지를 출력합니다.

---

## API

Restdocs 참고

---

## ERD

### DB Diagram

![accommodation](https://github.com/YBE-lucky7/KDT_Y_BE_Toy_Project3/assets/26517061/bf66ace3-7303-4b1c-9c66-00b095679ed4)


### Persistence View

![graphviz (1)](https://github.com/YBE-lucky7/KDT_Y_BE_Toy_Project3/assets/26517061/83d4e4d7-7d8c-4132-b741-0ad20d91e655)


---

## 흐름도

[ItineraryService_insertItineraries.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/d1862494-29db-4a4f-ad4a-1bdcb7ef1641/ItineraryService_insertItineraries.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> ItineraryService : insertItineraries
    activate ItineraryService
    ItineraryService ->> TripNotLoadedException : new
    activate TripNotLoadedException
    TripNotLoadedException ->> ItineraryRequest : ItineraryRequest::toEntity
    activate ItineraryRequest
    ItineraryRequest ->> Itinerary : builder
    activate Itinerary
    Itinerary -->> ItineraryRequest : #32; 
    deactivate Itinerary
    ItineraryRequest ->> ItineraryBuilder : itineraryName
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest ->> ItineraryBuilder : residences
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest ->> ItineraryBuilder : accommodations
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest ->> ItineraryBuilder : activities
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest ->> ItineraryBuilder : timeSchedule
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest ->> ItineraryBuilder : build
    activate ItineraryBuilder
    ItineraryBuilder -->> ItineraryRequest : #32; 
    deactivate ItineraryBuilder
    ItineraryRequest -->> TripNotLoadedException : #32; 
    deactivate ItineraryRequest
    TripNotLoadedException ->> ItineraryService : itinerary -&gt;
    activate ItineraryService
    ItineraryService -->> TripNotLoadedException : #32; 
    deactivate ItineraryService
    TripNotLoadedException ->> ItineraryResponse : ItineraryResponse::fromEntity
    activate ItineraryResponse
    ItineraryResponse ->> AccommodationDTO : fromEntities
    activate AccommodationDTO
    AccommodationDTO ->> AccommodationDTO : fromEntity
    activate AccommodationDTO
    AccommodationDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> AccommodationDTO : #32; 
    deactivate TimeUtils
    AccommodationDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> AccommodationDTO : #32; 
    deactivate TimeUtils
    AccommodationDTO ->> AccommodationDTO : new
    activate AccommodationDTO
    AccommodationDTO -->> AccommodationDTO : #32; 
    deactivate AccommodationDTO
    AccommodationDTO -->> AccommodationDTO : #32; 
    deactivate AccommodationDTO
    AccommodationDTO -->> ItineraryResponse : #32; 
    deactivate AccommodationDTO
    ItineraryResponse ->> ResidenceDTO : fromEntities
    activate ResidenceDTO
    ResidenceDTO ->> ResidenceDTO : fromEntity
    activate ResidenceDTO
    ResidenceDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ResidenceDTO : #32; 
    deactivate TimeUtils
    ResidenceDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ResidenceDTO : #32; 
    deactivate TimeUtils
    ResidenceDTO ->> ResidenceDTO : new
    activate ResidenceDTO
    ResidenceDTO -->> ResidenceDTO : #32; 
    deactivate ResidenceDTO
    ResidenceDTO -->> ResidenceDTO : #32; 
    deactivate ResidenceDTO
    ResidenceDTO -->> ItineraryResponse : #32; 
    deactivate ResidenceDTO
    ItineraryResponse ->> ActivityDTO : fromEntities
    activate ActivityDTO
    ActivityDTO ->> ActivityDTO : fromEntity
    activate ActivityDTO
    ActivityDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ActivityDTO : #32; 
    deactivate TimeUtils
    ActivityDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ActivityDTO : #32; 
    deactivate TimeUtils
    ActivityDTO ->> ActivityDTO : new
    activate ActivityDTO
    ActivityDTO -->> ActivityDTO : #32; 
    deactivate ActivityDTO
    ActivityDTO -->> ActivityDTO : #32; 
    deactivate ActivityDTO
    ActivityDTO -->> ItineraryResponse : #32; 
    deactivate ActivityDTO
    ItineraryResponse ->> TimeScheduleDTO : new
    activate TimeScheduleDTO
    TimeScheduleDTO ->> TimeScheduleDTO : new
    activate TimeScheduleDTO
    TimeScheduleDTO -->> TimeScheduleDTO : #32; 
    deactivate TimeScheduleDTO
    TimeScheduleDTO ->> TimeUtils : formatDate
    activate TimeUtils
    TimeUtils -->> TimeScheduleDTO : #32; 
    deactivate TimeUtils
    TimeScheduleDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> TimeScheduleDTO : #32; 
    deactivate TimeUtils
    TimeScheduleDTO ->> TimeUtils : formatDate
    activate TimeUtils
    TimeUtils -->> TimeScheduleDTO : #32; 
    deactivate TimeUtils
    TimeScheduleDTO ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> TimeScheduleDTO : #32; 
    deactivate TimeUtils
    TimeScheduleDTO -->> ItineraryResponse : #32; 
    deactivate TimeScheduleDTO
    ItineraryResponse ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ItineraryResponse : #32; 
    deactivate TimeUtils
    ItineraryResponse ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> ItineraryResponse : #32; 
    deactivate TimeUtils
    ItineraryResponse ->> ItineraryResponse : new
    activate ItineraryResponse
    ItineraryResponse -->> ItineraryResponse : #32; 
    deactivate ItineraryResponse
    ItineraryResponse -->> TripNotLoadedException : #32; 
    deactivate ItineraryResponse
    TripNotLoadedException -->> ItineraryService : #32; 
    deactivate TripNotLoadedException
    deactivate ItineraryService
    ```
    

[TripCommentServiceImpl_bringTripComments.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/ed9b7a1f-5e79-497a-8b3c-d616764118c7/TripCommentServiceImpl_bringTripComments.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> TripCommentServiceImpl : bringTripComments
    activate TripCommentServiceImpl
    alt tripRepository.findById(tripId).isPresent()
    TripCommentServiceImpl ->> TripCommentRepository : findByTripTripId
    activate TripCommentRepository
    TripCommentRepository ->> TripCommentGetResponse : TripCommentGetResponse::fromEntity
    activate TripCommentGetResponse
    TripCommentGetResponse ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> TripCommentGetResponse : #32; 
    deactivate TimeUtils
    TripCommentGetResponse ->> TripCommentGetResponse : new
    activate TripCommentGetResponse
    TripCommentGetResponse -->> TripCommentGetResponse : #32; 
    deactivate TripCommentGetResponse
    TripCommentGetResponse -->> TripCommentRepository : #32; 
    deactivate TripCommentGetResponse
    TripCommentRepository -->> TripCommentServiceImpl : #32; 
    deactivate TripCommentRepository
    else 
    TripCommentServiceImpl ->> InvalidTripException : new
    activate InvalidTripException
    InvalidTripException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidTripException : #32; 
    deactivate GlobalException
    InvalidTripException -->> TripCommentServiceImpl : #32; 
    deactivate InvalidTripException
    end
    deactivate TripCommentServiceImpl
    ```
    

[TripCommentServiceImpl_deleteTripComment.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/4e683c3f-5565-4565-85e7-7e6f582f2996/TripCommentServiceImpl_deleteTripComment.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> TripCommentServiceImpl : deleteTripComment
    activate TripCommentServiceImpl
    TripCommentServiceImpl ->> CheckUserLogined : checkUserLogined
    activate CheckUserLogined
    alt principalDetails ==null
    CheckUserLogined ->> InvalidPrincipalDetailsException : new
    activate InvalidPrincipalDetailsException
    InvalidPrincipalDetailsException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidPrincipalDetailsException : #32; 
    deactivate GlobalException
    InvalidPrincipalDetailsException -->> CheckUserLogined : #32; 
    deactivate InvalidPrincipalDetailsException
    end
    CheckUserLogined -->> TripCommentServiceImpl : #32; 
    deactivate CheckUserLogined
    alt tripCommentOptional.isPresent()
    alt !Objects.equals(principalDetails.getUser().getUserId(), tripComment.getUser().getUserId())
    TripCommentServiceImpl ->> InvalidAccessToUpdateTripComment : new
    activate InvalidAccessToUpdateTripComment
    InvalidAccessToUpdateTripComment ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidAccessToUpdateTripComment : #32; 
    deactivate GlobalException
    InvalidAccessToUpdateTripComment -->> TripCommentServiceImpl : #32; 
    deactivate InvalidAccessToUpdateTripComment
    end
    else 
    TripCommentServiceImpl ->> InvalidTripCommentIdException : new
    activate InvalidTripCommentIdException
    InvalidTripCommentIdException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidTripCommentIdException : #32; 
    deactivate GlobalException
    InvalidTripCommentIdException -->> TripCommentServiceImpl : #32; 
    deactivate InvalidTripCommentIdException
    end
    deactivate TripCommentServiceImpl
    ```
    

[TripCommentServiceImpl_insertTripComment.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/e001d4a7-35cb-4baa-8434-7092ae1f212a/TripCommentServiceImpl_insertTripComment.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> TripCommentServiceImpl : insertTripComment
    activate TripCommentServiceImpl
    TripCommentServiceImpl ->> CheckUserLogined : checkUserLogined
    activate CheckUserLogined
    alt principalDetails ==null
    CheckUserLogined ->> InvalidPrincipalDetailsException : new
    activate InvalidPrincipalDetailsException
    InvalidPrincipalDetailsException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidPrincipalDetailsException : #32; 
    deactivate GlobalException
    InvalidPrincipalDetailsException -->> CheckUserLogined : #32; 
    deactivate InvalidPrincipalDetailsException
    end
    CheckUserLogined -->> TripCommentServiceImpl : #32; 
    deactivate CheckUserLogined
    alt tripOptional.isPresent()
    TripCommentServiceImpl ->> UserRepository : findByUserId
    activate UserRepository
    UserRepository -->> TripCommentServiceImpl : #32; 
    deactivate UserRepository
    TripCommentServiceImpl ->> TripCommentAddRequest : toEntity
    activate TripCommentAddRequest
    TripCommentAddRequest ->> TripComment : builder
    activate TripComment
    TripComment -->> TripCommentAddRequest : #32; 
    deactivate TripComment
    TripCommentAddRequest ->> TripCommentBuilder : trip
    activate TripCommentBuilder
    TripCommentBuilder -->> TripCommentAddRequest : #32; 
    deactivate TripCommentBuilder
    TripCommentAddRequest ->> TripCommentBuilder : user
    activate TripCommentBuilder
    TripCommentBuilder -->> TripCommentAddRequest : #32; 
    deactivate TripCommentBuilder
    TripCommentAddRequest ->> TripCommentBuilder : tripComment
    activate TripCommentBuilder
    TripCommentBuilder -->> TripCommentAddRequest : #32; 
    deactivate TripCommentBuilder
    TripCommentAddRequest ->> TripCommentBuilder : build
    activate TripCommentBuilder
    TripCommentBuilder -->> TripCommentAddRequest : #32; 
    deactivate TripCommentBuilder
    TripCommentAddRequest -->> TripCommentServiceImpl : #32; 
    deactivate TripCommentAddRequest
    TripCommentServiceImpl ->> TripCommentAddResponse : fromEntity
    activate TripCommentAddResponse
    TripCommentAddResponse ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> TripCommentAddResponse : #32; 
    deactivate TimeUtils
    TripCommentAddResponse ->> TripCommentAddResponse : new
    activate TripCommentAddResponse
    TripCommentAddResponse -->> TripCommentAddResponse : #32; 
    deactivate TripCommentAddResponse
    TripCommentAddResponse -->> TripCommentServiceImpl : #32; 
    deactivate TripCommentAddResponse
    else 
    TripCommentServiceImpl ->> InvalidTripException : new
    activate InvalidTripException
    InvalidTripException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidTripException : #32; 
    deactivate GlobalException
    InvalidTripException -->> TripCommentServiceImpl : #32; 
    deactivate InvalidTripException
    end
    deactivate TripCommentServiceImpl
    ```
    

[TripCommentServiceImpl_updateTripComment.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/55cf536d-6849-4a01-a3d4-cda8ebc8da11/TripCommentServiceImpl_updateTripComment.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> TripCommentServiceImpl : updateTripComment
    activate TripCommentServiceImpl
    TripCommentServiceImpl ->> CheckUserLogined : checkUserLogined
    activate CheckUserLogined
    CheckUserLogined ->> InvalidPrincipalDetailsException : new
    activate InvalidPrincipalDetailsException
    InvalidPrincipalDetailsException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidPrincipalDetailsException : #32; 
    deactivate GlobalException
    InvalidPrincipalDetailsException -->> CheckUserLogined : #32; 
    deactivate InvalidPrincipalDetailsException
    CheckUserLogined -->> TripCommentServiceImpl : #32; 
    deactivate CheckUserLogined
    TripCommentServiceImpl ->> InvalidAccessToUpdateTripComment : new
    activate InvalidAccessToUpdateTripComment
    InvalidAccessToUpdateTripComment ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidAccessToUpdateTripComment : #32; 
    deactivate GlobalException
    InvalidAccessToUpdateTripComment -->> TripCommentServiceImpl : #32; 
    deactivate InvalidAccessToUpdateTripComment
    TripCommentServiceImpl ->> TripComment : editTripComment
    activate TripComment
    TripComment ->> TripCommentUpdateRequest : tripComment
    activate TripCommentUpdateRequest
    TripCommentUpdateRequest -->> TripComment : #32; 
    deactivate TripCommentUpdateRequest
    TripComment -->> TripCommentServiceImpl : #32; 
    deactivate TripComment
    TripCommentServiceImpl ->> TripCommentUpdateResponse : fromEntity
    activate TripCommentUpdateResponse
    TripCommentUpdateResponse ->> TimeUtils : formatDateTime
    activate TimeUtils
    TimeUtils -->> TripCommentUpdateResponse : #32; 
    deactivate TimeUtils
    TripCommentUpdateResponse ->> TripCommentUpdateResponse : new
    activate TripCommentUpdateResponse
    TripCommentUpdateResponse -->> TripCommentUpdateResponse : #32; 
    deactivate TripCommentUpdateResponse
    TripCommentUpdateResponse -->> TripCommentServiceImpl : #32; 
    deactivate TripCommentUpdateResponse
    TripCommentServiceImpl ->> InvalidTripCommentIdException : new
    activate InvalidTripCommentIdException
    InvalidTripCommentIdException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidTripCommentIdException : #32; 
    deactivate GlobalException
    InvalidTripCommentIdException -->> TripCommentServiceImpl : #32; 
    deactivate InvalidTripCommentIdException
    deactivate TripCommentServiceImpl
    ```
    

[UserLikeServiceImpl_addUserLike.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/850394bd-e63f-4ba6-aca7-5a33dc9833f3/UserLikeServiceImpl_addUserLike.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> UserLikeServiceImpl : addUserLike
    activate UserLikeServiceImpl
    UserLikeServiceImpl ->> CheckUserLogined : checkUserLogined
    activate CheckUserLogined
    alt principalDetails ==null
    CheckUserLogined ->> InvalidPrincipalDetailsException : new
    activate InvalidPrincipalDetailsException
    InvalidPrincipalDetailsException ->> GlobalException : new
    activate GlobalException
    GlobalException -->> InvalidPrincipalDetailsException : #32; 
    deactivate GlobalException
    InvalidPrincipalDetailsException -->> CheckUserLogined : #32; 
    deactivate InvalidPrincipalDetailsException
    end
    CheckUserLogined -->> UserLikeServiceImpl : #32; 
    deactivate CheckUserLogined
    alt tripOptional.isEmpty()
    UserLikeServiceImpl ->> TripNotLoadedException : new
    activate TripNotLoadedException
    TripNotLoadedException -->> UserLikeServiceImpl : #32; 
    deactivate TripNotLoadedException
    end
    UserLikeServiceImpl ->> UserLike : like
    activate UserLike
    UserLike ->> UserLike : new
    activate UserLike
    UserLike -->> UserLike : #32; 
    deactivate UserLike
    UserLike ->> UserLike : setTripAndUser
    activate UserLike
    UserLike -->> UserLike : #32; 
    deactivate UserLike
    UserLike -->> UserLikeServiceImpl : #32; 
    deactivate UserLike
    UserLikeServiceImpl ->> UserLikeRepository : findByUserAndTripTripId
    activate UserLikeRepository
    UserLikeRepository -->> UserLikeServiceImpl : #32; 
    deactivate UserLikeRepository
    alt userLikeOptional.isPresent()
    UserLikeServiceImpl ->> UserLikeAddTripResponse : new
    activate UserLikeAddTripResponse
    UserLikeAddTripResponse -->> UserLikeServiceImpl : #32; 
    deactivate UserLikeAddTripResponse
    end
    UserLikeServiceImpl ->> UserLikeAddTripResponse : fromEntity
    activate UserLikeAddTripResponse
    UserLikeAddTripResponse ->> UserLikeAddTripResponse : new
    activate UserLikeAddTripResponse
    UserLikeAddTripResponse -->> UserLikeAddTripResponse : #32; 
    deactivate UserLikeAddTripResponse
    UserLikeAddTripResponse -->> UserLikeServiceImpl : #32; 
    deactivate UserLikeAddTripResponse
    deactivate UserLikeServiceImpl
    ```
    

[UserLikeServiceImpl_bringUserLike.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/08566884-49c5-463c-a879-10ee0eb44bf0/UserLikeServiceImpl_bringUserLike.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> UserLikeServiceImpl : bringUserLike
    activate UserLikeServiceImpl
    UserLikeServiceImpl ->> UserLikeRepository : findByUser
    activate UserLikeRepository
    UserLikeRepository ->> UserLikeGetTripsResponse : UserLikeGetTripsResponse::fromEntity
    activate UserLikeGetTripsResponse
    UserLikeGetTripsResponse ->> UserLikeGetTripsResponse : new
    activate UserLikeGetTripsResponse
    UserLikeGetTripsResponse -->> UserLikeGetTripsResponse : #32; 
    deactivate UserLikeGetTripsResponse
    UserLikeGetTripsResponse -->> UserLikeRepository : #32; 
    deactivate UserLikeGetTripsResponse
    UserLikeRepository -->> UserLikeServiceImpl : #32; 
    deactivate UserLikeRepository
    deactivate UserLikeServiceImpl
    ```
    

[UserLikeServiceImpl_removeUserLike.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/2d20f90a-b261-4d60-98a0-d9144f368f74/UserLikeServiceImpl_removeUserLike.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> UserLikeServiceImpl : removeUserLike
    activate UserLikeServiceImpl
    UserLikeServiceImpl ->> UserLikeRepository : findByUserAndTripTripId
    activate UserLikeRepository
    UserLikeRepository -->> UserLikeServiceImpl : #32; 
    deactivate UserLikeRepository
    alt userLike.isEmpty()
    UserLikeServiceImpl ->> DeleteUserLikeResponse : fromEntity
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse ->> DeleteUserLikeResponse : new
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> DeleteUserLikeResponse : #32; 
    deactivate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> UserLikeServiceImpl : #32; 
    deactivate DeleteUserLikeResponse
    end
    alt !Objects.equals(userLike.get().getUser().getUserId(), principalDetails.getUser().getUserId())
    UserLikeServiceImpl ->> DeleteUserLikeResponse : fromEntity
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse ->> DeleteUserLikeResponse : new
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> DeleteUserLikeResponse : #32; 
    deactivate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> UserLikeServiceImpl : #32; 
    deactivate DeleteUserLikeResponse
    end
    UserLikeServiceImpl ->> UserLike : delete
    activate UserLike
    UserLike -->> UserLikeServiceImpl : #32; 
    deactivate UserLike
    UserLikeServiceImpl ->> DeleteUserLikeResponse : fromEntity
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse ->> DeleteUserLikeResponse : new
    activate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> DeleteUserLikeResponse : #32; 
    deactivate DeleteUserLikeResponse
    DeleteUserLikeResponse -->> UserLikeServiceImpl : #32; 
    deactivate DeleteUserLikeResponse
    deactivate UserLikeServiceImpl
    ```
    

[UserServiceImpl_signup.mmd](https://prod-files-secure.s3.us-west-2.amazonaws.com/3ef8dbd9-414c-4cf5-813d-32ecb943cc67/85f06d28-5b41-4168-b589-04b901483b22/UserServiceImpl_signup.mmd)

- 
    
    ```mermaid
    sequenceDiagram
    actor User
    User ->> UserServiceImpl : signup
    activate UserServiceImpl
    UserServiceImpl ->> CreateUserRequest : email
    activate CreateUserRequest
    CreateUserRequest -->> UserServiceImpl : #32; 
    deactivate CreateUserRequest
    UserServiceImpl ->> UserRepository : findByEmail
    activate UserRepository
    UserRepository -->> UserServiceImpl : #32; 
    deactivate UserRepository
    alt userRepository.findByEmail(createUserRequest.email()).isPresent()
    UserServiceImpl ->> EmailDuplicateError : new
    activate EmailDuplicateError
    EmailDuplicateError -->> UserServiceImpl : #32; 
    deactivate EmailDuplicateError
    end
    UserServiceImpl ->> CreateUserRequest : toEntity
    activate CreateUserRequest
    CreateUserRequest ->> User : builder
    activate User
    User -->> CreateUserRequest : #32; 
    deactivate User
    CreateUserRequest ->> UserBuilder : email
    activate UserBuilder
    UserBuilder -->> CreateUserRequest : #32; 
    deactivate UserBuilder
    CreateUserRequest ->> UserBuilder : username
    activate UserBuilder
    UserBuilder -->> CreateUserRequest : #32; 
    deactivate UserBuilder
    CreateUserRequest ->> UserBuilder : password
    activate UserBuilder
    UserBuilder -->> CreateUserRequest : #32; 
    deactivate UserBuilder
    CreateUserRequest ->> UserBuilder : authority
    activate UserBuilder
    UserBuilder -->> CreateUserRequest : #32; 
    deactivate UserBuilder
    CreateUserRequest ->> UserBuilder : build
    activate UserBuilder
    UserBuilder -->> CreateUserRequest : #32; 
    deactivate UserBuilder
    CreateUserRequest -->> UserServiceImpl : #32; 
    deactivate CreateUserRequest
    UserServiceImpl ->> CreateUserResponse : fromEntity
    activate CreateUserResponse
    CreateUserResponse ->> CreateUserResponse : new
    activate CreateUserResponse
    CreateUserResponse -->> CreateUserResponse : #32; 
    deactivate CreateUserResponse
    CreateUserResponse -->> UserServiceImpl : #32; 
    deactivate CreateUserResponse
    deactivate UserServiceImpl
    ```
