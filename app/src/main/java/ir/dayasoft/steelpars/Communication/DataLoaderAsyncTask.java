package ir.dayasoft.steelpars.Communication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import ir.dayasoft.steelpars.Core.Core;


public class DataLoaderAsyncTask extends AsyncTask<String, Integer, Integer> {
	ProgressDialog pDialog;
	Context context;
    Boolean showDialog;

	@Override
	protected Integer doInBackground(String... urls) {

		try {

			Core.ApplicationLuncher.copyAssets(context);
			return 1;
		} catch (Exception e) {

			return 0;
		}

	}

	public DataLoaderAsyncTask(Context context , Boolean showDialog) {

		this.context = context;
        this.showDialog=showDialog;

	}

	@Override
	protected void onPreExecute() {

        if (showDialog) {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("کمی صبر نمایید");
            pDialog.setIndeterminate(false);
            pDialog.setProgress(0);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setCancelable(false);
            pDialog.show();
        }
	}

	@Override
	protected void onPostExecute(Integer result) {

		if (result.equals(1)) {
		//	PropertiesHandler.SetIsLoadData(context, true);
            if (showDialog)
            pDialog.dismiss();

		} else if (result.equals(0)) {
            if (showDialog)
			pDialog.dismiss();

			/*com.garson24.individualclass.CreateDialog createDialog = new com.garson24.individualclass.CreateDialog();
			createDialog
					.CreateCustomDialog(
							context,
							Farsi.Convert(
                                    context.getString(R.string.dialog_title_error_dataLoading),
                                    PropertiesHandeler.GetFontFlag(context)),
							Farsi.Convert(
									context.getString(R.string.dialog_content_error_dataLoading),
									PropertiesHandeler.GetFontFlag(context)),
							Farsi.Convert(
									context.getString(R.string.dialog_button_error_dataLoading),
									PropertiesHandeler.GetFontFlag(context)));*/
		}

	}

	protected void onProgressUpdate(Integer... progress) {

        if (showDialog)
		pDialog.setProgress(progress[0]);
	}

}
