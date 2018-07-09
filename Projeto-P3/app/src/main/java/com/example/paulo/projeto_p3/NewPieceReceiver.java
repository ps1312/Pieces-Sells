package com.example.paulo.projeto_p3;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.parse.ParseUser;

public class NewPieceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null && currentUser.getUsername().equals("admin")) {
            //Notificacao quando alguma peça nova for adicionada
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext());
            builder
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("App")
                    .setContentText("Nova peça adicionada, veja agora mesmo");
            Intent openApp = new Intent(context.getApplicationContext(), ListPendingPiecesActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, openApp, 0);
            builder.setContentIntent(pendingIntent);
            Notification notification = builder.build();
            NotificationManagerCompat.from(context.getApplicationContext()).notify(0, notification);
        }
    }
}
