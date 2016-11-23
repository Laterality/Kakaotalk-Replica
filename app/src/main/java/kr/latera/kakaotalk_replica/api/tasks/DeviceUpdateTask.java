package kr.latera.kakaotalk_replica.api.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import kr.latera.kakaotalk_replica.api.API_Internal;
import kr.latera.kakaotalk_replica.api.dto.req.DeviceUpdateRequestDto;
import kr.latera.kakaotalk_replica.api.dto.res.DeviceResponseDto;
import kr.latera.kakaotalk_replica.utility.LConstants;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jinwoo on 2016-11-14.
 */

public class DeviceUpdateTask extends AsyncTask<String, Void, DeviceResponseDto>
{
	private static String TAG;
	private AsyncCallback<DeviceResponseDto> cb;

	public DeviceUpdateTask(AsyncCallback<DeviceResponseDto> callback)
	{
		TAG = LConstants.TAG_PREFIX + getClass().getSimpleName();
		this.cb = callback;
	}

	@Override
	protected void onPreExecute()
	{
		if(cb != null) {cb.onPreExecute();}
	}

	@Override
	protected DeviceResponseDto doInBackground(String... params)
	{
		try
		{
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(API_Internal.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();

			API_Internal.Services.DeviceService service = retrofit.create(API_Internal.Services.DeviceService.class);

			Call<DeviceResponseDto> call = service.updateDevice(new DeviceUpdateRequestDto(
					params[0],
					params[1]
			));

			Response<DeviceResponseDto> res = call.execute();

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
	protected void onPostExecute(DeviceResponseDto res)
	{
		if(cb != null) {cb.onPostExecute(res);}
	}
}
