package kr.latera.kakaotalk_replica.api.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import kr.latera.kakaotalk_replica.api.API_Internal;
import kr.latera.kakaotalk_replica.api.dto.req.ChatroomCreateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.res.ChatroomResponseDto;
import kr.latera.kakaotalk_replica.utility.LConstants;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class ChatroomCreateTask extends AsyncTask<String, Void, ChatroomResponseDto>
{
	private static String TAG;
	private AsyncCallback<ChatroomResponseDto> cb;
	private List<String> member;

	public ChatroomCreateTask(AsyncCallback<ChatroomResponseDto> callback, List<String> members)
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		cb = callback;
		this.member = members;
	}

	@Override
	protected void onPreExecute()
	{
		if(cb != null){cb.onPreExecute();}
	}

	@Override
	protected ChatroomResponseDto doInBackground(String... params)
	{
		try
		{
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(API_Internal.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			API_Internal.Services.ChatroomService service = retrofit.create(API_Internal.Services.ChatroomService.class);

			Call<ChatroomResponseDto> call = service.createChatroom(new ChatroomCreateRequestDto(
					member,
					params[0]
			));

			Response<ChatroomResponseDto> res = call.execute();

			if(res.isSuccessful())
			{
				Log.d(TAG, "Execution success");
			}
			else
			{
				Log.d(TAG, "Execution fail");
				Log.d(TAG, res.code() + " : " + res.message());
			}

			return res.body();
		}
		catch (IOException e)
		{
			Log.d(TAG, "Exception occurred");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ChatroomResponseDto res)
	{
		if(cb != null) {cb.onPostExecute(res);}
	}
}
