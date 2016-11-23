package kr.latera.kakaotalk_replica.api;

import kr.latera.kakaotalk_replica.api.dto.ChatroomDto;
import kr.latera.kakaotalk_replica.api.dto.req.ChatroomCreateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.req.DeviceRequestDto;
import kr.latera.kakaotalk_replica.api.dto.req.DeviceUpdateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.req.LoginRequestDto;
import kr.latera.kakaotalk_replica.api.dto.req.UserTempCreateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.req.UserUpdateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.res.ChatroomResponseDto;
import kr.latera.kakaotalk_replica.api.dto.res.DeviceResponseDto;
import kr.latera.kakaotalk_replica.api.dto.res.UserResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jinwoo on 2016-11-09.
 */

public class API_Internal
{
	public static final String BASE_URL = "https://apps.latera.kr/kr/api/";

	public static final String PATH_PARAM_ID = "id";

	public static final String PATH_POST_DEVICE = "device";
	public static final String PATH_PUT_DEVICE = "device";
	public static final String PATH_POST_USER_LOGIN = "user/login";
	public static final String PATH_POST_USER = "user";
	public static final String PATH_PUT_USER = "user";
	public static final String PATH_GET_USER = "user/{" + PATH_PARAM_ID + "}";
	public static final String PATH_GET_CHATROOM = "chatroom/{" + PATH_PARAM_ID + "}";
	public static final String PATH_POST_CHATROOM = "chatroom";

	public static final String PLATFORM_ANDROID = "android";



	public static class Services
	{
		public interface DeviceService
		{
			/**
			 * 디바이스 등록 API
			 * <p>
			 * Path : /api/device
			 * Method : POST
			 * <p>
			 * Request
			 *
			 * @body.cc : 국가 코드
			 * @body.phone : 사용자 전화번호
			 * @body.token : 푸시 메시지 토큰
			 * @body.platform : 디바이스의 플랫폼["android", "ios", "windows"]
			 * <p>
			 * Response
			 * @body.result : 처리 결과["duplicate", "success", "error"]
			 * @body.user : 등록된 디바이스 정보
			 */
			@POST(PATH_POST_DEVICE)
			public Call<DeviceResponseDto> createDevice(@Body DeviceRequestDto request);


			/**
			 * 디바이스 갱신 API
			 * <p>
			 * Path : /api/device
			 * Method : PUT
			 * <p>
			 * Request
			 *
			 * @body.device_id : 디바이스 id
			 * @body.user_id : 사용자 id
			 * <p>
			 * Response
			 * @body.result : 처리 결과["not found", "success", "error"]
			 * @body.device : 갱신된 디바이스 정보
			 */
			@PUT(PATH_PUT_DEVICE)
			public Call<DeviceResponseDto> updateDevice(@Body DeviceUpdateRequestDto request);
		}

		public interface  UserService
		{
			/**
			 * 임시 사용자 등록 API
			 *
			 * Path : /api/user
			 * Method : POST
			 *
			 * Request
			 * @body.username : string, 사용자명
			 * @body.device : string, 디바이스 id
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "error"];
			 * @body.user : 등록된 사용자 정보
			 */
			@POST(PATH_POST_USER)
			public Call<UserResponseDto> createUser(@Body UserTempCreateRequestDto request);

			/**
			 * 사용자 조회 API
			 *
			 * Path : /api/user/{id}
			 * Method : GET
			 *
			 * Request
			 * @query.id : 사용자 id
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "not found", "error"]
			 * @body.user : UserSchema, 사용자 정보
			 */
			@GET(PATH_GET_USER)
			public Call<UserResponseDto> retrieveUser(@Path(PATH_PARAM_ID) String user_id);


			/**
			 * 사용자 등록 API
			 *
			 * Path : /api/user
			 * Method : PUT
			 *
			 * Request
			 * @body.user_id : string, 사용자 id
			 * @body.email : string, 사용자 이메일
			 * @body.password : string, 사용자 비밀번호(raw string)
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "duplicate", "fail", error"]
			 * @body.user : UserSchema, 등록된 사용자 정보
			 */
			@PUT(PATH_PUT_USER)
			public Call<UserResponseDto> updateUser(@Body UserUpdateRequestDto request);
		}

		public interface AuthService
		{
			/**
			 * 사용자 로그인 API
			 *
			 * Path : /api/user/login
			 * Method : POST
			 *
			 * Request
			 * @body.email : string, 사용자 이메일
			 * @body.password : string, 사용자 비밀번호(raw string)
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "incorrect", "error"]
			 * @body.user : UserSchema, 로그인 성공한 경우 해당 유저 정보
			 */
			@POST(PATH_POST_USER_LOGIN)
			public Call<UserResponseDto> login(@Body LoginRequestDto request);
		}

		public interface ChatroomService
		{
			/**
			 * 채팅방 생성 API
			 *
			 * Path : /api/chatroom
			 * Method : POST
			 *
			 * Request
			 * @body.members : array<string>, 구성원의 id 배열
			 * @body.title : string, 채팅방 이름
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "error"]
			 * @body.chatroom : 생성된 채팅방 정보
			 */
			@POST(PATH_POST_CHATROOM)
			public Call<ChatroomResponseDto> createChatroom(@Body ChatroomCreateRequestDto request);


			/**
			 * 채팅방 조회 API
			 *
			 * Path : /api/chatroom/{id}
			 * Method : GET
			 *
			 * Request
			 * @path.id : string, 조회할 채팅방 id
			 *
			 * Response
			 * @body.result : string, 처리 결과["success", "error"]
			 * @body.chatroom : 생성된 채팅방 정보
			 */
			@GET(PATH_GET_CHATROOM)
			public Call<ChatroomResponseDto> retrieveChatroom(@Path(PATH_PARAM_ID) String id);
		}

	}
}
