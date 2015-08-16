package ir.dayasoft.steelpars.Communication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;


public class ImageDownloader extends AsyncTask<String, Integer, Integer> {

	ProgressDialog pDialog;
	Context context;
	Boolean flagShowDialog;

	@Override
	protected Integer doInBackground(String... urls) {
		int cnt = 0;
		int error = 0;
        if (!Core.Communication.isConnectingToInternet2(context))
            return 0 ;

        if (flagShowDialog)
		pDialog.setMax(urls.length);
		for (String url : urls) {

			String ImageName = url;
			String ServerUrl = Constants.WebImageDirectoryPath + url;
			Bitmap temp = downloadBitmap(ServerUrl);
			if (temp != null) {
				saveImageToSD(temp, ImageName.substring(ImageName.lastIndexOf("/")));
				cnt++;
			} else {
				error++;

			}

			if (error > (cnt + 3) * 2)
				return 0;

			publishProgress(cnt);

		}

		return 1;
	}

	public ImageDownloader(ProgressDialog dialog, Boolean FlagShowDialog, Context context) {
		this.context = context;
		this.pDialog = dialog;
		this.flagShowDialog=FlagShowDialog;
	}

	@Override
	protected void onPreExecute() {

		if (flagShowDialog)
		pDialog.show();
	}

	@Override
	protected void onPostExecute(Integer result) {

		
		if (flagShowDialog)
		pDialog.dismiss();
		if (result.equals(0) && flagShowDialog) {
			
			/*com.hamraahafzaar.Ekhtebar.CreateDialog createDialog = new com.hamraahafzaar.Ekhtebar.CreateDialog();
			createDialog.CreateCustomDialog(context, "خطا بروزرسانی",
					"لطفا دسترسی به اینترنت را چک بفرمایید", "تایید");*/
		}

	}

	protected void onProgressUpdate(Integer... progress) {

		if (flagShowDialog)
		pDialog.setProgress(progress[0]);
	}

	private Bitmap downloadBitmap(String url) {
		// initilize the default HTTP client object
		final DefaultHttpClient client = new DefaultHttpClient();
		// forming a HttoGet request
		final HttpGet getRequest = new HttpGet(url);
		try {
			HttpResponse response = client.execute(getRequest);
			// check 200 OK for success
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w("ImageDownloader", "Error " + statusCode
                        + " while retrieving bitmap from " + url);
				return null;
			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					// getting contents from the stream
					inputStream = entity.getContent();
					// decoding stream data back into image Bitmap that android
					// understands
					final Bitmap bitmap = BitmapFactory
							.decodeStream(inputStream);
					// saveImageToSD(bitmap);
					// imageView.setImageBitmap(bitmap);
					return bitmap;
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
			// You Could provide a more explicit error message for IOException
			getRequest.abort();
			Log.e("ImageDownloader", "Something went wrong while"
                    + " retrieving bitmap from " + url + e.toString());
		}

		return null;

	}

	private void saveImageToSD(Bitmap bitmap, String imageName) {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

		try {
			File directory = new File(Constants.ImageDirectoryPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File f = new File(directory, imageName);

			f.createNewFile();

			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());

			fo.close();

		} catch (Exception exception) {
			Log.i("imagedownloader", exception.getMessage());
		}

	}

}
