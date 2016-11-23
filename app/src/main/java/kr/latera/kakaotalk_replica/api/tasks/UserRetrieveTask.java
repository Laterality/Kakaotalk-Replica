package kr.latera.kakaotalk_replica.api.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import kr.latera.kakaotalk_replica.api.API_Internal;
import kr.latera.kakaotalk_replica.api.dto.res.UserResponseDto;
import kr.latera.kakaotalk_replica.utility.LConstants;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class UserRetrieveTask extends AsyncTask<String, Void, UserResponseDto>
{
	private static String TAG;
	private AsyncCallback<UserResponseDto> cb;

	public UserRetrieveTask(AsyncCallback<UserResponseDto> callback)
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		cb = callback;
	}

	@Override
	protected void onPreExecute()
	{
		if(cb != null) { cb.onPreExecute(); }
	}

	@Override
	protected UserResponseDto doInBackground(String... params)
	{
		try
		{
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(API_Internal.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			API_Internal.Services.UserService service = retrofit.create(API_Internal.Services.UserService.class);

			Call<UserResponseDto> call = service.retrieveUser(params[0]);

			Response<UserResponseDto> res = call.execute();

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
}
