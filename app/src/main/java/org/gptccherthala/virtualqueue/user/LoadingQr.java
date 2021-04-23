package org.gptccherthala.virtualqueue.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import org.gptccherthala.virtualqueue.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class LoadingQr {
    private Activity activity;
    private AlertDialog dialog;
    private ImageView imageView;

    public LoadingQr(Context context) {
        activity = (Activity) context;
    }

    public void displayQrCode(String userId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.display_qrcode, null);
        imageView = view.findViewById(R.id.qrcode);

        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        int width = point.x;
        int height = point.y;

        int dimen = Math.min(width, height);
        dimen = dimen * 3 / 4;

        QRGEncoder qrgEncoder = new QRGEncoder(userId, null, QRGContents.Type.TEXT, dimen);
        try {
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        builder.setView(view);
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }
}
