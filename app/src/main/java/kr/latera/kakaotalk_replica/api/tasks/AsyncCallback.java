package kr.latera.kakaotalk_replica.api.tasks;

/**
 * Created by jinwoo on 2016-11-14.
 */

public interface AsyncCallback<T>
{
	public void onPreExecute();
	public void onPostExecute(T arg);
}
